package com.ramon.teste.services;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

public class HttpRequests {

	private String urlFCM = "https://fcm.googleapis.com/fcm/send";
	
	public HttpStatus notificaUsuario(String tokenServidor,String tokenUsuario,String tituloMensagem,String mensagem)throws ClientProtocolException, IOException, JSONException 
	{
		String charset = "UTF-8";
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(urlFCM);
		httppost.addHeader("Authorization","key="+tokenServidor);
		httppost.addHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();

		data.put("title", tituloMensagem);
		data.put("message", mensagem);

		json.put("to",tokenUsuario);
		json.put("data", data);

		httppost.setEntity(new StringEntity(json.toString(),charset));
		HttpResponse response = httpclient.execute(httppost);
		
		if(response.getStatusLine().getStatusCode()==200)
			return HttpStatus.OK;
		else
			return HttpStatus.INTERNAL_SERVER_ERROR;
		
	}
	
	public HttpStatus notificaUsuario(String tokenServidor,String tokenUsuario,String tituloMensagem,String mensagem,String chaveParamentro,String ConteudoParametro)throws ClientProtocolException, IOException, JSONException 
	{
		String charset = "UTF-8";
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(urlFCM);
		httppost.addHeader("Authorization","key="+tokenServidor);
		httppost.addHeader("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();

		data.put("title", tituloMensagem);
		data.put("message", mensagem);

		json.put("to",tokenUsuario);
		json.put("data", data);

		httppost.setEntity(new StringEntity(json.toString(),charset));
		HttpResponse response = httpclient.execute(httppost);
		
		if(response.getStatusLine().getStatusCode()==200)
			return HttpStatus.OK;
		else
			return HttpStatus.INTERNAL_SERVER_ERROR;
		
	}
	

}
