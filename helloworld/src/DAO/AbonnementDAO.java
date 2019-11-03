package DAO;
import java.time.LocalDate;

import POJO.*;
import javafx.collections.ObservableList;

public interface AbonnementDAO extends DAO<Abonnement>{

	public abstract Abonnement getById(int id1, int id2);

	ObservableList<Abonnement> getAll();

	ObservableList<Abonnement> getEnCours();

	ObservableList<Abonnement> getAllByClient();

	ObservableList<Abonnement> getAllByRevue();

	public abstract ObservableList getByDateDebut(LocalDate date);
	
}
