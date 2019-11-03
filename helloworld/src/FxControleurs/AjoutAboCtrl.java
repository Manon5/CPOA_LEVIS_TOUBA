package FxControleurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.AbonnementDAO;
import DAO.ClientDAO;
import DAO.Persistance;
import DAO.RevueDAO;
import Factory.DAOFactory;
import FxVues.AjoutAboVue;
import MySQL.MySQLAbonnementDAO;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Abonnement;
import POJO.Client;
import POJO.Periodicite;
import POJO.Revue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import main.Connexion;

public class AjoutAboCtrl {
	
	private AjoutAboVue vue;

	@FXML private ComboBox<Client> id_cb_client;
	@FXML private ComboBox<Revue> id_cb_revue;
	@FXML private DatePicker id_dp_debut;
	@FXML private DatePicker id_dp_fin;
	@FXML private DatePicker id_recherche_debut;
	@FXML private Label id_lb_custom;
	@FXML private TableView<Abonnement> id_table;
	@FXML private TableColumn id_col_client;
	@FXML private TableColumn id_col_revue;
	@FXML private TableColumn id_col_date_deb;
	@FXML private TableColumn id_col_date_fin;
	@FXML private CheckBox id_cb_en_cours;
	@FXML private Label id_error_label;
	@FXML private Button id_btn_creer;
	@FXML private Button id_btn_valider;
	@FXML private Button id_btn_annuler;
	@FXML private Label label_abonnement;

	private int id_client;
	private int id_revue;
	private Persistance p;

	public void setVue(AjoutAboVue V, Persistance pr) {
		vue = V;
		p = pr;
		// remplissage combobox
		ClientDAO c =  DAOFactory.getDAOfactory(p).getClientDAO();
		ObservableList<Client> list = p.getAll(); 
		//System.out.println(list.toString());
		id_cb_client.setItems(list);
		RevueDAO r = DAOFactory.getDAOfactory(p).getRevueDAO();
		ObservableList<Revue> list2 = r.getAll(); 
		System.out.println(list2.toString());
		id_cb_revue.setItems(list2);
		
		remplirTable();
		setModeAjout();
	}
	
	public void setModeAjout(){
		id_btn_valider.setVisible(false);
		id_btn_annuler.setVisible(false);
		id_btn_creer.setVisible(true);
		label_abonnement.setText("Nouvel abonnement");
		id_cb_client.getSelectionModel().select(-1);
		id_cb_revue.getSelectionModel().select(-1);
		id_dp_debut.setValue(null);
		id_cb_client.setDisable(false);
		id_cb_revue.setDisable(false);
		id_dp_fin.setValue(null);
	}
	
	public void remplirTable() {
		
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
		id_col_revue.setCellValueFactory(new PropertyValueFactory<>("nomRevue"));
		id_col_date_deb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		id_col_date_fin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		
		if(id_cb_en_cours.isSelected()) {
			AbonnementDAO c = DAOFactory.getDAOfactory(p).getAbonnementDAO();
			ObservableList test = c.getEnCours();
			id_table.getItems().addAll(test);
		}else {
			AbonnementDAO c = DAOFactory.getDAOfactory(p).getAbonnementDAO();
			ObservableList test = c.getAll();
			id_table.getItems().addAll(test);
		}
		

		
	}
	
	public void retourAccueil() {
		this.vue.close();
	}
	
