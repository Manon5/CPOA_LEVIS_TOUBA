package POJO;

public class Client {

	private int id_client;
	private String nom;
	private String prenom;
	private String no_rue;
	private String voie;
	private String code_postal;
	private String ville;
	private String pays;
	
	public Client(int id_client, String nom, String prenom, String no_rue, String voie, String code_postal, String ville, String pays) {
		this.setId(id_client);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setNoRue(no_rue);
		this.setVoie(voie);
		this.setCodePostal(code_postal);
		this.setVille(ville);
		this.setPays(pays);
	}

	public Client() {
	}
	
	public int getId() {
		return this.id_client;
	}
	
	public void setId(int id_client) {
		this.id_client=id_client;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom=prenom;
	}
	
	public String getNoRue() {
		return this.no_rue;
	}
	
	public void setNoRue(String no_rue) {
		this.no_rue=no_rue;
	}
	
	public String getCodePostal() {
		return this.code_postal;
	}
	
	public void setCodePostal(String code_postal) {
		this.code_postal=code_postal;
	}
	
	public String getVille() {
		return this.nom;
	}
	
	public void setVille(String ville) {
		this.ville=ville;
	}
	
	public String getPays() {
		return this.pays;
	}
	
	public void setPays(String pays) {
		this.pays=pays;
	}
	
	public String getVoie() {
		return voie;
	}
	
	public void setVoie(String v) {
		voie = v;
	}
	
	
	public String toString() {
		return + this.id_client + this.nom + this.prenom + this.no_rue + this.code_postal + this.ville + this.pays ;
	}
}
