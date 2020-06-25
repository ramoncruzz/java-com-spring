package com.base.helpers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JsonString {
	
	public static String geraJsonString(Object o) {
		try {
			Class<?> classe = o.getClass();
			StringBuilder jsonString = new StringBuilder("{");
			jsonString.append(constroiParametro("success", true));
			jsonString.append(",");
			
			List<Method> listaMetodosComAtributoJson = constroiListaMetodosComAtributoJson(classe);
			if(listaMetodosComAtributoJson.isEmpty())
			{
				return jsonErroMensagem("Classe "+o.getClass().getSimpleName()+" não possui atributos habilitados.");
			}
			else
			{
				int tamanho = contaQtdGetValidos(o);
				int contador = 1;
				for (Method m : constroiListaMetodosComAtributoJson(classe)) {

					if (m.isAnnotationPresent(AtributoJson.class))
						jsonString.append(constroiParametro(deGetterParaPropriedade(m.getName()), m.invoke(o)));

					if (contador < tamanho)
						jsonString.append(",");

					contador++;

				}
			}
			jsonString.append("}");
			return jsonString.toString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Problema ao gerar jsonString", e);
		}
	}
	
	public static String geraJsonArray(Object o) {
		
		@SuppressWarnings("unchecked")
		List<Object> lista = (List<Object>) o;
		if(lista.isEmpty())
			return jsonErroMensagem("Erro ao tentar gerar JsonArray");
		else
		{
			String simpleName=lista.get(0).getClass().getSimpleName();
			StringBuilder nomeParamentro = new StringBuilder();
			
			StringBuilder jsonString = new StringBuilder("{");
			jsonString.append(constroiParametro("success", true));
			jsonString.append(",");
			
			nomeParamentro.append(simpleName.substring(0, 1).toLowerCase());
			nomeParamentro.append(simpleName.substring(1));
			
			jsonString.append(constroiParametro(nomeParamentro.toString(), lista));

			jsonString.append("}");

			return jsonString.toString();
		}
		
	}

	public static String geraJsonCreatedUpdated(Long id) {

		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"success\":");
		jsonString.append(true);
		jsonString.append(",\"Id\":");
		jsonString.append(id);
		jsonString.append("}");
		return jsonString.toString();
	}

	public static String geraJsonOK() {

		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"success\":");
		jsonString.append(true);
		jsonString.append("}");
		return jsonString.toString();
	}

	public static String geraJsonOKSMS(String mensagem) {

		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"success\":");
		jsonString.append(true);
		jsonString.append(",\"codigoSMS\":\"");
		jsonString.append(mensagem);
		jsonString.append("\"}");

		return jsonString.toString();
	}

	public static String geraJsonOKWithMensage(String mensagem) {

		StringBuilder jsonString = new StringBuilder("{");
		jsonString.append("\"success\":");
		jsonString.append(true);
		jsonString.append(",\"msg\":\"");
		jsonString.append(mensagem);
		jsonString.append("\"}");

		return jsonString.toString();
	}

	public static String jsonErroMensagem(String mensagem) {
		StringBuilder erro = new StringBuilder();
		erro.append("{");
		erro.append("\"success\":");
		erro.append(false);
		erro.append(",\"erro\":\"");
		erro.append(mensagem);
		erro.append("\"}");
		return erro.toString();
	}
	
	private static String geraJsonStringInterno(Object o) {
		
		try {
			Class<?> classe = o.getClass();
			StringBuilder jsonString = new StringBuilder("{");
			
			int tamanho = contaQtdGetValidos(o);
			int contador = 1;

			for (Method m : constroiListaMetodosComAtributoJson(classe)) {

				if (m.isAnnotationPresent(AtributoJson.class))
					jsonString.append(constroiParametro(deGetterParaPropriedade(m.getName()), m.invoke(o)));

				if (contador < tamanho)
					jsonString.append(",");

				contador++;
			}
			jsonString.append("}");
			return jsonString.toString();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("Problema ao gerar jsonString", e);
		}
	}

	@SuppressWarnings("unused")
	private static boolean isListReturn(Method m) {
		return m.getName().startsWith("get") && m.getReturnType() == List.class && m.getParameterTypes().length == 0;
	}

	private static boolean isGetter(Method m) {
		return (m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getReturnType() != void.class
				&& m.getParameterTypes().length == 0 && (m.isAnnotationPresent(AtributoJson.class));
	}

	private static int contaQtdGetValidos(Object o) {
		Class<?> classe = o.getClass();
		int tamanho = 0;
		for (Method m : classe.getMethods()) {
			if (isGetter(m))
				tamanho++;
		}
		return tamanho;
	}

	private static String deGetterParaPropriedade(String nomeGetter) {

		StringBuffer retorno = new StringBuffer();
		String nome = null;

		if (nomeGetter.startsWith("is")) {
			nome = nomeGetter.substring(2);
			retorno.append(nome.substring(0, 1).toLowerCase());
			retorno.append(nome.substring(1));

		} else {
			nome = nomeGetter.substring(3);
			retorno.append(nome.substring(0, 1).toLowerCase());
			retorno.append(nome.substring(1));

		}

		return retorno.toString();
	}

	public static String constroiParametro(String chave, String valor) {
		boolean aspas = (valor != null);

		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");
		if (aspas)
			jsonString.append("\"");
		jsonString.append(valor);
		if (aspas)
			jsonString.append("\"");

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, boolean valor) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, double valor) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, int valor) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, float valor) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		jsonString.append(valor);

		return jsonString.toString();
	}
	
	public static String constroiParametro(String chave, long valor) {
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, Object valor) {

		if (valor != null) {
			Class<?> classe = valor.getClass();
			if (classe == Integer.class) {
				int p = (int) valor;
				return constroiParametro(chave, p);
			}

			if (classe == Double.class) {
				double p = (double) valor;
				return constroiParametro(chave, p);
			}

			if (classe == String.class) {
				String p = (String) valor;
				return constroiParametro(chave, p);
			}

			if (classe == Long.class) {
				long p = (long) valor;
				return constroiParametro(chave, p);
			}
			
			if (classe == Float.class) {
				float p = (float) valor;
				return constroiParametro(chave, p);
			}

			if (classe == Boolean.class) {
				boolean p = (boolean) valor;
				return constroiParametro(chave, p);
			}

			if (classe == List.class) {
				java.awt.List p = (java.awt.List) valor;
				return constroiParametro(chave, p);
			}
			
			if(classe==org.hibernate.collection.internal.PersistentBag.class)
			{
				
				org.hibernate.collection.internal.PersistentBag valorBag=((org.hibernate.collection.internal.PersistentBag) valor);
				ArrayList<Object> p =new ArrayList<>();
				for(Object obj:valorBag.toArray())
					p.add(obj);
				
				return constroiParametro(chave, p);
			}
			if (classe == ArrayList.class) {
				@SuppressWarnings("unchecked")
				ArrayList<Object> p = (ArrayList<Object>) valor;
				return constroiParametro(chave, p);
			}
			boolean objJson=isObjetoJsonResposta(valor);
			if(objJson)
			{
				StringBuilder jsonString = new StringBuilder();
				jsonString.append("\"");
				jsonString.append(chave);
				jsonString.append("\"");
				jsonString.append(":");
				jsonString.append(geraJsonStringInterno(valor));
				return jsonString.toString();
			}
		}

		boolean aspas = (valor != null);
		StringBuilder jsonString = new StringBuilder();
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");

		if (aspas)
			jsonString.append("\"");
		jsonString.append(valor);
		if (aspas)
			jsonString.append("\"");

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, List<Object> valor) {

		StringBuilder jsonString = new StringBuilder();
		int indexLast = valor.size() - 1;
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");
		jsonString.append("[");
		for (int i = 0; i < valor.size(); i++) {
			jsonString.append(constroiParametro(valor.get(i)));
			if (i != indexLast)
				jsonString.append(",");
		}

		jsonString.append("]");

		return jsonString.toString();
	}

	public static String constroiParametro(String chave, ArrayList<Object> valor) {

		StringBuilder jsonString = new StringBuilder();
		int indexLast = valor.size() - 1;
		jsonString.append("\"");
		jsonString.append(chave);
		jsonString.append("\"");
		jsonString.append(":");
		jsonString.append("[");
		for (int i = 0; i < valor.size(); i++) {
			jsonString.append(constroiParametro(valor.get(i)));
			if (i != indexLast)
				jsonString.append(",");
		}
		
		jsonString.append("]");

		return jsonString.toString();
	}

	public static String constroiParametro(String valor) {
		boolean aspas = (valor != null);
		StringBuilder jsonString = new StringBuilder();
		if (aspas)
			jsonString.append("\"");
		jsonString.append(valor);
		if (aspas)
			jsonString.append("\"");

		return jsonString.toString();
	}

	public static String constroiParametro(boolean valor) {
		StringBuilder jsonString = new StringBuilder();

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(double valor) {
		StringBuilder jsonString = new StringBuilder();

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(int valor) {
		StringBuilder jsonString = new StringBuilder();

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(float valor) {
		StringBuilder jsonString = new StringBuilder();

		jsonString.append(valor);

		return jsonString.toString();
	}
	
	public static String constroiParametro(long valor) {
		StringBuilder jsonString = new StringBuilder();

		jsonString.append(valor);

		return jsonString.toString();
	}

	public static String constroiParametro(Object valor) {
		
		if (valor != null) {
			Class<?> classe = valor.getClass();
			if (classe == Integer.class) {
				int p = (int) valor;
				return constroiParametro(p);
			}

			if (classe == Double.class) {
				double p = (double) valor;
				return constroiParametro(p);
			}

			if (classe == String.class) {
				String p = (String) valor;
				return constroiParametro(p);
			}

			if (classe == Long.class) {
				long p = (long) valor;
				return constroiParametro(p);
			}

			if (classe == Boolean.class) {
				boolean p = (boolean) valor;
				return constroiParametro(p);
			}

			if (classe == List.class) {
				java.awt.List p = (java.awt.List) valor;
				return constroiParametro(p);
			}
			
			if(isObjetoJsonResposta(valor))
			{
				return geraJsonStringInterno(valor);
			}
		}
		
		boolean aspas = (valor != null);
		StringBuilder jsonString = new StringBuilder();

		if (aspas)
			jsonString.append("\"");
		jsonString.append(valor);
		if (aspas)
			jsonString.append("\"");

		return jsonString.toString();
	}

	public static List<Method> constroiListaMetodosComAtributoJson(Class<?> classe) {

		ArrayList<Method> lista = new ArrayList<>();
		for (Method m : classe.getMethods()) {
			if (isGetter(m)) {
				if (m.isAnnotationPresent(AtributoJson.class)) {
					lista.add(m);
				}
			}
		}
		return lista;
	}

	public static boolean isObjetoJsonResposta(Object obj) {
		Class<?> classe = obj.getClass();
		List<Method> lista = constroiListaMetodosComAtributoJson(classe);
		if (lista.isEmpty())
			return false;
		else
			return true;

	}
}
