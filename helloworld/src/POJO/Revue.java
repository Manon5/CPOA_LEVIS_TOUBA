package POJO;

import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;

public class Revue {
	private int id_revue;
	private String titre;
	private String description;
	private double tarif_numero;
	private String visuel;
	private int id_periodicite;
	
	public Revue(int id_revue, String titre, String description, double tarif_numero, String visuel, int id_periodicite) {
		this.setId(id_revue);
		this.setTitre(titre);
		this.setDesc(description);
		this.setTarif(tarif_numero);
		this.setVisuel(visuel);
		this.setIdPeriodicite(id_periodicite);
	}
	
	public Revue() {
	}

	public int getId() {
		return this.id_revue;
	}
	
	public void setId(int id_revue) {
		this.id_revue=id_revue;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public void setTitre(String titre) {
		this.titre=titre;
	}
	
	public String getDesc() {
		return this.description;
	}
	
	public void setDesc(String description) {
		this.description=description;
	}
	
	public double getTarif() {
		return this.tarif_numero;
	}
	
	public void setTarif(double tarif_numero) {
		this.tarif_numero=tarif_numero;
	}
	
	public String getVisuel() {
		return this.visuel;
	}
	
	public void setVisuel(String visuel) {
		this.visuel=visuel;
	}
	
	public int getIdPeriodicite() {
		return this.id_periodicite;
	}
	
	public void setIdPeriodicite(int id_periodicite) {
		this.id_periodicite=id_periodicite;
	}
	
	public String toString() {
		return this.titre;
	}
	
	public int getNbAbonnements() {
		MySQLRevueDAO r = MySQLRevueDAO.getInstance();
		return r.getNbAbonnements(id_revue);
	}
	
	public String getLibellePeriod() {
		MySQLPeriodiciteDAO r = MySQLPeriodiciteDAO.getInstance();
		return r.getById(id_periodicite).getLibelle();
	}
}
