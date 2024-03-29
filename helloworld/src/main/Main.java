package main;
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import DAO.Persistance;
import Factory.DAOFactory;
import FxVues.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class Main extends Application{
	
	@FXML private Label id_lb_custom;
	@FXML private RadioMenuItem local_mode;
	
	private Stage st;
	private DAOFactory fact;
	private Persistance p = Persistance.MYSQL;

	
	@Override
	public void start(Stage primaryStage) {
		try {URL fxmlURL=getClass().getResource("/sources/accueil.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		Node root = fxmlLoader.load();
		Scene scene = new Scene((VBox) root, 1307, 519);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Projet de CPOA");
		primaryStage.show();
		p = Persistance.MYSQL;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public void switchPersistance() {
		//partie persistance
		if(local_mode.isSelected()) {
			System.out.println("Mode local activ�");	
			p = Persistance.LISTE_MEMOIRE;
		}else {
			System.out.println("Mode local d�sactiv�");
			p = Persistance.MYSQL;
		}
	}
	
	public void creerModele() {
		this.id_lb_custom.setText("test");
	}
	
	public void addRevue() {
		AjoutRevueVue V = new AjoutRevueVue(p);
	}

	
	public void addClient() {
		AjoutClientVue V = new AjoutClientVue(p);
	}
	
	public void addPeriod() {

		System.out.println(p);
		AjoutPeriodVue V = new AjoutPeriodVue(p);
	}
	
	public void addAbo() {
		AjoutAboVue V = new AjoutAboVue(p);
	}
	
	

}