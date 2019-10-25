package POJO;

import java.time.LocalDate;

//import modele.metier;
public class Abonnement {
	private int idClient;
	private int idRevue;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	
	/*public Abonnement(int id_client) {
		this(-1, id_client);*/

	public Abonnement(int id_revue, int id_client,LocalDate date_debut,LocalDate date_fin) {
		this.setIdClient(id_client);
		this.setIdRevue(id_revue);
		this.setDateDebut(date_debut);
		this.setDateFin(date_fin);
	}


	public Abonnement() {
		// TODO Auto-generated constructor stub
	}


	public int getIdClient() {
		return this.idClient;
	}
	
	public void setIdClient(int id_client) {
		this.idClient=id_client;
	}
	
	public int getIdRevue() {
		return this.idRevue;
	}
	
	public void setIdRevue(int id_revue) {
		this.idRevue=id_revue;
	}
	
	public LocalDate getDateDebut() {
		return this.dateDebut;
	}
	
	public void setDateDebut(LocalDate date_debut) {
		this.dateDebut=date_debut;
	}
	
	public LocalDate getDateFin() {
		return this.dateFin;
	}
	
	public void setDateFin(LocalDate date_fin) {
		this.dateFin=date_fin;
	}
	
	public String toString() {
		return "(" + (this.idClient>=0?this.idClient:"nouveau")+")" + this.idRevue + this.dateDebut + this.dateFin;
		
	}
	

	public boolean equals(Object abo) {
		Abonnement a = (Abonnement)abo;
		if(a.getIdClient() != this.getIdClient()) {
			return false;
		}else if(a.getIdRevue() != this.getIdRevue()) {
			return false;
		}else{
			return true;
		}
	}



	
	
	
	
}
