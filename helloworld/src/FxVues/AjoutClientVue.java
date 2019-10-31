package FxVues;

import java.net.URL;

import FxControleurs.AjoutClientCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AjoutClientVue extends Stage{
	
	
	public AjoutClientVue() {
		try {
			final URL fxmlURL= getClass().getResource("/sources/affi_client.fxml");
	        this.setTitle("Saisir un client");
			final FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
			final VBox node = (VBox)fxmlLoader.load();
	        this.setScene(new Scene(node, 1307, 519));
	        AjoutClientCtrl controleur = fxmlLoader.getController();
	        controleur.setVue(this);
			this.initModality(Modality.APPLICATION_MODAL);
	        this.show();
		}catch(Exception e){
			
		}
}

}