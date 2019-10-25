package RequetesSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import main.Connexion;

public class ReqPeriodicite {
	
	public void add(String libelle) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("INSERT INTO Periodicite (libelle) VALUES ('" + libelle + "')");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	public void delete(int id) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("DELETE FROM Periodicite WHERE id_periodicite = " + id  + ";");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	
	public void updateLibelle(int id, String libelle) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Periodicite\n" + 
					"SET libelle = '" + libelle + "'\n" + 
					"WHERE id_periodicite=" + id);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
		
		
	}
	
	
	
	
}
