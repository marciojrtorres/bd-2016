package dc.cobaia.webservices;

import spark.Request;
import spark.Response;

public class Get {

	public final Response response;
	public final Request request;

	private Get(Request request, Response response) {
		this.request = request;
		this.response = response;
	}

	public static Get data(Request request, Response response) {
		return new Get(request, response);
	}

}
