package FxControleurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Table;

import DAO.ClientDAO;
import FxVues.AjoutClientVue;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Client;
import POJO.Revue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import main.Connexion;

public class AjoutClientCtrl {
	
	private AjoutClientVue vue;
	
	
	@FXML private TextField id_tf_nom;
	@FXML private TextField id_tf_prenom;
	@FXML private TextField id_tf_norue;
	@FXML private TextField id_tf_voie;
	@FXML private TextField id_tf_codepost;
	@FXML private TextField id_tf_ville;
	@FXML private TextField id_tf_pays;
	@FXML private TextField id_recherche_nom;
	@FXML private TextField id_recherche_prenom;
	@FXML private TextField id_recherche_ville;
	@FXML private Label id_lb_custom;
	@FXML private Label id_error_label;
	@FXML private TableView<Client> id_table;
	@FXML private TableColumn id_col_id;
	@FXML private TableColumn id_col_nom;
	@FXML private TableColumn id_col_prenom;
	@FXML private TableColumn id_col_no;
	@FXML private TableColumn id_col_rue;
	@FXML private TableColumn id_col_code;
	@FXML private TableColumn id_col_ville;
	@FXML private TableColumn id_col_pays;
	
	
	public void setVue(AjoutClientVue V) {
		vue = V;
		remplirTable();
	}
	
	public void remplirTable() {
		
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		id_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		id_col_no.setCellValueFactory(new PropertyValueFactory<>("noRue"));
		id_col_rue.setCellValueFactory(new PropertyValueFactory<>("voie"));
		id_col_code.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
		id_col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
		id_col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
		MySQLClientDAO c = MySQLClientDAO.getInstance();
		ObservableList test = c.getAll();
		id_table.getItems().addAll(test);
	}
	
	public void creerClient() {
		String nom = id_tf_nom.getText().trim();
		String prenom = id_tf_prenom.getText().trim();
		String no_rue = id_tf_norue.getText().trim();
		String voie = id_tf_voie.getText().trim();
		String code_postal = id_tf_codepost.getText().trim();
		String ville = id_tf_ville.getText().trim();
		String pays = id_tf_pays.getText().trim();
		// on vÃ©rifie que les champs ne sont pas vides
		if(nom.equals("") || nom == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner un nom svp");
		}else if(prenom.equals("") || prenom == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner un prénom svp");
		}else if(no_rue.equals("") || no_rue == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner un numéro de rue svp");
		}else if(voie.equals("") || voie == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner la voie svp");
		}else if(code_postal.equals("") || code_postal == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez entrer un code postal correct svp");
		}else if(ville.equals("") || ville == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez entrer un nom de ville correct svp");
		}else if(pays.equals("") || pays == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez entrer un pays correct svp");
		}else {
			MySQLClientDAO c = MySQLClientDAO.getInstance();
			Client Cli = new Client(1, nom, prenom, no_rue, voie, code_postal, ville, pays);
			c.create(Cli);
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Client " + nom + " " + prenom + " ajouté à la bdd");
		}
		
		remplirTable();
	}

	public void retourAccueil() {
		this.vue.close();
	}
	
	public void triParVille() {

		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		id_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		id_col_no.setCellValueFactory(new PropertyValueFactory<>("noRue"));
		id_col_rue.setCellValueFactory(new PropertyValueFactory<>("voie"));
		id_col_code.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
		id_col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
		id_col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
		
		MySQLClientDAO c = MySQLClientDAO.getInstance();
		
		Connection laCo = Connexion.createConnexion();

		ObservableList<Client> a = FXCollections.observableArrayList();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Client ORDER BY ville");
			ResultSet res = requete.executeQuery();
			while(res.next()) {
				Client c1 = new Client();
				c1.setId(res.getInt("id_client"));
				c1.setPrenom(res.getString("prenom"));
				c1.setNom(res.getString("nom"));
				c1.setNoRue(res.getString("no_rue"));
				c1.setVoie(res.getString("voie"));
				c1.setCodePostal(res.getString("code_postal"));
				c1.setVille(res.getString("ville"));
				c1.setPays(res.getString("pays"));
				a.add(c1);
			}
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
		}
		id_table.getItems().addAll(a);
	}
	
	
	
	public void triParClient() {

			id_table.getItems().clear();
			//on prépare les colonnes
			id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			id_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			id_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			id_col_no.setCellValueFactory(new PropertyValueFactory<>("noRue"));
			id_col_rue.setCellValueFactory(new PropertyValueFactory<>("voie"));
			id_col_code.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
			id_col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
			id_col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
			
			MySQLClientDAO c = MySQLClientDAO.getInstance();
			
			Connection laCo = Connexion.createConnexion();

			ObservableList<Client> a = FXCollections.observableArrayList();
			try {
				PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Client ORDER BY nom, prenom");
				ResultSet res = requete.executeQuery();
				while(res.next()) {
					Client c1 = new Client();
					c1.setId(res.getInt("id_client"));
					c1.setPrenom(res.getString("prenom"));
					c1.setNom(res.getString("nom"));
					c1.setNoRue(res.getString("no_rue"));
					c1.setVoie(res.getString("voie"));
					c1.setCodePostal(res.getString("code_postal"));
					c1.setVille(res.getString("ville"));
					c1.setPays(res.getString("pays"));
					a.add(c1);
				}
			}catch(SQLException e){
				System.out.println("Pb select" + e.getMessage());
			}
			id_table.getItems().addAll(a);
		}
	
	public void rechercheVille() {
		String ville = id_recherche_ville.getText().trim();
		
		if(ville.equals(null) || ville.equals("")) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Entrer une ville pour la recherche svp");
		}else {
			id_table.getItems().clear();
			//on prépare les colonnes
			id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			id_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			id_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			id_col_no.setCellValueFactory(new PropertyValueFactory<>("noRue"));
			id_col_rue.setCellValueFactory(new PropertyValueFactory<>("voie"));
			id_col_code.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
			id_col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
			id_col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
			MySQLClientDAO c = MySQLClientDAO.getInstance();
			ObservableList test = c.getByVille(ville);
			
			
			// si on a pas de résultat
			if(test.isEmpty()) {
				id_error_label.setTextFill(Color.RED);
				id_error_label.setText("Aucun résultat trouvé");
			}
			id_table.getItems().addAll(test);
		}
	}
	
	
	public void rechercheNomPrenom() {
		String nom = id_recherche_nom.getText().trim();
		String prenom = id_recherche_prenom.getText().trim();
		
		if(nom.equals(null) || nom.equals("")) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Entrer un nom pour la recherche svp");
		}else if(prenom.equals(null) || prenom.equals("")) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Entrer un prénom pour la recherche svp");
		}else {
			id_table.getItems().clear();
			//on prépare les colonnes
			id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			id_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			id_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
			id_col_no.setCellValueFactory(new PropertyValueFactory<>("noRue"));
			id_col_rue.setCellValueFactory(new PropertyValueFactory<>("voie"));
			id_col_code.setCellValueFactory(new PropertyValueFactory<>("codePostal"));
			id_col_ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
			id_col_pays.setCellValueFactory(new PropertyValueFactory<>("pays"));
			MySQLClientDAO c = MySQLClientDAO.getInstance();
			ObservableList test = c.getByNomPrenom(nom, prenom);
			
			
			// si on a pas de résultat
			if(test.isEmpty()) {
				id_error_label.setTextFill(Color.RED);
				id_error_label.setText("Aucun résultat trouvé");
			}
			id_table.getItems().addAll(test);
		}
		
		
		
	}
	}

