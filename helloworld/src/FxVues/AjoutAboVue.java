package FxVues;
	
import java.net.URL;

import DAO.Persistance;
import FxControleurs.AjoutAboCtrl;
import FxControleurs.AjoutRevueCtrl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AjoutAboVue extends Stage {
	
	
	public AjoutAboVue(Persistance p) {
		try {
	        final URL fxmlURL= getClass().getResource("/sources/affi_abo.fxml");
	        this.setTitle("Saisir un abonnement");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			final VBox node = (VBox)fxmlLoader.load();
			Scene scene = new Scene(node, 1307, 519);
			this.setScene(scene);
			this.initModality(Modality.APPLICATION_MODAL);
			AjoutAboCtrl controleur = fxmlLoader.getController();
			controleur.setVue(this, p);
			this.show();
		}catch(Exception e){
			
		}
}


}