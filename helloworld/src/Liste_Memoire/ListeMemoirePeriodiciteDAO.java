package Liste_Memoire;

import java.util.ArrayList;
import java.util.List;

import DAO.PeriodiciteDAO;
import DAO.Persistance;
import POJO.Periodicite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListeMemoirePeriodiciteDAO implements PeriodiciteDAO {

	private static ListeMemoirePeriodiciteDAO instance;
	private ObservableList<Periodicite> donnees;
	
	@Override
	public boolean create(Periodicite objet) {

		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Periodicite objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
				int idx = this.donnees.indexOf(objet);
				if (idx == -1) {
					throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
				} else {
					
					this.donnees.set(idx, objet);
				}
				
				return true;
	}

	@Override
	public boolean delete(Periodicite objet) {
		Periodicite supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	
	public static ListeMemoirePeriodiciteDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoirePeriodiciteDAO();
		}

		return instance;
	}
	
	private ListeMemoirePeriodiciteDAO() {

		this.donnees = FXCollections.observableArrayList();

		this.donnees.add(new Periodicite(1, "Mensuel"));
		this.donnees.add(new Periodicite(2, "Quotidien"));
	}
	
	@Override
	public Periodicite getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Periodicite(id, "test"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public Object getDAOFactory(Persistance mysql) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}

	@Override
	public ObservableList<Periodicite> getAll() {
		return donnees;
	}

	@Override
	public ObservableList<Periodicite> getByLibelle(String libelle) {
		int i = 0;
		ObservableList<Periodicite> res = FXCollections.observableArrayList();
		while(i < donnees.size()) {
			if(donnees.get(i).getLibelle().equals(libelle)) {
				res.add(donnees.get(i));
			}
			i++;
		}
		return res;
	}
	
	

}
