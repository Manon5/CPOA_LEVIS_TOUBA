package DAO;

import POJO.Abonnement;
import javafx.collections.ObservableList;

public interface DAO<T> {
	public abstract boolean create(T objet);
	public abstract boolean update(T objet);
	public abstract boolean delete(T objet);
}
