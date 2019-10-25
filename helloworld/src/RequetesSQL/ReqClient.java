package RequetesSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import main.Connexion;


public class ReqClient {
	
	public void add(String nom, String prenom, int no_rue, String voie, String code_postal, String ville, String pays) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("INSERT INTO Client(nom, prenom, no_rue, voie, code_postal, ville, pays) VALUES ('" + nom + "', '" + prenom + "', '" + no_rue + "', '" + voie + "', '" + code_postal + "', '" + ville + "', '" + pays + "')");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
	}
	
	public void delete(int id_client) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("DELETE FROM Client WHERE id_client = " + id_client  + ";");
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}
		
		
		
	}
	
	
	public void update_nom(int id_client, String nom) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET nom = '" + nom + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	
	
	public void update_prenom(int id_client, String prenom) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET prenom = '" + prenom + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	
	public void update_no_rue(int id_client, int no) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET no_rue = '" + no + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	
	
	public void update_voie(int id_client, String voie) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET voie = '" + voie + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	
	
	public void update_code_postal(int id_client, String cp) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET code_postal = '" + cp + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	public void update_ville(int id_client, String ville) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET ville = '" + ville + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	public void update_pays(int id_client, String pays) {
		try {
			Connexion laConnexion =  new Connexion();
			Connection co = laConnexion.createConnexion();
			Statement requete = co.createStatement();
			int res = requete.executeUpdate("UPDATE Client\n" + 
					"SET pays = '" + pays + "'\n" + 
					"WHERE id_client = " + id_client);
			co.close();
		} catch (SQLException sqle) {
			System.out.println("Pb select" + sqle.getMessage());
		}

	}
	
	
	
}
