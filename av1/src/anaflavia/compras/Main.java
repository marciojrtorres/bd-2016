package anaflavia.compras;

import java.sql.Date;
import java.util.List;

import anaflavia.dao.ComprasDAO;
import anaflavia.model.Clientes;
import anaflavia.model.Compras;
import anaflavia.model.Tipo_compra;

public class Main {

	public static void main(String[] args) {
		
		Clientes cliente = new Clientes();
		cliente.setNome("Ana Flavia");
		cliente.setEmail("flavia.chamoraes@hotmail.com");
		cliente.setIdade(17);
		
		Tipo_compra tipo = new Tipo_compra();
		tipo.setTipo("Mochila");
		
		Compras compras = new Compras();
		compras.setId_cliente(2);
		compras.setId_tipo(1);
		compras.setQuantidade(2);
		compras.setPreco(30.00);
		Date d = new Date(20,8,2016);
		compras.setData(d);
		cliente.setCompras(compras);
		
		ComprasDAO dao = new ComprasDAO();
		dao.insert(tipo); // inserindo tipos de compra
		dao.insert(cliente); //inserindo clientes
		dao.insert(compras); //inserindo compras
		
		System.out.println(cliente.getId()!=null); // Clientes, tipos e compras que s�o inseridos
		System.out.println(tipo.getId_tipo()!=null);// no banco, tem id != null
		System.out.println(compras.getId_compra()!=null);
		
		Clientes teste = new Clientes();
		teste.setNome("Algu�m a ser apagado");
		teste.setEmail("asuhaushuas@hotmail.com");
		teste.setIdade(19);
		Tipo_compra teste_tipo = new Tipo_compra();
		teste_tipo.setTipo("Objeto");
		Compras teste_compras = new Compras();
		teste_compras.setId_cliente(2);
		teste_compras.setId_tipo(1);
		teste_compras.setQuantidade(2);
		teste_compras.setPreco(30.00);
		Date d2 = new Date(20,8,2016);
		compras.setData(d2);
		cliente.setCompras(teste_compras);
		
		dao.insert(teste);
		dao.insert(teste_tipo);
		dao.insert(teste_compras);
		
		dao.deleteCompra(teste_compras.getId_compra()); // deletando tipos de compras pelo Id
		dao.deleteTipo(teste_tipo.getId_tipo()); //deletando cliente pel id;
		dao.deleteCliente(teste.getId()); //deletando compras pelo id;
		
		//Quando se deleta um cliente, ele deixa de existir no banco e n�o pode ser selecionado
		System.out.println(dao.selectCliente(teste.getId())==null);
		System.out.println(dao.selectTipo(teste_tipo.getId_tipo())==null);
		System.out.println(dao.selectCompra(teste_compras.getId_compra())==null);
		
		//Selecionando clientes pelo id
		Clientes cliente2 = dao.selectCliente(1); //caso n�o exista o id, retorna null
		//System.out.println(cliente2);
		System.out.println(cliente2 !=null);
		//Selecionando tipos de compra pelo id
		Tipo_compra tipo2 = dao.selectTipo(2);
		//System.out.println(tipo2);
		System.out.println(tipo2 !=null);
		//Selecionando compras pelo id
		Compras compra2 = dao.selectCompra(2);
		//System.out.println(compra2);
		System.out.println(compra2 != null);
		
		Tipo_compra tipo3 = new Tipo_compra();
		tipo3 = dao.selectTipo(1);
		tipo3.setTipo("Lapis");
		dao.updateTipo(tipo3);
		//Depois de fazer o update
		System.out.println(dao.selectTipo(1).getTipo().equals("Lapis"));
		
		Clientes cliente3 = new Clientes();
		cliente3 = dao.selectCliente(1);
		cliente3.setNome("Paulo");
		cliente3.setEmail("paulo@hotmail.com");
		cliente3.setIdade(42);
		dao.updateCliente(cliente3);
		//depois do update
		System.out.println(dao.selectCliente(1).getNome().equals("Paulo"));
		
		Compras compra3 = new Compras();
		compra3 = dao.selectCompra(3);
		compra3.setId_compra(3);
		compra3.setId_cliente(1);
		compra3.setId_tipo(2);
		compra3.setQuantidade(4);
		compra3.setPreco(8.50);
		Date data2 = new Date(2016,8,24);
		compra3.setData(data2);
		dao.updateCompra(compra3); 
		System.out.println(dao.selectCompra(3).getQuantidade()==4);
		
		//Selecionando clientes, tipos e compras em p�ginas
		List<Clientes>pag1 = dao.selectPageClientes(1);
		//System.out.println(pag1); 
		System.out.println(pag1 !=null);
		
		List<Tipo_compra>pag2 = dao.selectPageTipo(2);
		//System.out.println(pag2);
		System.out.println(pag2 !=null);
		
		List<Compras> pag3 = dao.selectPageCompras(1);
		//System.out.println(pag3);
		System.out.println(pag3 !=null);
	}

}
