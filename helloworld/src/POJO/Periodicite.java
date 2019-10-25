package POJO;

public class Periodicite {
 private int id_periodicite;
 private String libelle;
 
 public Periodicite(int id_periodicite, String libelle) {
	 this.setId(id_periodicite);
	 this.setLibelle(libelle);
 }
 
 public Periodicite() {
	
}

public int getId() {
	 return this.id_periodicite;
 }
 
 public void setId(int id_periodicite) {
	 this.id_periodicite=id_periodicite;
 }
 
 public String getLibelle() {
	 return this.libelle;
 }
 
 public void setLibelle(String libelle) {
	 this.libelle=libelle;
 }
 
 public String toString() {
	 return this.id_periodicite + this.libelle;
 }

}
