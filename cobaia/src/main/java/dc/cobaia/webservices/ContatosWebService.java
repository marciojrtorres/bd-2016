package dc.cobaia.webservices;

import java.util.ArrayList;
import java.util.List;

import dc.cobaia.model.Contato;
import dc.cobaia.util.JsonTransformer;
import spark.Request;
import spark.Response;

public class ContatosWebService extends WebService {
		
	public ContatosWebService() {
		super("application/json", new JsonTransformer());
	}

	public final Service contatos = new Service() {
		
		@Override
		public Object answer(Request req, Response resp) {
			List<Contato> contatos = new ArrayList<>();
			contatos.add(new Contato("a"));
			contatos.add(new Contato("b"));
			return contatos;
		}
	};
	
	public final Service nada = new Service() {
		
		@Override
		public Object answer(Request req, Response resp) {
			resp.status(401);
			return "";
		}
	};

}
