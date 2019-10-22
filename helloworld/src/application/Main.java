package application;
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;


public class Main extends Application{
	
	@FXML
	private Label id_lb_custom;
	
	@Override
	public void start(Stage primaryStage) {
		try {URL fxmlURL=getClass().getResource("/sources/saisir_client.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Node root = fxmlLoader.load();
		Scene scene = new Scene((VBox) root, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Ma première fenêtre JavaFX");
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	public void creerModele() {
		
		
		this.id_lb_custom.setText("test");
	}

}
