package erick;

import java.util.Date;

import erick.dao.ClientesDAO;
import erick.dao.ProdutosDAO;
import erick.model.*;

public class Main {

	public static void main(String[] args) {
		

		Clientes c = new Clientes();
		
		c.setNome("Erick");
		c.setSobrenome("Martins");
		c.setNascimento(new Date("09/10/1999"));		
		
		// CRUD CLIENTES
		
		ClientesDAO dao = new ClientesDAO();
		
		dao.insert(c); // CREATE
		System.out.println(dao.verCliente(c).toString().equals(c.getId() + " Erick Martins 1999-09-10")); // READ
		
		
		
		c.setNome("�rick");
		c.setSobrenome("Santos");
		c.setNascimento(new Date("09/10/1998"));
		dao.update(c); // UPDATE
		
		System.out.println(dao.verCliente(c).toString().equals(c.getId() + " �rick Santos 1998-09-10")); // READ
		
		System.out.println(dao.delete(c)); // DELETE
		
		dao.insert(c);
		
		
		//CRUD PRODUTOS
			
		Produtos p = new Produtos();
		
		p.setNome("Impala 69");		
		p.setPreco(10000);		
		p.setRaridade(Enumerado.Comum);
		c.addProdutos(p);		
		
		ProdutosDAO daoP = new ProdutosDAO();
		
		daoP.insert(p);  // CREATE

		System.out.println(daoP.verProdutos(p).toString().equals(p.getId_produto() + 
				" " + p.getId() + " Impala 69 10000 Comum")); //READ
		
		p.setNome("Camaro");
		p.setPreco(200000);
		p.setRaridade(Enumerado.Raro);
		
		daoP.update(p); //UPDATE
		
		System.out.println(daoP.verProdutos(p).toString().equals(p.getId_produto() + 
				" " + p.getId() + " Camaro 200000 Raro")); //READ
		
		
		System.out.println(daoP.delete(p)); // DELETE
		
		daoP.insert(p);
		
		System.out.println(dao.verProdutosCliente(c)); // LISTA DE PRODUTOS DO CLIENTE
		
		System.out.println(daoP.selectByRaridade(Enumerado.Raro)); // CARREGAR CRITERIADO
		
		System.out.println(dao.selectPage(1)); // CARREGAR PAGINADO
		
		
		}
}
