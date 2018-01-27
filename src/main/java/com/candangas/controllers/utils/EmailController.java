package com.candangas.controllers.utils;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.candangas.DAO.util.EmailValidacaoDAO;
import com.candangas.model.util.EmailValidacao;

@RestController
@RequestMapping("/v0/email")
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailValidacaoDAO emailValidacaoDao;
	
	@Value("${spring.mail.username}")
	private String emailRementente;
	
	@PostMapping("/gerar-codigo")
	private String gerarCodigo(@RequestBody EmailValidacao emailValidacao)
	{
		String codigo = geraCodigo(6);
		String codigoFormatado = "Olá, Seu código  do App Candagas é " + codigo.substring(0, 3) + " " + codigo.substring(3) + " ";

		EmailValidacao emailSalvar = new EmailValidacao();
		emailSalvar.setCodigoValidacao(codigo);
		emailSalvar.setEmail(emailValidacao.getEmail());
		emailSalvar.setValidado(false);

		EmailValidacao s = emailValidacaoDao.save(emailSalvar);
		s.getId();
		enviaEmail(emailValidacao.getEmail(),"Código de Validação Candangas",codigoFormatado);
		return codigoFormatado;
	}
	
	@PostMapping("/validar-codigo")
	private boolean validarCodigo(@RequestBody EmailValidacao emailValidacao)
	{
		boolean resultado = false;
		List<EmailValidacao> lista = emailValidacaoDao.findByEmail(emailValidacao.getEmail());
		EmailValidacao emoilSalve = lista.get(lista.size() - 1);

		if (emoilSalve != null) {
			if (!emoilSalve.isValidado()) {
				if (emoilSalve.getCodigoValidacao().equals(emailValidacao.getCodigoValidacao())) {
					resultado = true;
					emoilSalve.setValidado(true);
					emailValidacaoDao.save(emoilSalve);
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
	
	private void enviaEmail(String destinatario, String titulo, String mensagem)
	{
		SimpleMailMessage email = new SimpleMailMessage();

		email.setText(mensagem);
		email.setTo(destinatario);
		email.setFrom(emailRementente);
		email.setSubject(titulo);
        mailSender.send(email);
        
	}
	
}