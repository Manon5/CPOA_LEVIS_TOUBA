// Classe corrigée : à tester ?

package Liste_Memoire;

import java.util.ArrayList;
import java.util.List;

import DAO.*;
import POJO.Periodicite;
import POJO.Revue;

public class ListeMemoireRevueDAO implements RevueDAO{

	private static ListeMemoireRevueDAO instance;
	private List<Revue> donnees;
	
	private ListeMemoireRevueDAO() {

		this.donnees = new ArrayList<Revue>();
		this.donnees.add(new Revue(1, "Tintin", "BD de Tintin", 10.50, "couverture_tintin.png", 1));
		this.donnees.add(new Revue(2, "Le Monde", "Journal d'informations", 3.0, "le_monde.jpeg", 2));
	}
	
	@Override
	public boolean create(POJO.Revue objet) {
		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);

		return ok;
	}

	@Override
	public boolean update(POJO.Revue objet) {
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
	public boolean delete(POJO.Revue objet) {
		Revue a_supprimer;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			a_supprimer = this.donnees.remove(idx);
		}
		
		return objet.equals(a_supprimer);
	}

	@Override
	public Revue getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Revue(id, "test", "", 0, "", 0));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	
	public static ListeMemoireRevueDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireRevueDAO();
		}

		return instance;
	}


}
