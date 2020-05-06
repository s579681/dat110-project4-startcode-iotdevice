package no.hvl.dat110.aciotdevice.client;
import okhttp3.*;

import java.io.IOException;

import com.google.gson.Gson;
public class RestClient {

	public RestClient() {
	}

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
	
		Gson gson = new Gson();											//Lager Gson objekt
		AccessMessage msg = new AccessMessage(message);					//Oppretter beskjeden som skal sendes
		RequestBody body = RequestBody.create(JSON, gson.toJson(msg));  //Oppretter HTTP body med message som GSON og konverterer til JSON.
																		//Oppretter GET Request for retur av log
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("http://localhost:8080/accessdevice/log/").post(body).build();
		try (Response response = client.newCall(request).execute()) {	//Forsøker å opprette en tilsvarende response basert på Request
			System.out.println(response.body().string());				//Print ut Response i konsoll
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public AccessCode doGetAccessCode() {
		AccessCode code = null;
		// TODO: implement a HTTP GET on the service to get current access code
		Request request = new Request.Builder().url("http://localhost:8080/accessdevice/code").get().build();
		
		OkHttpClient client = new OkHttpClient();							// Lager gson obj.
		Gson gson = new Gson();												//Oppretter Request til ../code
											//Forsøker å opprette response til HTTP GET basert på request til */code
		try (Response response = client.newCall(request).execute()) {
			String body = response.body().string();							//Lagrer Rquest i String for lett print
			System.out.println(body);										//Printer
			code = gson.fromJson(body, AccessCode.class);					//Gjør om koden i response fra json til gson
		} catch (IOException e) {
			e.printStackTrace();
		}
		return code;
	}
}

