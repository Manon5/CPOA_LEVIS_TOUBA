package FxControleurs;

import FxVues.AjoutPeriodVue;
import FxVues.AjoutRevueVue;
import MySQL.MySQLPeriodiciteDAO;
import POJO.Periodicite;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class AjoutPeriodCtrl {

	@FXML
	private TextField id_tf_period;	
	
	@FXML
	private Label id_lb_custom;
	
	private AjoutPeriodVue vue;
	
	void AjoutRevueCtrl() {}

	public void setVue(AjoutPeriodVue ajoutPeriodVue) {
		vue = ajoutPeriodVue;
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
	}
	

	public void retourAccueil() {
		this.vue.close();
	}
}
