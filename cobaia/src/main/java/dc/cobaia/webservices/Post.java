package dc.cobaia.webservices;

import com.google.gson.Gson;

import dc.cobaia.model.Contato;
import spark.Request;
import spark.Response;

public class Post {

	private Gson gson = new Gson();
	
	public final Response response;
	public final Request request;

	private Post(Request request, Response response) {
		this.request = request;
		this.response = response;
	}

	public static Post data(Request request, Response response) {
		return new Post(request, response);
	}

	public <T> T decode(Class<T> clazz) {
		return gson.fromJson(request.queryParams("data"), clazz);
	}
	
	

}
