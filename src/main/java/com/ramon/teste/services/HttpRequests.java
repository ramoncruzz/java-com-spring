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
//		StringEntity param = new StringEntity(json.toString());
//		httppost.setEntity(param);
		httppost.setEntity(new StringEntity(json.toString(),charset));

		HttpResponse response = httpclient.execute(httppost);
		
		if(response.getStatusLine().getStatusCode()==200)
			return HttpStatus.OK;
		else
			return HttpStatus.INTERNAL_SERVER_ERROR;
		
	}
	
	public int method() throws ClientProtocolException, IOException, JSONException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("https://fcm.googleapis.com/fcm/send");
		httppost.addHeader("Authorization",
				"key=AAAApejE2J8:APA91bHHpo_ILaY9fs2dWG2JBAQKDyAkg2Qu-Cd0xh9SH_BGRHBwrpbYnfpLWuqEZQuaMB0X3s1w-ys9nqmqG5btT7wqvUTQ2-iBkUAEE3rAp-aBSs4w4r7VPcpUbZxs077ZhafuXNxX");
		httppost.addHeader("Content-Type", "application/json");

		
		// // Request parameters and other properties.
		// List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		// params.add(new BasicNameValuePair("param-1", "12345"));
		// params.add(new BasicNameValuePair("param-2", "Hello!"));
		//
		//
		// httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();

		data.put("title", "Deu certooooooooo!!");
		data.put("message", "mensagem automatica");

		json.put("to",
				"fiZnAZtFBys:APA91bGxwT-AAAE8_zurVn85vYXC2nsmnkBQVY3nfnmiUrMITv1y37AfOt6y7p-l6QgvElovUsID0MFOOwsjp3QZ-0ku6PytXGlToHQnyKC3O0Tt1H-k4CDi6790pTHj7CF6-D-9oqlg");
		json.put("data", data);
		StringEntity param = new StringEntity(json.toString());
		httppost.setEntity(param);

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		
		return response.getStatusLine().getStatusCode();
		
	}

}
