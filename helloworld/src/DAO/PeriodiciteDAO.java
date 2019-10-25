package DAO;
import POJO.*;

public interface PeriodiciteDAO extends DAO<Periodicite>{
	
	Object getDAOFactory(Persistance mysql);
	

	public abstract Periodicite getById(int id);

}
