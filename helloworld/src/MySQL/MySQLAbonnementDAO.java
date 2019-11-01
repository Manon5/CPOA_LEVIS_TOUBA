package MySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.*;
import POJO.Abonnement;
import POJO.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Connexion;

public class MySQLAbonnementDAO implements AbonnementDAO{
	
	private static MySQLAbonnementDAO instance;
	
	private MySQLAbonnementDAO() {
		
	}
	
	
	public static MySQLAbonnementDAO getInstance() {
		if (instance == null) {	
			instance = new MySQLAbonnementDAO();
		}
		return instance;
	}
	
	

	@Override
	public boolean create(Abonnement a) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("insert into Abonnement(id_client, id_revue, date_debut, date_fin) value (?,?,?,?)");
			requete.setInt(1, a.getIdClient());
			requete.setInt(2, a.getIdRevue());
			requete.setDate(3, java.sql.Date.valueOf(a.getDateDebut()));
			requete.setDate(4, java.sql.Date.valueOf(a.getDateFin()));
			requete.executeUpdate();
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}
	

	@Override
	public boolean update(Abonnement a) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("update Abonnement(date_debut = ?, date_fin = ? WHERE id_client = ? AND id_revue = ?");
			requete.setInt(3, a.getIdClient());
			requete.setInt(4, a.getIdRevue());
			requete.setDate(1, java.sql.Date.valueOf(a.getDateDebut()));
			requete.setDate(2, java.sql.Date.valueOf(a.getDateFin()));
			requete.executeUpdate();
			laCo.close();
			return true;
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(Abonnement a) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("DELETE FROM Revue WHERE id_revue = ? and id_client = ?");
			requete.setInt(2, a.getIdClient());
			requete.setInt(1, a.getIdRevue());
			requete.executeUpdate();
			laCo.close();
			
			return true;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return false;
		}
	}

	@Override
	public Abonnement getById(int idC, int idR) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE id_revue = ? and id_client = ?");
			requete.setInt(2, idC);
			requete.setInt(1, idR);
			ResultSet res = requete.executeQuery();
			laCo.close();
			Abonnement r = new Abonnement();
			if(res.next()) {
				r.setIdClient(res.getInt("id_client"));
				r.setIdRevue(res.getInt("id_revue"));
				r.setDateDebut(res.getDate("date_debut").toLocalDate());
				r.setDateFin(res.getDate("date_fin").toLocalDate());
			}
			return r;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
		
	}
	
	public ArrayList<Abonnement> getByIdClient(int idC) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE id_client = ?");
			requete.setInt(1, idC);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Abonnement> a = new ArrayList<Abonnement>();
			Abonnement r = new Abonnement();
			while(res.next()) {
				r.setIdClient(res.getInt("id_client"));
				r.setIdRevue(res.getInt("id_revue"));
				r.setDateDebut(res.getDate("date_debut").toLocalDate());
				r.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
		
	}
	
	
	public ArrayList<Abonnement> getByIdRevue(int idR) {
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Revue WHERE id_revue = ?");
			requete.setInt(1, idR);
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Abonnement> a = new ArrayList<Abonnement>();
			Abonnement r = new Abonnement();
			while(res.next()) {
				r.setIdClient(res.getInt("id_client"));
				r.setIdRevue(res.getInt("id_revue"));
				r.setDateDebut(res.getDate("date_debut").toLocalDate());
				r.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
		
	}
	

	
	public ObservableList<Abonnement> getByDateDebut(LocalDate dateDeb){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Abonnement WHERE date_debut >= ?");
			requete.setDate(1, java.sql.Date.valueOf(dateDeb));
			ResultSet res = requete.executeQuery();
			ObservableList<Abonnement> a = FXCollections.observableArrayList();
			while(res.next()) {
				Abonnement r = new Abonnement();
				r.setIdClient(res.getInt("id_client"));
				r.setIdRevue(res.getInt("id_revue"));
				r.setDateDebut(res.getDate("date_debut").toLocalDate());
				r.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	
	public ArrayList<Abonnement> getByDateFin(LocalDate dateFin){
		Connection laCo = Connexion.createConnexion();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT * FROM Abonnement WHERE date_fin = ?");
			requete.setDate(1, java.sql.Date.valueOf(dateFin));
			ResultSet res = requete.executeQuery();
			laCo.close();
			ArrayList<Abonnement> a = new ArrayList<Abonnement>();
			Abonnement r = new Abonnement();
			while(res.next()) {
				r.setIdClient(res.getInt("id_client"));
				r.setIdRevue(res.getInt("id_revue"));
				r.setDateDebut(res.getDate("date_debut").toLocalDate());
				r.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(r);
			}
			return a;
			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	
	@Override
	public ObservableList<Abonnement> getAll() {
		Connection laCo = Connexion.createConnexion();
		try {
		PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Abonnement");
			ResultSet res = requete.executeQuery();
			ObservableList<Abonnement> a = FXCollections.observableArrayList();
			while(res.next()) {
				Abonnement c = new Abonnement();
				c.setIdClient(res.getInt("id_client"));
				c.setIdRevue(res.getInt("id_revue"));
				c.setDateDebut(res.getDate("date_debut").toLocalDate());
				c.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(c);
			}
			return a;

			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
	@Override
	public ObservableList<Abonnement> getEnCours() {
		Connection laCo = Connexion.createConnexion();
		try {
		PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Abonnement WHERE date_debut <= NOW() AND date_fin >= NOW()");
			ResultSet res = requete.executeQuery();
			ObservableList<Abonnement> a = FXCollections.observableArrayList();
			while(res.next()) {
				Abonnement c = new Abonnement();
				c.setIdClient(res.getInt("id_client"));
				c.setIdRevue(res.getInt("id_revue"));
				c.setDateDebut(res.getDate("date_debut").toLocalDate());
				c.setDateFin(res.getDate("date_fin").toLocalDate());
				a.add(c);
			}
			return a;

			
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
			return null;
		}
	}
	
	
}