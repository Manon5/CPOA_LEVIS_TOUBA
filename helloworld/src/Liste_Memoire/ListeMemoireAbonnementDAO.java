package Liste_Memoire;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import DAO.*;
import MySQL.MySQLAbonnementDAO;
import POJO.Abonnement;
import POJO.Revue;
import javafx.collections.ObservableList;

public class ListeMemoireAbonnementDAO implements AbonnementDAO{

	private static ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;
	
	
	private ListeMemoireAbonnementDAO() {
		this.donnees = new ArrayList<Abonnement>();
		LocalDate dd1 = LocalDate.of(2005, 02, 25);
		LocalDate df1 = LocalDate.of(2000, 4, 30);
		LocalDate dd2 = LocalDate.of(2001, 12, 10);
		LocalDate df2 = LocalDate.of(2001, 4, 15);
		this.donnees.add(new Abonnement(1, 1, dd1, df1));
		this.donnees.add(new Abonnement(2, 1, dd2, df2));
	}
	


	@Override
	public boolean create(Abonnement objet) {
		//objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			//objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);		
		return ok;
	}


	public static ListeMemoireAbonnementDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireAbonnementDAO();
		}

		return instance;
	}

	@Override
	public boolean update(Abonnement objet) {
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
	public boolean delete(Abonnement objet){
		Abonnement supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un objet inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Abonnement getById(int idC, int idR) {
		// Ne fonctionne que si l'objet métier est bien fait...
				int idx = this.donnees.indexOf(new Abonnement(idR, idC, LocalDate.of(1, 1, 1), LocalDate.of(1, 1, 1)));
				if (idx == -1) {
					throw new IllegalArgumentException("Aucun objet ne possède cet identifiant");
				} else {
					return this.donnees.get(idx);
				}
	}
	
	// marche pas => ??
	public ArrayList<Abonnement> getByDateDeb(LocalDate date) {
		Iterator<Abonnement> it = donnees.iterator();
		ArrayList<Abonnement> list = null;
		while (it.hasNext()) {
		 Abonnement e = it.next();
		 if(e.getDateDebut() == date) {
			 list.add(e);
		 }
		}
		return list;
	}
	
	//marche pas 
	public ArrayList<Abonnement> getByDateFin(LocalDate date) {
		Iterator<Abonnement> it = donnees.iterator();
		ArrayList<Abonnement> list = null;
		while (it.hasNext()) {
		 Abonnement e = it.next();
		 if(e.getDateFin() == date) {
			 list.add(e);
		 }
		}
		return list;
	}



	@Override
	public ObservableList<Abonnement> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



}

