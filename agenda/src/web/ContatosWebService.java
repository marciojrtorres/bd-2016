package web;

import com.google.gson.Gson;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;
import spark.Request;
import spark.Response;
import spark.Route;

public class ContatosWebService extends WebService {

	private ContatoDAO dao = new ContatoDAO();
	private Gson gson = new Gson();
	
	public ContatosWebService() {
		super("application/json", new JsonTransformer());
	}
	
	public final Service selectAll = new Service() {
		@Override
		public Object deal(Request request, Response response) throws Exception {
			return dao.selectAll();
		}
	};

	// por ID
	// http://localhost/contatos/:id
	// http://localhost/contatos/3
	public final Service select = new Service() {
		@Override
		public Object deal(Request request, Response response) throws Exception {
			int id = Integer.parseInt(request.params("id"));
			return dao.select(id);
		}
	};
	
	// cada ação é um Service do WebService
	public final Service delete = new Service() {
		
		@Override
		public Object deal(Request request, Response response) throws Exception {
			// dao.delete(c)
			// TODO Auto-generated method stub
			return "";
		}
	};
// AJAX: assincronous javascript and xml
	public final Service insert = new Service() {
		
		@Override
		public Object deal(Request request, Response response) throws Exception {
			String json = request.body();			
			// System.out.println(json);
			Contato contato = 
					gson.fromJson(json, Contato.class);
			dao.insert(contato);
			return "";
		}
	};
	
	
}







