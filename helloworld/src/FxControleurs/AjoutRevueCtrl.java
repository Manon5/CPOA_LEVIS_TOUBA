package FxControleurs;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.PeriodiciteDAO;
import FxVues.AjoutRevueVue;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Client;
import POJO.Periodicite;
import POJO.Revue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Connexion;

public class AjoutRevueCtrl{
	
	@FXML private TextField id_tf_titre; 
	@FXML private TextArea id_tf_desc;
	@FXML private TextField id_tf_tarif;
	@FXML private ComboBox<Periodicite> id_cb_period;
	@FXML private Label id_lb_custom;
	@FXML private TableView<Revue> id_table;
	@FXML private TableColumn id_col_id;
	@FXML private TableColumn id_col_titre;
	@FXML private TableColumn id_col_desc;
	@FXML private TableColumn id_col_tarif;
	@FXML private TableColumn id_col_period;
	@FXML private TableColumn id_col_abo;
	
	
	private AjoutRevueVue vue;
	
	public void AjoutRevueCtrl() {}


	public void setVue(AjoutRevueVue ajoutRevueVue) {
		vue = ajoutRevueVue;
		id_tf_desc.setWrapText(true);
		MySQLPeriodiciteDAO p = MySQLPeriodiciteDAO.getInstance();
		ObservableList<Periodicite> list = p.getAll(); 
		id_cb_period.setItems(list);
		remplirTable();
	}
	
public void remplirTable() {
		
		id_table.getItems().clear();
		//on pr�pare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		id_col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		id_col_tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		id_col_period.setCellValueFactory(new PropertyValueFactory<>("idPeriodicite"));
		id_col_abo.setCellValueFactory(new PropertyValueFactory<>("abo"));
		MySQLRevueDAO c = MySQLRevueDAO.getInstance();
		ObservableList test = c.getAll();
		id_table.getItems().addAll(test);
	}
	
	@FXML
	public void creerRevue(){
		// on récupère les champs
		String titre = id_tf_titre.getText().trim();
		String desc = id_tf_desc.getText().trim();
		String tarifT = id_tf_tarif.getText().trim();
		double tarif;
		boolean tarifInvalide = false;
		try {
			tarif= Double.parseDouble(tarifT);
		}catch(NumberFormatException e) {
			tarifInvalide = true;
		}
		
		System.out.println(titre);
		// on vérifie que les champs ne sont pas vides
		if(titre.equals("") || titre == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner un titre svp");
		}else if(desc.equals("") || desc == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner la description svp");
		}else if(tarifT.equals("") || tarifT == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner un tarif svp");
		}else if(id_cb_period.getSelectionModel().getSelectedItem() == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez choisir la p�riodicit� svp");
		}else if(tarifInvalide == true) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez entrer un tarif correct svp");
		}else {
			// tout est correct, on insère dans la BdD
			tarif = Double.parseDouble(tarifT);
			String period = id_cb_period.getSelectionModel().getSelectedItem().toString();
			MySQLPeriodiciteDAO p = MySQLPeriodiciteDAO.getInstance();
			int id = p.getByLibelle(period).get(0).getId();
			MySQLRevueDAO r = MySQLRevueDAO.getInstance();
			Revue Rev = new Revue(1, titre, desc, tarif, "pas d'image", id);
			r.create(Rev);
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Revue " + titre + " ajout�e � la Bdd ");
		}
		
		remplirTable();
	}

	public void retourAccueil() {
		this.vue.close();
	}
	
	public void triParTitre() {
		id_table.getItems().clear();
		//on pr�pare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		id_col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		id_col_tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		id_col_period.setCellValueFactory(new PropertyValueFactory<>("idPeriodicite"));
		id_col_abo.setCellValueFactory(new PropertyValueFactory<>("abo"));
		MySQLRevueDAO c = MySQLRevueDAO.getInstance();
		Connection laCo = Connexion.createConnexion();

		ObservableList<Revue> a = FXCollections.observableArrayList();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Revue ORDER BY titre");
			ResultSet res = requete.executeQuery();
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
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
		}
		id_table.getItems().addAll(a);
	}
	
	public void triParPeriodicite() {
		id_table.getItems().clear();
		//on pr�pare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
		id_col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
		id_col_tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
		id_col_period.setCellValueFactory(new PropertyValueFactory<>("idPeriodicite"));
		id_col_abo.setCellValueFactory(new PropertyValueFactory<>("abo"));
		MySQLRevueDAO c = MySQLRevueDAO.getInstance();
		Connection laCo = Connexion.createConnexion();

		ObservableList<Revue> a = FXCollections.observableArrayList();
		try {
			PreparedStatement requete = laCo.prepareStatement("SELECT distinct * FROM Revue ORDER BY id_periodicite");
			ResultSet res = requete.executeQuery();
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
		}catch(SQLException e){
			System.out.println("Pb select" + e.getMessage());
		}
		id_table.getItems().addAll(a);
	}



}
