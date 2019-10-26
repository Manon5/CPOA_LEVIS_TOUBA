package FxControleurs;

import FxVues.AjoutClientVue;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Client;
import POJO.Revue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AjoutClientCtrl {
	
	private AjoutClientVue vue;
	
	
	@FXML
	private TextField id_tf_nom;
	
	@FXML
	private TextField id_tf_prenom;
	
	@FXML
	private TextField id_tf_norue;
	
	@FXML
	private TextField id_tf_voie;
	
	@FXML
	private TextField id_tf_codepost;
	
	@FXML
	private TextField id_tf_ville;
	
	@FXML
	private TextField id_tf_pays;
	
	@FXML
	private Label id_lb_custom;
	
	
	public void setVue(AjoutClientVue V) {
		vue = V;
	}
	
	public void creerClient() {
		String nom = id_tf_nom.getText().trim();
		String prenom = id_tf_prenom.getText().trim();
		String no_rue = id_tf_norue.getText().trim();
		String voie = id_tf_voie.getText().trim();
		String code_postal = id_tf_codepost.getText().trim();
		String ville = id_tf_ville.getText().trim();
		String pays = id_tf_pays.getText().trim();
		
		MySQLClientDAO c = MySQLClientDAO.getInstance();
		Client Cli = new Client(1, nom, prenom, no_rue, voie, code_postal, ville, pays);
		//r.create(Cli);
		id_lb_custom.setText("Ajouter � la Bdd : nom='" + nom + "', prenom=" + prenom + "', no_rue=" + no_rue + "', voie=" + voie + "', code_postal=" + code_postal +"', ville=" + ville +"', pays=" + pays);
		
	}

	public void retourAccueil() {
		this.vue.close();
	}

}
