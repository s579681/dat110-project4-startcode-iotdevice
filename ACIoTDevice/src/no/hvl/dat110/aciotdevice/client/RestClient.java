package no.hvl.dat110.aciotdevice.client;
import okhttp3.*;

import java.io.IOException;

import com.google.gson.Gson;
public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public void doPostAccessEntry(String message) {
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();
		AccessMessage msg = new AccessMessage(message);
		RequestBody body = RequestBody.create(JSON, gson.toJson(msg));
		Request req = new Request.Builder().url("http://localhost:8080" + logpath).post(body).build();
		System.out.println(req);
		
		try (Response response = client.newCall(req).execute()) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {
		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		OkHttpClient client = new OkHttpClient();
		Gson gson = new Gson();

		Request req = new Request.Builder().url("http://localhost:8080" + codepath).get().build();

		System.out.println(req);

		try (Response response = client.newCall(req).execute()) {
			String body = response.body().string();
			System.out.println(body);
			code = gson.fromJson(body, AccessCode.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;
	}
}
