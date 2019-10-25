package MySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.*;
import POJO.Periodicite;
import main.Connexion;

public class MySQLPeriodiciteDAO implements PeriodiciteDAO{

	private static MySQLPeriodiciteDAO instance;
	
	private MySQLPeriodiciteDAO() {
		
	}
	
	
	
	public static MySQLPeriodiciteDAO getInstance() {
		if (instance == null) {	
			instance = new MySQLPeriodiciteDAO();
		}
		return instance;
	}
	
	
	
	@Override
	public boolean create(Periodicite p) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("insert into Periodicite(libelle) value (?)");
			requete.setString(1, p.getLibelle());
			int nbLigne = requete.executeUpdate();
			// récupère l'id
			if(nbLigne > 0) {
				ResultSet key = requete.getGeneratedKeys();
				if(key.next()) {
					p.setId(key.getInt(1));
				}
			}
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
		return false;
	}
	}

	@Override
	public boolean update(Periodicite p) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("update Client libelle = ? WHERE id_periodicite = ?");
			requete.setInt(2, p.getId());
			requete.setString(1, p.getLibelle());
			requete.executeUpdate();
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Periodicite p) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("DELETE FROM Periodicite WHERE id_periodicite = ?");
			requete.setInt(1, p.getId());
			requete.executeUpdate();
			laCo.close();
			
			return true;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public Periodicite getById(int id) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Periodicite WHERE id_periodicite = ?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			laCo.close();
			Periodicite p = new Periodicite(id, null);
			if(res.next()) {
				p.setId(res.getInt("id_periodicite"));
				p.setLibelle(res.getString("libelle"));
			}
			return p;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Periodicite> getByLibelle(String libelle){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Periodicite WHERE libelle = ?");
			requete.setString(1, libelle);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Periodicite> a = new ArrayList<Periodicite>();
			Periodicite p = new Periodicite();
			while(res.next()) {
				p.setId(res.getInt("id_periodicite"));
				p.setLibelle(res.getString("libelle"));

				a.add(p);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	
	@Override
	public Object getDAOFactory(Persistance mysql) {
		return null;
	}

}
