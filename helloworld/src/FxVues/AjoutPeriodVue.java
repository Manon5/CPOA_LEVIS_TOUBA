package FxVues;
	
import java.net.URL;

import FxControleurs.AjoutAboCtrl;
import FxControleurs.AjoutPeriodCtrl;
import FxControleurs.AjoutRevueCtrl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AjoutPeriodVue extends Stage {
	
	
	public AjoutPeriodVue() {
		try {
			final URL fxmlURL= getClass().getResource("/sources/saisir_period.fxml");
	        this.setTitle("Saisir un abonnement");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
			final VBox node = (VBox)fxmlLoader.load();
	        this.setScene(new Scene(node, 600, 400));
	        AjoutPeriodCtrl controleur = fxmlLoader.getController();
	        controleur.setVue(this);
			this.initModality(Modality.APPLICATION_MODAL);
	        this.show();
		}catch(Exception e){
			
		}
}


}