package DAO;
import POJO.*;
import javafx.collections.ObservableList;

public interface ClientDAO extends DAO<Client>{

	Object getDAOFactory(Persistance mysql);
	

	public abstract Client getById(int id);
	public ObservableList<Client> getAll();
	
}

