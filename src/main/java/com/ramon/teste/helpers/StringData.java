package com.ramon.teste.helpers;

import java.util.Calendar;

public class StringData {

	 public static String getStringData()
	    {
	        int ano = Calendar.getInstance().get(Calendar.YEAR);
	        int mes = Calendar.getInstance().get(Calendar.MONTH)+1;
	        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	        int hora = Calendar.getInstance().get(Calendar.HOUR);
	        int minuto = Calendar.getInstance().get(Calendar.MINUTE);
	        
	        String data = ""+((dia<10)?"0"+dia:dia)+"/"+((mes<10)?"0"+mes:mes)+"/"+((ano<10)?"0"+ano:ano)+"-"+((hora<10)?"0"+hora:hora)+":"+((minuto<10)?"0"+minuto:minuto);
	        
	        return data;
	    }
	
}
