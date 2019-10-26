package TestsMySQL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import DAO.PeriodiciteDAO;
import MySQL.MySQLPeriodiciteDAO;
import POJO.Periodicite;

public class TestMySQLPeriodicite {
	private PeriodiciteDAO instance = MySQLPeriodiciteDAO.getInstance();
	private Periodicite p;
	
	@Before
	public void setUpBefore() {
		Periodicite p = new Periodicite(1, "Test");
	}
	
	@Test
	public void testCreate() {
		instance.create(p);
		int id = p.getId();
		Periodicite PrBdd = instance.getById(id);
		assertNotNull(PrBdd);
	}
	
	@Test
	public void testUpdate() {
		Periodicite newP = new Periodicite(1, "Test");
		instance.update(newP);
		Periodicite BddPer = instance.getById(newP.getId());
		assertEquals(newP, BddPer);
	}
	
	@Test
	public void testDelete() {
		
	}
	
	@Test
	public void testGetById() {
		//DAOFactory.create(c);
		
	}
	
	
	@Test
	public void testGetByLibelle() {
		
	}
}
