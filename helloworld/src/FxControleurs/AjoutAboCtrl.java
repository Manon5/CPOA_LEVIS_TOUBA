package FxControleurs;

import java.time.LocalDate;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class AjoutAboCtrl {
	
	private AjoutAboVue vue;

	@FXML
	private ComboBox<Client> id_cb_client;
	
	@FXML
	private ComboBox<Revue> id_cb_revue;

	@FXML
	private DatePicker id_dp_debut;

	@FXML
	private DatePicker id_dp_fin;
	
	@FXML
	private Label id_lb_custom;

	public void setVue(AjoutAboVue V) {
		vue = V;
		// remplissage combobox
		MySQLClientDAO p = MySQLClientDAO.getInstance();
		ObservableList<Client> list = p.getAll(); 
		id_cb_client.setItems(list);
		MySQLRevueDAO r = MySQLRevueDAO.getInstance();
		ObservableList<Revue> list2 = r.getAll(); 
		System.out.println(list2.toString());
		id_cb_revue.setItems(list2);
	}
	
	public void creerRevue(){
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
			
			MySQLAbonnementDAO r = MySQLAbonnementDAO.getInstance();
			Abonnement Abo = new Abonnement(cli.getId(), rev.getId(), dateDeb, dateFin);
			r.create(Abo);
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Abonnement ajouté à la Bdd ");
		}
	}

	}
