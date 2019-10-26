package DAO;
import POJO.*;

public interface AbonnementDAO extends DAO<Abonnement>{

	public abstract Abonnement getById(int id1, int id2);
	
}
