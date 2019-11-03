package FxControleurs;

import DAO.PeriodiciteDAO;
import DAO.Persistance;
import Factory.DAOFactory;
import FxVues.AjoutPeriodVue;
import FxVues.AjoutRevueVue;
import Liste_Memoire.ListeMemoirePeriodiciteDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Periodicite;
import POJO.Revue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	@FXML private Button id_btn_creer;
	@FXML private Button id_btn_valider;
	@FXML private Button id_btn_annuler;
	@FXML private Label label_periodicite;
	
	private AjoutPeriodVue vue;

	private  DAOFactory fact;
	private int id_select;
	private Persistance p;
	
	void AjoutRevueCtrl() {}

	public void setVue(AjoutPeriodVue ajoutPeriodVue, Persistance pr) {
		vue = ajoutPeriodVue;
		p = pr;
		remplirTable();
		setModeAjout();
	}
	
	public void setModeAjout(){
		id_btn_valider.setVisible(false);
		id_btn_annuler.setVisible(false);
		id_btn_creer.setVisible(true);
		label_periodicite.setText("Nouvelle périodicité");
		id_tf_period.clear(); 
	}
	
	
	public void remplirTable() {
		
		id_table.getItems().clear();
		//on prépare les colonnes
		id_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		id_col_libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
		System.out.println(p);
		PeriodiciteDAO c = DAOFactory.getDAOfactory(p).getPeriodiciteDAO();
		ObservableList test = c.getAll();
		id_table.getItems().addAll(test);
	}
	
	public void creerPeriod() {
		String libelle = id_tf_period.getText().trim();
		
		if(libelle.equals("") || libelle == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une pÃ©riodicitÃ© svp");
		}else {
			PeriodiciteDAO c = DAOFactory.getDAOfactory(p).getPeriodiciteDAO();
			c.create(new Periodicite(1, libelle));
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
			
			PeriodiciteDAO c = DAOFactory.getDAOfactory(p).getPeriodiciteDAO();
			ObservableList test = c.getByLibelle(libelle);
			
			// si aucun résultat
			if(test.isEmpty()) {
				id_error_label.setTextFill(Color.RED);
				id_error_label.setText("Aucun résultat");
			}
			id_table.getItems().addAll(test);
		}
		
	}
	
	
	public void affiModifPeriod() {
		
		// on récupère la sélection
		ObservableList selection = id_table.getSelectionModel().getSelectedItems();
		if(selection.size() == 0) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Aucune périodicité sélectionnée");
		}else if(selection.size() > 1) {
			id_error_label.setTextFill(Color.RED);
			id_error_label.setText("Plusieurs périodicités sélectionnées");
		}else {
			// on prépare l'interface 
			Periodicite p = ((Periodicite) selection.get(0));
			id_select = p.getId();
			id_btn_creer.setVisible(false);
			id_btn_valider.setVisible(true);
			id_btn_annuler.setVisible(true);
			label_periodicite.setText("Modifier la périodicité n°" + id_select);
			id_tf_period.setText(p.getLibelle());
		}

	}
	
	
	
	public void annulerModif() {
		// message de confirmation
		id_lb_custom.setTextFill(Color.BLACK);
		id_lb_custom.setText("Modification annulée");
		setModeAjout();
		remplirTable();
	}
	
	
	
	public void validerModif() {
		String libelle = id_tf_period.getText().trim();
		if(libelle.equals("") || libelle == null) {
			id_lb_custom.setTextFill(Color.RED);
			id_lb_custom.setText("Veuillez renseigner une périodicité svp");
		}else {
			PeriodiciteDAO c = DAOFactory.getDAOfactory(p).getPeriodiciteDAO();
			c.update(new Periodicite(id_select, libelle));
			// message de confirmation
			id_lb_custom.setTextFill(Color.BLACK);
			id_lb_custom.setText("Périodicité n°" + id_select + " modifiée dans la bdd");
		}
		remplirTable();
		setModeAjout();
	}
	
	@FXML
	public void supprPeriod() {
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
