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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
	
	
	private AjoutRevueVue vue;
	
	public void AjoutRevueCtrl() {}


	public void setVue(AjoutRevueVue ajoutRevueVue) {
		vue = ajoutRevueVue;
		 
		
	}
	
	@FXML
	public void creerRevue(){
		String titre = id_tf_titre.getText().trim();
		String desc = id_tf_desc.getText().trim();
		double tarif = Double.parseDouble(id_tf_tarif.getText().trim());
		String period = id_cb_period.getSelectionModel().getSelectedItem().toString();
		MySQLPeriodiciteDAO p = MySQLPeriodiciteDAO.getInstance();
		int id = p.getByLibelle(period).get(0).getId();
		System.out.println(id);
		MySQLRevueDAO r = MySQLRevueDAO.getInstance();
		Revue Rev = new Revue(1, titre, desc, tarif, "pas d'image", id);
		//r.create(Rev);
	}

	public void retourAccueil() {
		this.vue.close();
	}



}
