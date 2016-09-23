package rodrigo.SpotFavela;

import java.sql.Date;
import java.util.EnumSet;
import java.util.List;

import rodrigo.dao.GravadoraDAO;
import rodrigo.model.Banda;
import rodrigo.model.Gravadora;
import rodrigo.model.Banda.NAC;
public class Main {

	public static void main(String[] args) {
		/*
		Gravadora g = new Gravadora();
		g.setNome("Gravadora exemplo");
		g.setQtde_bandas(2);
		Date gd = new Date(1, 1, 1998);
		System.out.println(g.getQtde_bandas());
		
		Banda b = new Banda();
		NAC naciob1 = NAC.EST;
		b.setNome("Arctic Monkeys");
		b.setData_fundacao(new Date(1,1,1989));
		g.setData(gd);
		b.setNacionalidade(naciob1);
		
		g.addBanda(b);
		*/
		/* come�a aki 
		Gravadora g = new Gravadora();
		g.setNome("Sei la3 records");
		g.setData(new Date(1,1,1989));
		g.setQtde_bandas(32);
		Banda b = new Banda();
		b.setNome("Molejao");
		b.setGenero("Pagodao");
		b.setqtde_albums(2);
		g.addBanda(b);
		GravadoraDAO dao = new GravadoraDAO();
		b.setNacionalidade("BR");
		dao.insert(g);
		//dao.selectBandas(g);
		//System.out.println(dao.select(6));
		dao.selectBandas(g);
		System.out.println(dao.select(30));
		dao.delete(2);
		dao.selectBandas(g);
		System.out.println(dao.select(30));
		//
		//List<Contato> todos = dao.selectAll();

		List<Gravadora> pag1 = dao.selectPage(2);
		System.out.println(pag1);
		Gravadora grav_upd = dao.select(4);
		System.out.println(grav_upd.toString());
		dao.update(grav_upd);
		System.out.println(grav_upd.toString());
		System.out.println(dao.selectnome("Sei la records"));

		System.out.println("aqui");
		dao.selectBandas(g);
		termina aki */
		Gravadora g = new Gravadora();
		g.setNome("Som Livre");
		g.setQtde_bandas(32);
		g.setData(new Date (03,04,1969));
		Banda b1 = new Banda();
		b1.setNome("Tiago Iorc");
		b1.setNacionalidade("BR");
		b1.setGenero("Folk");
		b1.setqtde_albums(4);
		g.addBanda(b1);
		Banda b2 = new Banda();
		b2.setNome("Tit�s");
		b2.setNacionalidade("BR");
		b2.setGenero("Rock");
		b2.setqtde_albums(31);
		g.addBanda(b2);
		Gravadora g2 = new Gravadora();
		GravadoraDAO dao = new GravadoraDAO();
		dao.insert(g);
		System.out.println(dao.select(1));
		System.out.println(dao.selectnome("Som Livre"));
		
		
//		System.out.println(pag1);
		//System.out.println(g);
		
		
		
		
	}

}
