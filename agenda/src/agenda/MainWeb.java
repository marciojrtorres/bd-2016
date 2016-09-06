package agenda;

import spark.Spark;
import web.ContatosWebService;

public class MainWeb {

	public static void main(String[] args) {
		
		ContatosWebService ws = 
				new ContatosWebService();
		
		Spark.get("/contatos", ws.contentType, 
				ws.selectAll, ws.responseTransformer);
		
		Spark.get("/contatos/:id", ws.contentType,
				ws.select, ws.responseTransformer);
		
		
	}
}







