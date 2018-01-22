package com.candangas.helpers;

public class ErrosMensagens {
	
	public static String erroMensagem(String titulo, String mensagem)
	{
		StringBuilder erro = new StringBuilder();
		erro.append("{");
		erro.append("\""+titulo+"\",");
		erro.append("\""+mensagem+"\"}");
		return erro.toString();
	}

}
