package dc.cobaia;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class Main {
	public static void main(String[] args) {
		
		Spark.get("/teste", new Route() {
			
			@Override
			public Object handle(Request request, Response response) throws Exception {
				int x = 3 / 1;
				throw new RuntimeException();
				//return x;
			}
		});
		// MustacheTemplateEngine engine =
		// new MustacheTemplateEngine("web/htmls");

		// JsonTransformer tranformer = new JsonTransformer();

		// Spark.staticFileLocation("/web/assets");

		// ContatosController contatos = new ContatosController();

		/*
		ContatosWebService contatosWS = new ContatosWebService();

		Spark.get("/contatos", contatosWS.contentType, 
				contatosWS.contatos, contatosWS.transformer);
		
		Spark.get("/nada", contatosWS.contentType, 
				contatosWS.nada, contatosWS.transformer);
		*/
		
//		String c = "{nome: 'Marcio'}";
//		Gson g = new Gson();
//		Contato contato = g.fromJson(c, Contato.class);
//		System.out.println(contato.getNome());
	}
}
