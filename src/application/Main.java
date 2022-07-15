package application;
	
import Controller.Controller;
import Model.Company;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public void start(Stage primaryStage) throws Exception {
		View ElectionView = new View(primaryStage);
		Company ElectionModel = new Company();
		@SuppressWarnings("unused")
		Controller controller = new Controller(ElectionModel,ElectionView);
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}
