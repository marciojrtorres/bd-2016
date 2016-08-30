package dc.cobaia;

import dc.cobaia.webservices.ContatosWebService;
import spark.Spark;

public class Main {
	public static void main(String[] args) {

		// MustacheTemplateEngine engine =
		// new MustacheTemplateEngine("web/htmls");

		// JsonTransformer tranformer = new JsonTransformer();

		// Spark.staticFileLocation("/web/assets");

		// ContatosController contatos = new ContatosController();

		ContatosWebService contatosWS = new ContatosWebService();

		Spark.get("/contatos", contatosWS.contentType, 
				contatosWS.contatos, contatosWS.transformer);
		
		Spark.get("/nada", contatosWS.contentType, 
				contatosWS.nada, contatosWS.transformer);

	}
}
