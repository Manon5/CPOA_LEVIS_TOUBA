package TestsMySQL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import DAO.RevueDAO;
import MySQL.MySQLRevueDAO;
import POJO.Revue;

public class TestMySQLRevue {
	private RevueDAO instance = MySQLRevueDAO.getInstance();
	private Revue r;
	
	@Before
	public void setUpBefore() {
		Revue r = new Revue(1, "Test", "truc", 12.6, "sjqkd", 2);
	}
	
	@Test 
	public void testCreate() {
		instance.create(r);
		int id = r.getId();
		Revue RevBdd = instance.getById(id);
		assertNotNull(RevBdd);
	}
	
	@Test
	public void testUpdate() {
		Revue newR = new Revue(1, "Test", "truc", 12.6, "sjqkd", 2);
		instance.update(newR);
		Revue BddRev = instance.getById(newR.getId());
		assertEquals(newR, BddRev);
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testGetById() {
		//DAOFactory.create(c);
		
	}
	
	
	@Test
	public void testGetByTitre() {
		
	}
	
	
	@Test
	public void testGetBydesc() {
		
	}
	
	
	@Test
	public void testGetByTarif() {
		
	}
	
	
	@Test
	public void testGetByVisuel() {
		
	}
	
	
	@Test
	public void testGetByPer() {
		
	}
}
