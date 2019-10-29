package FxControleurs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import FxVues.AjoutRevueVue;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Revue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutRevueCtrl{
	
	@FXML
	private TextField id_tf_titre;
	
	@FXML
	private TextArea id_tf_desc;
	
	@FXML
	private TextField id_tf_tarif;
	
	@FXML
	private ComboBox id_cb_period;
	
	@FXML
	private Label id_lb_custom;
	
	
	private AjoutRevueVue vue;
	
	public void AjoutRevueCtrl() {}


	public void setVue(AjoutRevueVue ajoutRevueVue) {
		vue = ajoutRevueVue;
		id_tf_desc.setWrapText(true);
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
			id_lb_custom.setText("Veuillez choisir la périodicité svp");
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
			id_lb_custom.setText("Ajout� la Bdd : titre='" + titre + "', description=" + desc + "', tarif=" + tarif + "€, périodicité=" + period);
		}
		
		
	}

	public void retourAccueil() {
		this.vue.close();
	}



}
