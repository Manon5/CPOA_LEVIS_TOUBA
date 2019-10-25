package main;
import java.sql.*;
public class Connexion {
	
	public static Connection createConnexion() {
		String url = "jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/touba8u_RevuesOnLine?serverTimezone=Europe/Paris";
		String username = "touba8u_appli";
		String mdp = "31809319";
		Connection maConnexion = null;
		
		try {
			maConnexion = DriverManager.getConnection(url, username, mdp);
		}catch (SQLException sqle) {
			System.out.println("Erreur connexion" + sqle.getMessage());
		}
		
		return maConnexion;
	}

}
