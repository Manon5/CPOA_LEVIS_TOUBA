package Liste_Memoire;
import java.util.ArrayList;
import java.util.List;

import DAO.*;
import POJO.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListeMemoireClientDAO implements ClientDAO {

	private static ListeMemoireClientDAO instance;
	private ObservableList<Client> donnees;
	
	
	private ListeMemoireClientDAO() {
		this.donnees = FXCollections.observableArrayList();
		this.donnees.add(new Client(1, "LEVIS", "Lucas", "14", "Rue de la cath�drale", "57000", "Metz", "France"));
		this.donnees.add(new Client(2, "RAMM", "Arno", "02", "Rue des tulipes", "57000", "Metz", "France"));
	}
	
	
	
	@Override
	public boolean create(POJO.Client objet) {
		objet.setId(3);
		
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(POJO.Client objet) {
		
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un objet inexistant");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(POJO.Client objet) {
		
		Client a_supprimer;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			a_supprimer = this.donnees.remove(idx);
		}
		
		return objet.equals(a_supprimer);
	}

	@Override
	public Client getById(int id) {
		
		int idx = this.donnees.indexOf(new Client(id, "test", "", "0", " ", "0", "", ""));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne poss�de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	// utilité de cette fonction ??
	@Override
	public Object getDAOFactory(Persistance mysql) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}
	
	
	public static ListeMemoireClientDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireClientDAO();
		}

		return instance;
	}



	@Override
	public ObservableList<Client> getAll() {
		return this.donnees;
	}



	@Override
	public ObservableList<Client> OrderByNom() {
		return this.donnees;
	}



	@Override
	public ObservableList<Client> getByNomPrenom(String nom, String prenom) {
		ObservableList res = FXCollections.observableArrayList();
		int i = 0;
		while(i < donnees.size()) {
			if(donnees.get(i).getNom() == nom && donnees.get(i).getPrenom() == prenom) {
				res.add(donnees.get(i));
			}
		}
		return res;
	}



	@Override
	public ObservableList<Client> getByVille(String v) {
		ObservableList res = FXCollections.observableArrayList();
		int i = 0;
		while(i < donnees.size()) {
			if(donnees.get(i).getVille() == v) {
				res.add(donnees.get(i));
			}
		}
		return res;
	}

}
