package FxVues;
	
import java.net.URL;

import DAO.Persistance;
import Factory.DAOFactory;
import FxControleurs.AjoutAboCtrl;
import FxControleurs.AjoutPeriodCtrl;
import FxControleurs.AjoutRevueCtrl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AjoutPeriodVue extends Stage {
	
	
	public AjoutPeriodVue(Persistance p) {
		try {
			final URL fxmlURL= getClass().getResource("/sources/affi_period.fxml");
	        this.setTitle("Saisir une périodicité");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			final VBox node = (VBox)fxmlLoader.load();
			Scene scene = new Scene(node, 1307, 519);
			this.setScene(scene);
			this.initModality(Modality.APPLICATION_MODAL);
			AjoutPeriodCtrl controleur = fxmlLoader.getController();
			System.out.println(p);
			controleur.setVue(this, p);
			this.show();
		}catch(Exception e){
			
		}
}


}