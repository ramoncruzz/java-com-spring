package com.candangas.controllers.utils;

import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.candangas.DAO.util.SMSValidacaoDAO;
import com.candangas.DAO.util.ServidorConfiguracoesDAO;
import com.candangas.helpers.JsonString;
import com.candangas.model.util.SMSValidacao;
import com.candangas.model.util.ServidorConfiguracoes;

@RestController
@RequestMapping("/v0/sms")
public class SMSController {

	@Autowired
	SMSValidacaoDAO smsDao;

	@Autowired
	ServidorConfiguracoesDAO servidorDao;
	
	@GetMapping
	public List<SMSValidacao> getSMS() {
		return smsDao.findAll();
	}

	@PostMapping(value="/gerar-codigo",produces="application/json")
	public String geraCodigo(@RequestBody SMSValidacao sms) {
		try {
		String codigo = geraCodigo(6);
		String codigoFormatado = "Seu código  do Aplicativo As Candangas  é " + codigo.substring(0, 3) + " " + codigo.substring(3) + " ";

		SMSValidacao smsSalvar = new SMSValidacao();
		smsSalvar.setCodigoValidacao(codigo);
		smsSalvar.setTelefone(sms.getTelefone());
		smsSalvar.setValidado(false);

		SMSValidacao s = smsDao.save(smsSalvar);
		s.getId();
		
		enviaSMSAPITotalVoice(sms.getTelefone(),codigoFormatado);
		
			return JsonString.geraJsonOKWithMensage(codigoFormatado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return JsonString.jsonErroMensagem(e.getMessage());
		}
	}

	@PostMapping(value="/validar",produces="application/json")
	public String  validaCodigo(@RequestBody SMSValidacao sms) {
		
		boolean resultado = false;
		List<SMSValidacao> lista = smsDao.findByTelefone(sms.getTelefone());
		SMSValidacao smsSalvo = lista.get(lista.size() - 1);

		if (smsSalvo != null) {
			if (!smsSalvo.isValidado()) {
				if (smsSalvo.getCodigoValidacao().equals(sms.getCodigoValidacao())) {
					resultado = true;
					smsSalvo.setValidado(true);
					smsDao.save(smsSalvo);
				}
			}
		} else
			resultado = false;
		
		if(resultado)
			return JsonString.geraJsonOK();
		 else
			return JsonString.jsonErroMensagem("Código inválido!");
	}

	private String geraCodigo(int len) {
		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] senha = new char[len];
		int chartLenght = chart.length;
		Random rdm = new Random();
		for (int x = 0; x < len; x++) {
			senha[x] = chart[rdm.nextInt(chartLenght)];
		}
		return new String(senha);
	}

	// TODO melhorar uso dessa tecnologia
	public String enviaSMS(String phoneNumber, String message) {
		String ACCESS_KEY = "AKIAJ6IVG3WDASEMYSHQ";
		String SECRET_KEY = "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";
		BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
		@SuppressWarnings("deprecation")
		AmazonSNSClient snsClient = new AmazonSNSClient(credentials).withRegion(Regions.US_EAST_1);

		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();

		return sendSMSMessage(snsClient, message, phoneNumber, smsAttributes).toString();
	}

	private PublishResult sendSMSMessage(AmazonSNSClient snsClient, String message, String phoneNumber,
			Map<String, MessageAttributeValue> smsAttributes) {
		PublishResult result = snsClient.publish(new PublishRequest().withMessage(message).withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));

		return result;
	}

	public void enviaSMSAPITotalVoice(String numeroDestino,String mensagemTexto) throws ClientProtocolException, IOException, JSONException,Exception {
	
		JSONObject mensagem = new JSONObject();
		ServidorConfiguracoes servidor = servidorDao.findFirstByOrderByIdDesc();
		mensagem.put("numero_destino", numeroDestino);
		mensagem.put("mensagem", mensagemTexto);
		mensagem.put("resposta_usuario", false);
		mensagem.put("multi_sms", false);
		
		HttpClient httpClient=HttpClients.createDefault();
        
//        HttpPost httpPost = new HttpPost("https://api.totalvoice.com.br/sms");
//        httpPost.setHeader("Access-Token", "b4e5c480634ccd2903b2ef80aee7fa40");
		
		HttpPost httpPost = new HttpPost(servidor.getUlrTotalVoiceSMS());
        httpPost.setHeader("Access-Token", servidor.getAccesTokenTotalVoiceSMS());
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("accept", "text/json");
        StringEntity json = new StringEntity(mensagem.toString());
        httpPost.setEntity(json);
        
        @SuppressWarnings("unused")
		HttpResponse response = httpClient.execute(httpPost);
        
	}

}
