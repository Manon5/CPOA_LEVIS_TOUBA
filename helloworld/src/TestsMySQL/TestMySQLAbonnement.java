package TestsMySQL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import DAO.AbonnementDAO;
import MySQL.MySQLAbonnementDAO;
import POJO.Abonnement;

public class TestMySQLAbonnement {
	private MySQLAbonnementDAO instance = MySQLAbonnementDAO.getInstance();
	private Abonnement a;
	
	@Before
	public void setUpBefore() {
		Abonnement a = new Abonnement(1, 2, LocalDate.of(2000, 02, 17), LocalDate.of(2003, 10, 26));
	}
	
	@BeforeEach @Test
	public void create() {
		instance.create(a);
	}
	
	@AfterEach @Test
	public void delete() {
		instance.delete(a);
	}
	@Test
	public void testGetById() {
		int idR = a.getIdRevue();
		int idC = a.getIdClient();
		Abonnement AboBdd = instance.getById(idC, idR);
		assertNotNull(AboBdd);
	}
	
	@Test
	public void testUpdate() {
		Abonnement newA = new Abonnement(1, 2, LocalDate.of(2000, 02, 17), LocalDate.of(2003, 10, 26));
		instance.update(newA);
		Abonnement BddAbo = instance.getById(newA.getIdClient(), newA.getIdRevue());
		assertEquals(newA, BddAbo);
	}
	
	
	@Test
	public void testGetByIdClient() {
		ArrayList<Abonnement> aBdd = instance.getByIdClient(1);
		assertTrue(aBdd.contains(a));
	}
	
	
	@Test
	public void testGetByIdRev() {
		ArrayList<Abonnement> aBdd = instance.getByIdRevue(2);
		assertTrue(aBdd.contains(a));
	}
	
	
	@Test
	public void testGetByDateDeb() {
		
	}
	
	
	@Test
	public void testGetByDateFin() {
		
	}
}
