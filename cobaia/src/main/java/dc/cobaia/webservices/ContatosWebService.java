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
		public Object get(Get data) {
			List<Contato> contatos = new ArrayList<>();
			contatos.add(new Contato("a"));
			contatos.add(new Contato("b"));
			return contatos;
		}
	};
	
	public final Service nada = new Service() {
		
		@Override
		public Object post(Post data) {
			Contato c = data.decode(Contato.class);
			return "";
		}
	};

}
