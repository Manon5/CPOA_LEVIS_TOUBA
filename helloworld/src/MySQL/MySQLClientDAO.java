package MySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.*;
import POJO.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Connexion;

public class MySQLClientDAO implements ClientDAO{
	
	private static MySQLClientDAO instance;
	
	private MySQLClientDAO() {
		
	}
	
	
	
	public static MySQLClientDAO getInstance() {
		if (instance == null) {	
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	

	@Override
	public boolean create(Client c) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("insert into Client(nom, prenom, no_rue, voie, code_postal, ville, pays) value (?,?,?,?,?,?,?)");
			requete.setString(1, c.getNom());
			requete.setString(2, c.getPrenom());
			requete.setString(3, c.getNoRue());
			requete.setString(4, c.getVoie());
			requete.setString(5, c.getCodePostal());
			requete.setString(6, c.getVille());
			requete.setString(7, c.getPays());
			int nbLigne = requete.executeUpdate();
			// récupère l'id
			if(nbLigne > 0) {
				ResultSet key = requete.getGeneratedKeys();
				if(key.next()) {
					c.setId(key.getInt(1));
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
	public boolean update(Client c) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("update Client set nom=?, prenom = ?, no_rue = ?, voie = ?, code_postal = ?, ville = ?, pays = ? WHERE id_client = ?");
			requete.setString(1, c.getNom());
			requete.setString(2, c.getPrenom());
			requete.setString(3, c.getNoRue());
			requete.setString(4, c.getVoie());
			requete.setString(5, c.getCodePostal());
			requete.setString(6, c.getVille());
			requete.setString(7, c.getPays());
			requete.setInt(8, c.getId());
			requete.executeUpdate();
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Client c) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("DELETE FROM Client WHERE id_client = ?");
			requete.setInt(1, c.getId());
			requete.executeUpdate();
			laCo.close();
			
			return true;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public Client getById(int id) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE id_client = ?");
			requete.setInt(1, id);
			ResultSet res = requete.executeQuery();
			laCo.close();
			Client c = new Client();
			c.setId(id);
			if(res.next()) {
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setVoie(res.getString("voie"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
			}
			return c;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Client> getByNomPrenom(String nom, String prenom){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE nom = ? AND prenom = ?");
			requete.setString(1, nom);
			requete.setString(2, prenom);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Client> a = new ArrayList<Client>();
			Client c = new Client();
			while(res.next()) {
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVoie(res.getString("voie"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Client> getByNoRue(int no){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE no_rue = ?");
			requete.setInt(1, no);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Client> a = new ArrayList<Client>();
			Client c = new Client();
			while(res.next()) {
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVoie(res.getString("voie"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Client> getByVoie(String v){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE voie = ?");
			requete.setString(1, v);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Client> a = new ArrayList<Client>();
			Client c = new Client();
			while(res.next()) {
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setVoie(res.getString("voie"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Client> getByVille(String v){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE ville = ?");
			requete.setString(1, v);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Client> a = new ArrayList<Client>();
			Client c = new Client();
			while(res.next()) {
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setVoie(res.getString("voie"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	public ArrayList<Client> getByPays(String v){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Client WHERE pays = ?");
			requete.setString(1, v);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Client> a = new ArrayList<Client>();
			Client c = new Client();
			while(res.next()) {
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVoie(res.getString("voie"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}

	@Override
	public Object getDAOFactory(Persistance mysql) {
		// TODO Stub de la méthode généré automatiquement
		return null;
	}



	@Override
	public ObservableList<Client> getAll() {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Client");
			ResultSet res = requete.executeQuery();
			ObservableList<Client> a = FXCollections.observableArrayList();
			while(res.next()) {
				Client c = new Client();
				c.setId(res.getInt("id_client"));
				c.setPrenom(res.getString("prenom"));
				c.setNom(res.getString("nom"));
				c.setNoRue(res.getString("no_rue"));
				c.setVoie(res.getString("voie"));
				c.setCodePostal(res.getString("code_postal"));
				c.setVille(res.getString("ville"));
				c.setPays(res.getString("pays"));
				a.add(c);
			}
			return a;

			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}

}
