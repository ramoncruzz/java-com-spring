package com.ramon.teste.controllers.utils;

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

import com.ramon.teste.DAO.util.SMSValidacaoDAO;
import com.ramon.teste.model.util.SMSValidacao;

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

@RestController
@RequestMapping("/v0/sms")
public class SMSController {

	@Autowired
	SMSValidacaoDAO smsDao;

	@GetMapping
	public List<SMSValidacao> getSMS() {
		return smsDao.findAll();
	}

	@PostMapping("/gerar-codigo")
	public String geraCodigo(@RequestBody SMSValidacao sms) {
		try {
		String codigo = geraCodigo(6);
		String codigoFormatado = "Olá, Seu código  do App Restaurante Vitória é " + codigo.substring(0, 3) + " " + codigo.substring(3) + " ";

		SMSValidacao smsSalvar = new SMSValidacao();
		smsSalvar.setCodigoValidacao(codigo);
		smsSalvar.setTelefone(sms.getTelefone());
		smsSalvar.setValidado(false);

		SMSValidacao s = smsDao.save(smsSalvar);
		s.getId();
		// enviaSMS("+55"+sms.getTelefone(), codigoFormatado);
		
		enviaSMSAPITotalVoice(sms.getTelefone(),codigoFormatado);
		
			return codigoFormatado;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@PostMapping("/validar")
	public boolean validaCodigo(@RequestBody SMSValidacao sms) {
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

		return resultado;
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

	public void enviaSMSAPITotalVoice(String numeroDestino,String mensagemTexto) throws ClientProtocolException, IOException, JSONException {
		
		JSONObject mensagem = new JSONObject();
		
		mensagem.put("numero_destino", numeroDestino);
		mensagem.put("mensagem", mensagemTexto);
		mensagem.put("resposta_usuario", false);
		mensagem.put("multi_sms", false);
		
		HttpClient httpClient=HttpClients.createDefault();
        
        HttpPost httpPost = new HttpPost("https://api.totalvoice.com.br/sms");
        httpPost.setHeader("Access-Token", "b4e5c480634ccd2903b2ef80aee7fa40");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("accept", "text/json");
        StringEntity json = new StringEntity(mensagem.toString());
        httpPost.setEntity(json);
        
        HttpResponse response = httpClient.execute(httpPost);
        
	}

}
