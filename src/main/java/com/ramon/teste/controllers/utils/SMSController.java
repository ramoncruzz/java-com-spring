package com.ramon.teste.controllers.utils;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.teste.DAO.util.SMSValidacaoDAO;
import com.ramon.teste.model.util.SMSValidacao;

@RestController
@RequestMapping("/sms")
public class SMSController {
	
	@Autowired
	SMSValidacaoDAO smsDao;
	
	@PostMapping("/gerar-codigo")
	public String geraCodigo(@RequestBody SMSValidacao sms)
	{
		String codigo = geraCodigo(6);
		String codigoFormatado = "Seu código é "+codigo.substring(0, 3)+" " + codigo.substring(3) +" "+sms.getTelefone();
		
		SMSValidacao smsSalvar = new SMSValidacao();
		smsSalvar.setCodigoValidacao(codigo);
		smsSalvar.setTelefone(sms.getTelefone());
		smsSalvar.setValidado(false);
		
		SMSValidacao s=smsDao.save(smsSalvar);
		s.getId();
		return  codigoFormatado;
	}
	
	@PostMapping("/validar")
	public boolean validaCodigo(@RequestBody SMSValidacao sms)
	{
		boolean resultado=false;
		SMSValidacao smsSalvo = smsDao.findByTelefone(sms.getTelefone());
		
		if(smsSalvo!=null)
		{
			if(!smsSalvo.isValidado())
			{
				if(smsSalvo.getCodigoValidacao().equals(sms.getCodigoValidacao()))
				{
					resultado=true;
					smsSalvo.setValidado(true);
					smsDao.save(smsSalvo);
				}
			}
		}else 
			resultado= false;
		
		return resultado;
	}
	
	private String geraCodigo(int len) {
        char[] chart = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] senha = new char[len];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < len; x++) {
            senha[x] = chart[rdm.nextInt(chartLenght)];
        }
        return new String(senha);
    }

}
