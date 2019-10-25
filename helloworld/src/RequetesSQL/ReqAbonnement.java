package RequetesSQL;
import java.sql.Connection;
import java.time.LocalDate;

import main.Connexion;

import java.sql.SQLException;
import java.sql.Statement;

public class ReqAbonnement {

	
	public void add(int id_client, int id_revue, LocalDate date_debut, LocalDate date_fin) {
		try {
			Connexion laConnexion =  new Connexion(); // on ouvre la connexion à la bdd
			Connection co = laConnexion.createConnexion();
			
			Statement requete = co.createStatement(); // on envoie la requete
			int res = requete.executeUpdate("INSERT INTO Abonnement VALUES ('" + id_client + "', '" + id_revue +"', '" + java.sql.Date.valueOf(date_debut) +"', '" + java.sql.Date.valueOf(date_fin) + "')");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	public void delete(int id_client, int id_revue) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("DELETE FROM Abonnement WHERE id_client = " + id_client  + " AND id_revue = " + id_revue +";");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
		
		
	}
	
	

	public void updateDateDebut(int id_revue, int id_client, LocalDate dateDebut) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Abonnement\n" + 
					"SET date_debut = " + java.sql.Date.valueOf(dateDebut) 
					+ " WHERE id_client = '" + id_client + "'\n" + 
					"AND id_revue=" + id_revue);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	
	public void updateDateFin(int id_revue, int id_client, LocalDate dateFin) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Abonnement\n" + 
					"SET date_fin = " + java.sql.Date.valueOf(dateFin) 
					+ " WHERE id_client = '" + id_client + "'\n" + 
					"AND id_revue=" + id_revue);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
	}
	
	
	
	
}
