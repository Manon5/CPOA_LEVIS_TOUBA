package TestsListeMemoire;

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
import Liste_Memoire.ListeMemoireAbonnementDAO;
import MySQL.MySQLAbonnementDAO;
import POJO.Abonnement;

public class TestListeMemoireAbonnement {
	private ListeMemoireAbonnementDAO instance = ListeMemoireAbonnementDAO.getInstance();
	private Abonnement a;
	
	@Before
	public void setUpBefore() {
		a = new Abonnement(1, 2, LocalDate.of(1998, 02, 03), LocalDate.of(2003, 10, 03));
	}
	
	@Test
	public void create() {
		boolean b = instance.create(a);
		assertTrue(b);
		
	}
	
	@Test
	public void delete() {
		boolean b = instance.delete(a);
		assertTrue(b);
	}
	
	@Test
	public void testGetById() {
		instance.create(a);
		int idR = 1;
		int idC = 2;

		Abonnement AboBdd = instance.getById(idC, idR);
		assertNotNull(AboBdd);
		instance.delete(a);
	}
	
	@Test
	public void testUpdate() {
		instance.create(a);
		Abonnement newA = new Abonnement(1, 2, LocalDate.of(2000, 02, 17), LocalDate.of(2003, 10, 26));
		instance.update(newA);
		Abonnement BddAbo = instance.getById(newA.getIdClient(), newA.getIdRevue());
		assertEquals(newA, BddAbo);
		instance.delete(a);
	}
	
	
	/*@Test
	public void testGetByIdClient() {
		ArrayList<Abonnement> aBdd = instance.getByIdClient(1);
		assertTrue(aBdd.contains(a));
	}
	
	
	@Test
	public void testGetByIdRev() {
		ArrayList<Abonnement> aBdd = instance.getByIdRevue(2);
		assertTrue(aBdd.contains(a));
	}
	*/
	
	@Test
	public void testGetByDateDeb() {
		instance.create(a);
		ArrayList<Abonnement> AboBdd = instance.getByDateDeb(LocalDate.of(1998, 02, 03));
		assertNotNull(AboBdd);
		instance.delete(a);
	}
	
	
	@Test
	public void testGetByDateFin() {
		instance.create(a);
		
		instance.delete(a);
		
	}
}