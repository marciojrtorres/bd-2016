package web;

import agenda.dao.ContatoDAO;
import spark.Request;
import spark.Response;

public class ContatosWebService extends WebService {

	private ContatoDAO dao = new ContatoDAO();
	
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
		
	
	
	
	
}







