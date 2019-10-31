package DAO;
import POJO.*;
import javafx.collections.ObservableList;

public interface AbonnementDAO extends DAO<Abonnement>{

	public abstract Abonnement getById(int id1, int id2);

	ObservableList<Abonnement> getAll();
	
}
