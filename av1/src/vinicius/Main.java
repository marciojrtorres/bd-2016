package vinicius;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vinicius.dao.*;
import vinicius.model.*;

public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		/*Cliente cliente1 = new Cliente();
		
		cliente1.setNome("Jo�o");
		cliente1.setSobrenome("Almeida");
		cliente1.setCidade("Porto Alegre");
		cliente1.setUf("RS");
		cliente1.setCep("96212-000");
		
		Cliente cliente2 = new Cliente();
		
		cliente2.setNome("Ibere");
		cliente2.setSobrenome("Then�rio");
		cliente2.setCidade("S�o Paulo");
		cliente2.setUf("SP");
		cliente2.setCep("84250-000");
		
		ClienteDAO dao = new ClienteDAO();
		
		dao.insert(cliente1);
		dao.insert(cliente2);
		
		cliente2.setNome("carlinhos");
		
		System.out.print(dao.update(cliente2));
		
		System.out.println(dao.delete(cliente2));
		
		VendedorDAO vend_dao = new VendedorDAO();
		Vendedor vendedor1 = new Vendedor();
		
		vendedor1 = vend_dao.select(3);
		
		vend_dao.insert(vendedor1);
		
		vend_dao.delete(5);
		
		ProdutoDAO prod_dao = new ProdutoDAO();
		
		prod_dao.insert(prod_dao.select(3));
		
		System.out.println(prod_dao.selectPage(2));*/
		
		
		/* TESTANDO PEDIDO-DAO*/
		PedidoDAO ped_dao = new PedidoDAO();	
		
		Pedido pedido = new Pedido();
		
		pedido.setCod_cli(2);
		pedido.setStatus(Status_ped.Entregue);
		pedido.setCod_vend(3);
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			pedido.setData_pedido(dateFormat.parse("2016-05-05"));
			pedido.setData_entrega(dateFormat.parse("2016-05-10"));
		} catch (ParseException e) {
			throw new RuntimeException("Datas invalidas");
		}
		pedido.setFrete(50.00);
		
		System.out.println(ped_dao.selectAll());
		
		ped_dao.delete(23);
		
		System.out.println(ped_dao.selectAll());
		
		ped_dao.insert(pedido);
		
		pedido.setFrete(300.00);
		
		ped_dao.update(pedido);
		
		System.out.println(ped_dao.selectPage(3));
		
		System.out.println(ped_dao.selectByYear(2016));
		/*FIM TESTANDO PEDIDO-DAO*/
		
		/* TESTANDO CLIENTE-DAO*/
		
		Cliente cliente = new Cliente("Marcos", "Almeida", "Uberl�ndia", "MG", "85240-000");
		
		ClienteDAO cli_dao = new ClienteDAO();
		
		System.out.println(cli_dao.selectAll()); // antes do 1ro insert
		
		cli_dao.insert(cliente);
		
		System.out.println(cli_dao.selectAll()); // depois do 1ro insert
		
		Cliente cliente2 = cli_dao.select(1);
		
		cli_dao.insert(cliente2);
		
		System.out.println(cli_dao.selectAll()); // depois do 2ndo insert
		
		cli_dao.delete(97);
		
		System.out.println(cli_dao.selectAll()); 
		
		cliente2.setCidade("Westphalen");
		cli_dao.update(cliente2);
		
		System.out.println(cli_dao.selectAll());
		
		System.out.println(cli_dao.selectPage(3));
		
		System.out.println(cli_dao.selectByCidade("PORTO ALEGRE"));
		
		/*FIM TESTANDO CLIENTE-DAO*/
		
		/* TESTANDO PRODUTO-DAO*/
		
		Produto produto = new Produto("Luva l�tex", "Prote��o", 3.00 , 300);
		
		ProdutoDAO prod_dao = new ProdutoDAO();
		
		System.out.println(prod_dao.selectAll());
		
		prod_dao.insert(produto);
		
		System.out.println(prod_dao.selectAll());
		
		prod_dao.delete(7);
		
		System.out.println(prod_dao.selectAll());
		
		produto.setPreco(30.00);
		prod_dao.update(produto);
		
		System.out.println(prod_dao.selectAll());
		
		Produto produto2 = new Produto();
		
		produto2 = prod_dao.select(5);
		
		prod_dao.insert(produto2);
		
		System.out.println(prod_dao.selectAll());
		
		System.out.println(prod_dao.selectPage(4));
		System.out.println(prod_dao.selectByCategoria("Prote��o"));
		
		/*FIM TESTANDO PRODUTO-DAO*/
		
		/* TESTANDO VENDEDOR-DAO*/
		
		Vendedor vendedor = new Vendedor();
		
		vendedor.setNome("Marciano");
		vendedor.setSobrenome("Fulano");
		vendedor.setRegiao("Sul");
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			vendedor.setData_nasc(dateFormat.parse("1950-08-08"));
		} catch (ParseException e) {
			throw new RuntimeException("Data invalida");
		}
		VendedorDAO vend_dao = new VendedorDAO();
		
		vend_dao.insert(vendedor);
		
		System.out.println(vend_dao.selectAll());
		
		vend_dao.delete(32);
		
		System.out.println(vend_dao.selectAll());
		
		Vendedor vendedor2 = new Vendedor();
		vendedor2 = vend_dao.select(34);
		vend_dao.insert(vendedor2);
		System.out.println(vend_dao.selectAll());
		
		System.out.println(vend_dao.selectPage(30));
		System.out.println(vend_dao.selectByRegiao("sul"));
		
		/*FIM TESTANDO VENDEDOR-DAO*/
		
		
	}

}
