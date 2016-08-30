package web;

import agenda.dao.ContatoDAO;
import spark.*;

public class RouteContatosAll implements Route {

	private ContatoDAO dao = new ContatoDAO();
	
	@Override
	public Object handle(Request req, Response resp) throws Exception {
		// está voltando um json
		resp.header("Content-Type", "application/json");
		// permitir o AJAX
		resp.header("Access-Control-Allow-Origin", "*");
		return dao.selectAll();
	}
}







