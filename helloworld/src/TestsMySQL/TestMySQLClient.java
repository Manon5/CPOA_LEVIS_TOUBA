package TestsMySQL;

import Factory.*;
import MySQL.MySQLClientDAO;
import DAO.ClientDAO;
import DAO.Persistance;
import POJO.Client;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMySQLClient {
	
	private ClientDAO instance = MySQLClientDAO.getInstance();
	private Client c;
	
	@Before
	public void setUpBefore() {
		Client c = new Client(1, "Test", "Truc", "5", "rue des papillons", "57000", "Metz", "France");
	}
	
	@Test
	public void testCreate() {
		instance.create(c);
		int id = c.getId();
		Client ClBdd = instance.getById(id);
		assertNotNull(ClBdd);
	}
	
	@Test
	public void testUpdate() {
		Client newC = new Client(1, "Test", "Truc", "5", "rue des fleurs", "57000", "Metz", "France");
		instance.update(newC);
		Client BddC = instance.getById(newC.getId());
		assertEquals(newC, BddC);
	}
	
	@Test
	public void testDelete() {
		
	}
	
	public void testGetById() {
		//DAOFactory.create(c);
		
	}
	
	@Test
	public void testGetByNom() {
		
	}
	
	
	@Test
	public void testGetByPrenom() {
		
	}
	
	
	@Test
	public void testGetByNoR() {
		
	}
	
	
	@Test
	public void testGetByVoie() {
		
	}
	
	
	@Test
	public void testGetByCode() {
		
	}
	
	
	@Test
	public void testGetByVille() {
		
	}
	
	
	@Test
	public void testGetByPays() {
		
	}
}
