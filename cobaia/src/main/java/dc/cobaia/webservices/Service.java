package dc.cobaia.webservices;

import spark.Request;
import spark.Response;
import spark.Route;

public abstract class Service implements Route {
		
	public abstract Object answer(Request req, Response resp);
	
	public Object handle(Request request, Response response) throws Exception {
		response.header("Content-Type", "application/json");
		response.header("Access-Control-Allow-Origin", "*");
		return answer(request, response);
	}
	
}
