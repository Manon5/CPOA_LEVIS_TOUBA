package DAO;
import POJO.*;
import javafx.collections.ObservableList;

public interface RevueDAO extends DAO<Revue>{
	

	public abstract Revue getById(int id);
	public ObservableList<Revue> getAll();

}
