package DAO;
import java.util.ArrayList;

import POJO.*;
import javafx.collections.ObservableList;

public interface PeriodiciteDAO extends DAO<Periodicite>{
	
	Object getDAOFactory(Persistance mysql);
	

	public abstract Periodicite getById(int id);
	public ObservableList<Periodicite> getAll();

}
