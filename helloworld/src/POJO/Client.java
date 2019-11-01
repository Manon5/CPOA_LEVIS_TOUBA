package POJO;

public class Client {

	private int idclient;
	private String nom;
	private String prenom;
	private String norue;
	private String voie;
	private String codepostal;
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
		return this.idclient;
	}
	
	public void setId(int id_client) {
		this.idclient=id_client;
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
		return this.norue;
	}
	
	public void setNoRue(String no_rue) {
		this.norue=no_rue;
	}
	
	public String getCodePostal() {
		return this.codepostal;
	}
	
	public void setCodePostal(String code_postal) {
		this.codepostal=code_postal;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public void setVille(String ville) {
		this.ville=ville;

		System.out.println(ville);
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
		return this.nom + " " +this.prenom ;
	}
}
