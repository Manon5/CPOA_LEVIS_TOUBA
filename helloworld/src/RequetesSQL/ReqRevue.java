package RequetesSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import main.Connexion;

public class ReqRevue {

	
	public void add(int id_revue, String titre, double tarif_numero, int id_periodicite, String description, String visuel) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("INSERT INTO Revue VALUES ('" + id_revue + "', "
					+ ""+ titre +","
					+ "" + tarif_numero + ","
					+ "" + id_periodicite + ","
					+ "" + description + ","
					+ ""  + visuel +")");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	public void delete(int id_revue,  String titre, double tarif_numero, int id_periodicite, String description, String visuel) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("DELETE FROM Revue WHERE id_revue = " + id_revue  +
					" and titre = "+ titre +" "
							+ "and tarif_numero = "+ tarif_numero +" "
							+ "and id_periodicite ="+ id_periodicite 
							+" and description = "+ description 
							+" and visuel = "+ visuel +";");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
		
		
	}
	
	
	public void update_revue(String id_revue,  String titre, double tarif_numero, int id_periodicite, String description, String visuel) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Revue\n" + 
					"SET id_revue = '" + id_revue + "'\n" + 
					"WHERE titre = " + titre +
					"And description=" + description +
					"and tarif_numero =" + tarif_numero +
					"and id_periodicite =" + id_periodicite +
					"and visuel =" + visuel);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
		
		
	}
}

