package FxControleurs;

import java.net.URL;
import java.util.ResourceBundle;

import FxVues.AjoutRevueVue;
import MySQL.MySQLRevueDAO;
import POJO.Revue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AjoutRevueCtrl{
	
	@FXML
	private TextField id_tf_titre;
	
	
	private AjoutRevueVue vue;

	public void setVue(AjoutRevueVue V) {
		vue = V;
	}
	
	@FXML
	public void creerRevue(){
		/*MySQLRevueDAO r = MySQLRevueDAO.getInstance();
		Revue Rev = new Revue(1, titre, desc, prix, image, idperiod);
		r.create(Rev);*/
		
		System.out.println(id_tf_titre.getText());
	}

	public void retourAccueil() {
		this.vue.close();
	}



}
