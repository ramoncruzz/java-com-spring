package com.candangas.helpers;

import java.lang.reflect.Method;
import java.util.List;

public class JsonString {

	public static String geraJsonString(Object o)
	{
		try
		{
			Class<?> classe = o.getClass();
			StringBuilder jsonString = new StringBuilder("{");
			jsonString.append("\"sucess\":");
			jsonString.append(true);
			jsonString.append(",");
			int tamanho = contaQtdGetValidos(o);
			int contador=1;
			
			for(Method m : classe.getMethods())
			{
					if(isGetter(m))
					{
						if(m.isAnnotationPresent(AtributoJson.class))
						{
							if(m.getReturnType()==String.class && m.invoke(o)!=null)
							{
								jsonString.append("\"");
								jsonString.append(deGetterParaPropriedade(m.getName()));
								jsonString.append("\":\"");
								jsonString.append(m.invoke(o));
								jsonString.append("\"");
							}else
							{
								jsonString.append("\"");
								jsonString.append(deGetterParaPropriedade(m.getName()));
								jsonString.append("\":");
								jsonString.append(m.invoke(o));
								
							}
						}
						if(contador<tamanho)
							jsonString.append(",");
						contador++;
						
					}
					if(isListReturn(m))
					{
						jsonString.append(",\"");
						jsonString.append(deGetterParaPropriedade(m.getName()));
						jsonString.append("\":");
						jsonString.append("[");
						
						@SuppressWarnings("unchecked")
						List<Object> lista =(List<Object>) m.invoke(o);
						for(int i=0;i<lista.size();i++)
						{
							jsonString.append(geraJsonString(lista.get(i)));
							if((i+1)<lista.size())
								jsonString.append(",");
						}
						jsonString.append("]");
						if(contador<=tamanho)
							jsonString.append(",");
						
					}
			}
			jsonString.append("}");
			return jsonString.toString().replace(",,", ",");
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Problema ao gerar jsonString",e);
		}
	}
	
	public static String geraJsonArray(Object o)
	{
		StringBuilder jsonString = new StringBuilder("{");
		@SuppressWarnings("unchecked")
		List<Object> lista =(List<Object>)o;
		jsonString.append("\"sucess\":");
		jsonString.append(true);
		jsonString.append(",\"");
		jsonString.append(lista.get(0).getClass().getSimpleName());
		jsonString.append("\":[");
		
		for(int i=0;i<lista.size();i++)
		{
			jsonString.append(geraJsonString(lista.get(i)));
			if((i+1)<lista.size())
				jsonString.append(",");
		}
			
		jsonString.append("]");
		jsonString.append("}");
		
		return jsonString.toString();
	}
	
	public static String geraJsonCreatedUpdated(Long id) {
		
		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"sucess\":");
		jsonString.append(true);
		jsonString.append(",\"Id\":");
		jsonString.append(id);
		jsonString.append("}");
		return jsonString.toString();
	}
	 
public static String geraJsonOK() {
		
		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"sucess\":");
		jsonString.append(true);
		jsonString.append("}");
		return jsonString.toString();
	}
public static String geraJsonOKWithMensage(String mensagem) {
	
	StringBuilder jsonString = new StringBuilder("{");
	jsonString.append("\"sucess\":");
	jsonString.append(true);
	jsonString.append(",\"codigoSMS\":\"");
	jsonString.append(mensagem);
	jsonString.append("\"}");
	
	return jsonString.toString();
}
	public static String jsonErroMensagem(String mensagem)
	{
		StringBuilder erro = new StringBuilder();
		erro.append("{");
		erro.append("\"sucess\":");
		erro.append(false);
		erro.append(",\"erro\":\"");
		erro.append(mensagem);
		erro.append("\"}");
		return erro.toString();
	}
	
	private static boolean isListReturn(Method m)
	{
		return  m.getName().startsWith("get")&&
				m.getReturnType()==List.class &&
			    m.getParameterTypes().length == 0 &&
	           (m.isAnnotationPresent(AtributoJson.class));
	}
	private static boolean isGetter(Method m) {
        return (m.getName().startsWith("get")||m.getName().startsWith("is")) &&
            m.getReturnType() != void.class &&
            m.getReturnType()!=List.class &&
            m.getParameterTypes().length == 0 &&
            (m.isAnnotationPresent(AtributoJson.class));
    }
	private static int contaQtdGetValidos(Object o)
	{
		Class<?> classe = o.getClass();
		int tamanho = 0;
		for(Method m : classe.getMethods())
		{
			if(isGetter(m))
				tamanho++;
		}
		return tamanho;
	}
    private static String deGetterParaPropriedade(String nomeGetter){
        StringBuffer retorno = new StringBuffer();
        if(nomeGetter.contains("is"))
        {
        	    retorno.append(nomeGetter.substring(2, 4).toLowerCase());
            retorno.append(nomeGetter.substring(4));
        }else
        {
          	retorno.append(nomeGetter.substring(3, 4).toLowerCase());
            retorno.append(nomeGetter.substring(4));
        }
        
        return retorno.toString();
}
}
