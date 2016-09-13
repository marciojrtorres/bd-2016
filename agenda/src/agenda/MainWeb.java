package agenda;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import web.ContatosWebService;

public class MainWeb {

	public static void main(String[] args) {
		
		ContatosWebService ws = 
				new ContatosWebService();
		
		// http://localhost:4567/contatos
		Spark.get("/contatos", ws.contentType, 
				ws.selectAll, ws.responseTransformer);
		
		// http://localhost:4567/contatos/1
		Spark.get("/contatos/:id", ws.contentType,
				ws.select, ws.responseTransformer);
		
		Spark.post("/contatos", ws.insert);
/*
contato="{nome: "Heitor", sobrenome: "Godoi",
 telefones: [
 {ddd:"53", numero:"88333333"},
 {ddd:"53", numero:"88343443"}
]}"

*/

		
		
		
		
		
		
		
		
	}
}