	@FXML
	public void creerAbo() {
		LocalDate dateDeb = id_dp_debut.getValue();
		LocalDate dateFin = id_dp_fin.getValue();
		
		if(dateDeb == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une date de début svp");
		}else if(dateFin == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une date de fin svp");
		}else if(id_cb_client.getSelectionModel().getSelectedItem() == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez choisir un client svp");
		}else if(id_cb_revue.getSelectionModel().getSelectedItem() == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez choisir une revue svp");
		}else if(dateFin.isBefore(dateDeb)) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("La date de fin est supérieure à la date de début");
		}else {
			// tout est correct, on insère dans la BdD
			Client  cli = id_cb_client.getSelectionModel().getSelectedItem();
			Revue  rev = id_cb_revue.getSelectionModel().getSelectedItem();
			
			AbonnementDAO r = DAOFactory.getDAOfactory(p).getAbonnementDAO();
			Abonnement Abo = new Abonnement(rev.getId(),cli.getId(),  dateDeb, dateFin);
			r.create(Abo);
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Abonnement ajouté à la Bdd ");
		}
		remplirTable();
	}
	
	public void rechercheDateDeb() {
		
		
		id_table.getItems().clear();
		LocalDate date = id_recherche_debut.getValue();
		
		if(date == null) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Entrer une date pour la recherche svp");
		}else {
			//on prépare les colonnes
			id_col_client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
			id_col_revue.setCellValueFactory(new PropertyValueFactory<>("nomRevue"));
			id_col_date_deb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
			id_col_date_fin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));

			AbonnementDAO c = DAOFactory.getDAOfactory(p).getAbonnementDAO();
			ObservableList test = c.getByDateDebut(date);
			
			//si aucun résultat
			if(test.isEmpty()) {
				id_error_label.setTextFill(Color.RED);
				id_error_label.setText("Aucun résultat");
			}else {
				id_error_label.setTextFill(Color.BLACK);
				id_error_label.setText("Recherche terminée");
			}
			id_table.getItems().addAll(test);
		}
		


	}
	
	public void triParClient(){
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
		id_col_revue.setCellValueFactory(new PropertyValueFactory<>("nomRevue"));
		id_col_date_deb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		id_col_date_fin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		AbonnementDAO c = DAOFactory.getDAOfactory(p).getAbonnementDAO();		
		ObservableList test = c.getAllByClient();
		id_table.getItems().addAll(test);

		
	}
	
	public void triParRevue(){
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
		id_col_revue.setCellValueFactory(new PropertyValueFactory<>("nomRevue"));
		id_col_date_deb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
		id_col_date_fin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
		AbonnementDAO c = DAOFactory.getDAOfactory(p).getAbonnementDAO();
		ObservableList test = c.getAllByRevue();
		id_table.getItems().addAll(test);
	}
	
	
	public void affiModifAbo() {
		
		// on récupère la sélection
		ObservableList selection = id_table.getSelectionModel().getSelectedItems();
		if(selection.size() == 0) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Aucun abonnement sélectionné");
		}else if(selection.size() > 1) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Plusieurs abonnements sélectionnés");
		}else {
			// on prépare l'interface 
			Abonnement a = ((Abonnement) selection.get(0));
			id_client = a.getIdClient();
			id_revue = a.getIdRevue();
			id_cb_client.setDisable(true);
			id_cb_revue.setDisable(true);
			id_btn_creer.setVisible(false);
			id_btn_valider.setVisible(true);
			id_btn_annuler.setVisible(true);
			label_abonnement.setText("Modifier l'abonnement du client" + id_client + " à la revue " + id_revue);

			ClientDAO i = DAOFactory.getDAOfactory(p).getClientDAO();
			id_cb_client.getSelectionModel().select(i.getById(a.getIdClient()));
			RevueDAO o = DAOFactory.getDAOfactory(p).getRevueDAO();
			id_cb_revue.getSelectionModel().select(o.getById(a.getIdRevue()));
			
			System.out.println("Test :" + i.getById(a.getIdClient()).toString());

			id_dp_debut.setValue(a.getDateDebut());
			id_dp_fin.setValue(a.getDateFin());
		}

	}
	

	public void validerModif() {
		LocalDate dateDeb = id_dp_debut.getValue();
		LocalDate dateFin = id_dp_fin.getValue();
		
		if(dateDeb == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une date de début svp");
		}else if(dateFin == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une date de fin svp");
		}else if(id_cb_client.getSelectionModel().getSelectedItem() == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez choisir un client svp");
		}else if(id_cb_revue.getSelectionModel().getSelectedItem() == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez choisir une revue svp");
		}else if(dateFin.isBefore(dateDeb)) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("La date de fin est supérieure à la date de début");
		}else {
			// tout est correct, on insère dans la BdD
			Client  cli = id_cb_client.getSelectionModel().getSelectedItem();
			Revue  rev = id_cb_revue.getSelectionModel().getSelectedItem();
			
			AbonnementDAO r = DAOFactory.getDAOfactory(p).getAbonnementDAO();
			Abonnement Abo = new Abonnement(rev.getId(), cli.getId(), dateDeb, dateFin);
			r.update(Abo);
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Abonnement modifié dans la Bdd ");
		}
		remplirTable();
		setModeAjout();
	}
	
	
	public void annulerModif() {
		// message de confirmation
		id_lb_custom.setTextFill(Color.BLACK);
		id_lb_custom.setText("Modification annulée");
		setModeAjout();
		remplirTable();
	}
	
	@FXML
	public void supprAbo() {
		//on récupère la ligne sélectionnée
				ObservableList selection = id_table.getSelectionModel().getSelectedItems();
				if(selection.size()==0) {
					id_error_label.setTextFill(Color.RED);
					id_error_label.setText("Aucune revue sélectionnée");
				}else if(selection.size() > 1) {
					id_error_label.setTextFill(Color.RED);
					id_error_label.setText("Plusieurs revues sélectionnées");
				}else {
					//on supprime la ligne sélectionnée de la vue (fonctionne)
					id_table.getItems().removeAll(selection);
				}
	}


	}
