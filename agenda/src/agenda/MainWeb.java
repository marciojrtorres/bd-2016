package agenda;

import spark.*;
import web.JsonTransformer;
import web.RouteContatosAll;

public class MainWeb {

	public static void main(String[] args) {
		
		JsonTransformer transf = new JsonTransformer();
		
		RouteContatosAll contatos = new RouteContatosAll();
		
		Spark.get("/contatos", "application/json",
				  contatos, transf);		
		
	}
}







