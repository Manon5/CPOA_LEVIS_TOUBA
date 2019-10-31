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
			final URL fxmlURL= getClass().getResource("/sources/affi_revue.fxml");
	        this.setTitle("Saisir une revue");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			final VBox node = (VBox)fxmlLoader.load();
			Scene scene = new Scene(node, 1307, 519);
			this.setScene(scene);
			this.initModality(Modality.APPLICATION_MODAL);
			AjoutRevueCtrl controleur = fxmlLoader.getController();
			controleur.setVue(this);
			this.show();
		}catch(Exception e){
			
		}
}

	
	
	
	

}
