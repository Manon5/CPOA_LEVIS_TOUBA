package FxControleurs;

import FxVues.AjoutAboVue;
import MySQL.MySQLClientDAO;
import MySQL.MySQLPeriodiciteDAO;
import MySQL.MySQLRevueDAO;
import POJO.Client;
import POJO.Periodicite;
import POJO.Revue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class AjoutAboCtrl {
	
	private AjoutAboVue vue;

	@FXML
	private ComboBox<Client> id_cb_client;
	
	@FXML
	private ComboBox<Revue> id_cb_revue;

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

}
