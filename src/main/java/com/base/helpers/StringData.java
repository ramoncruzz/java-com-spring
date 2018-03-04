package com.base.helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class StringData {

	public static String getStringData() {
		ZoneId sp = ZoneId.of("America/Sao_Paulo");
		int ano = LocalDateTime.now(sp).getYear();
		int mes = LocalDateTime.now(sp).getMonth().getValue();
		int dia = LocalDateTime.now(sp).getDayOfMonth();
		int hora = LocalDateTime.now(sp).getHour();
		int minuto = LocalDateTime.now(sp).getMinute();

		String data = "" + ((dia < 10) ? "0" + dia : dia) + "/" + ((mes < 10) ? "0" + mes : mes) + "/"
				+ ((ano < 10) ? "0" + ano : ano) + "-" + ((hora < 10) ? "0" + hora : hora) + ":"
				+ ((minuto < 10) ? "0" + minuto : minuto);

		return data;
	}

	public static String getDataHoraNumeros() {
		ZoneId sp = ZoneId.of("America/Sao_Paulo");
		int ano = LocalDateTime.now(sp).getYear();
		int mes = LocalDateTime.now(sp).getMonth().getValue();
		int dia = LocalDateTime.now(sp).getDayOfMonth();
		int hora = LocalDateTime.now(sp).getHour();
		int minuto = LocalDateTime.now(sp).getMinute();

		String data = "" + ((dia < 10) ? "0" + dia : dia) + ((mes < 10) ? "0" + mes : mes)
				+ ((ano < 10) ? "0" + ano : ano) + ((hora < 10) ? "0" + hora : hora)
				+ ((minuto < 10) ? "0" + minuto : minuto);
		return data;
	}

	public static String getValorSemDataHora(String dataHora) {

		return dataHora.substring(12, dataHora.length());
	}

}
