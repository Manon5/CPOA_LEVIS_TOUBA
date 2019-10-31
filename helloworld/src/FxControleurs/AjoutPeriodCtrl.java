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
	
	private AjoutPeriodVue vue;
	
	void AjoutRevueCtrl() {}

	public void setVue(AjoutPeriodVue ajoutPeriodVue) {
		vue = ajoutPeriodVue;
		remplirTable();
	}
	
	public void remplirTable() {
		
		id_table.getItems().clear();
		//on pr�pare les colonnes
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
			id_lb_custom.setText("Veuillez renseigner une périodicité svp");
		}else {
			MySQLPeriodiciteDAO p = MySQLPeriodiciteDAO.getInstance();
			p.create(new Periodicite(1, libelle));
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("P�riodicit� " + libelle + " ajout�e � la bdd");
		}
		
		remplirTable();
	}
	

	public void retourAccueil() {
		this.vue.close();
	}
}
