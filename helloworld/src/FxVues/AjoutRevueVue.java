package FxVues;
	
import java.net.URL;

import FxControleurs.AjoutRevueCtrl;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class AjoutRevueVue extends Stage {
	
	
	public AjoutRevueVue() {
		try {
			final URL fxmlURL= getClass().getResource("/sources/saisir_revue.fxml");
	        this.setTitle("Saisir une revue");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
			final VBox node = (VBox)fxmlLoader.load();
	        this.setScene(new Scene(node, 800, 500));
	        AjoutRevueCtrl controleur = fxmlLoader.getController();
	        controleur.setVue(this);
			this.initModality(Modality.APPLICATION_MODAL);
	        this.show();
		}catch(Exception e){
			
		}
}

	
	
	
	

}
