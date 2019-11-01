package FxControleurs;

import FxVues.AjoutPeriodVue;
import FxVues.AjoutRevueVue;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Periodicite;
import POJO.Revue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class AjoutPeriodCtrl {

	@FXML private TextField id_tf_period;	
	@FXML private Label id_lb_custom;
	@FXML private TableView<Periodicite> id_table;
	@FXML private TableColumn id_col_id;
	@FXML private TableColumn id_col_libelle;
	@FXML private TextField id_recherche_libelle;
	@FXML private Label id_error_label;
	
	private AjoutPeriodVue vue;
	
	void AjoutRevueCtrl() {}

	public void setVue(AjoutPeriodVue ajoutPeriodVue) {
		vue = ajoutPeriodVue;
		remplirTable();
	}
	
	public void remplirTable() {
		
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		
		MySQLPeriodiciteDAO c = MySQLPeriodiciteDAO.getInstance();
		ObservableList test = c.getAll();
		id_table.getItems().addAll(test);
	}
	
	public void creerPeriod() {
		String libelle = id_tf_period.getText().trim();
		
		if(libelle.equals("") || libelle == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une pÃ©riodicitÃ© svp");
		}else {
			MySQLPeriodiciteDAO p = MySQLPeriodiciteDAO.getInstance();
			p.create(new Periodicite(1, libelle));
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Périodicité " + libelle + " ajoutée à la bdd");
		}
		
		remplirTable();
	}
	

	public void retourAccueil() {
		this.vue.close();
	}
	
	public void rechercheLibelle() {
		String libelle = id_recherche_libelle.getText().trim();
		
		if(libelle.equals("") || libelle.equals(null)) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Entrer un libellé pour la recherche svp");
		}else {
			id_table.getItems().clear();
			//on prépare les colonnes
			id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
			id_col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
			
			MySQLPeriodiciteDAO c = MySQLPeriodiciteDAO.getInstance();
			ObservableList test = c.getByLibelle(libelle);
			
			// si aucun résultat
			if(test.isEmpty()) {
				id_error_label.setTextFill(Color.RED);
				id_error_label.setText("Aucun résultat");
			}
			id_table.getItems().addAll(test);
		}
		
	}
}
