package FxVues;
	
import java.net.URL;

import FxControleurs.AjoutAboCtrl;
import FxControleurs.AjoutRevueCtrl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AjoutAboVue extends Stage {
	
	
	public AjoutAboVue() {
		try {
			final URL fxmlURL= getClass().getResource("/sources/saisir_abonnement.fxml");
	        this.setTitle("Saisir un abonnement");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
			final VBox node = (VBox)fxmlLoader.load();
	        this.setScene(new Scene(node, 600, 400));
	        AjoutAboCtrl controleur = fxmlLoader.getController();
	        controleur.setVue(this);
			this.initModality(Modality.APPLICATION_MODAL);
	        this.show();
		}catch(Exception e){
			
		}
}

	
	
	
	

}