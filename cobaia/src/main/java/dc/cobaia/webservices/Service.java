package dc.cobaia.webservices;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Service implements Route {
		
	public Object handle(Request request, Response response) throws Exception {
		response.header("Content-Type", "application/json");
		response.header("Access-Control-Allow-Origin", "*");
		if (request.requestMethod().equals("POST")) {
			return this.post(Post.data(request, response));
		} 
		if (request.requestMethod().equals("GET")) {
			return this.get(Get.data(request, response));
		} 
		response.status(405);
		return "Metodo nao permitido";
	}

	protected Object get(Get data) {
		data.response.status(204);
		return "";
	}

	protected Object post(Post data) {
		data.response.status(204);
		return "";
	}
	
}
