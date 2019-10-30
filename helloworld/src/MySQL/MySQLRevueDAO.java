package MySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.*;
import POJO.Client;
import POJO.Revue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Connexion;

public class MySQLRevueDAO implements RevueDAO{
	
	private static MySQLRevueDAO instance;
	
	private MySQLRevueDAO() {
		
	}
	
	
	
	public static MySQLRevueDAO getInstance() {
		if (instance == null) {	
			instance = new MySQLRevueDAO();
		}
		return instance;
	}
	
	

	@Override
	public boolean create(Revue r) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("insert into Revue(titre, description, tarif_numero, visuel, id_periodicite) value (?,?,?,?,?)");
			requete.setString(1, r.getTitre());
			requete.setString(2, r.getDesc());
			requete.setDouble(3, r.getTarif());
			requete.setString(4, r.getVisuel());
			requete.setInt(5, r.getIdPeriodicite());
			int nbLigne = requete.executeUpdate();
			// récupère l'id
			if(nbLigne > 0) {
				ResultSet key = requete.getGeneratedKeys();
				if(key.next()) {
					r.setId(key.getInt(1));
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
	public boolean update(Revue r) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("update Revue titre = ?, description = ?, tarif_numero = ?, visuel = ?, id_periodicite = ? WHERE id_revue = ?");
			requete.setString(1, r.getTitre());
			requete.setString(2, r.getDesc());
			requete.setDouble(3, r.getTarif());
			requete.setString(4, r.getVisuel());
			requete.setInt(5, r.getIdPeriodicite());
			requete.executeUpdate();
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Revue r) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("DELETE FROM Revue WHERE id_revue = ?");
			requete.setInt(1, r.getId());
			requete.executeUpdate();
			laCo.close();
			
			return true;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public Revue getById(int id) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE id_revue = ?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			laCo.close();
			Revue r = new Revue();
			r.setId(id);
			if(res.next()) {
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getInt("tarif_numero"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
			}
			return r;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	

	
	public ArrayList<Revue> getByTitre(String titre){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE titre = ?");
			requete.setString(1, titre);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Revue> a = new ArrayList<Revue>();
			Revue r = new Revue();
			while(res.next()) {
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Revue> getByDesc(String desc){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE description = ?");
			requete.setString(1, desc);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Revue> a = new ArrayList<Revue>();
			Revue r = new Revue();
			while(res.next()) {
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Revue> getByTarif(double tarif){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE tarif = ?");
			requete.setDouble(1, tarif);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Revue> a = new ArrayList<Revue>();
			Revue r = new Revue();
			while(res.next()) {
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Revue> getByVisuel(String visuel){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE visuel = ?");
			requete.setString(1, visuel);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Revue> a = new ArrayList<Revue>();
			Revue r = new Revue();
			while(res.next()) {
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Revue> getByIdPeriodicite(int id_p){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE id_periodicite = ?");
			requete.setInt(1, id_p);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Revue> a = new ArrayList<Revue>();
			Revue r = new Revue();
			while(res.next()) {
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif_numero"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	@Override
	public ObservableList<Revue> getAll() {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Revue");
			ResultSet res = requete.executeQuery();
			ObservableList<Revue> a = FXCollections.observableArrayList();
			while(res.next()) {
				Revue r = new Revue();
				r.setId(res.getInt("id_revue"));
				r.setTitre(res.getString("titre"));
				r.setDesc(res.getString("description"));
				r.setTarif(res.getDouble("tarif_numero"));
				r.setVisuel(res.getString("visuel"));
				r.setIdPeriodicite(res.getInt("id_periodicite"));
				a.add(r);
			}
			return a;

			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	
	
	

}
