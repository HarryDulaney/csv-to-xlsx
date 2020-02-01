package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import application.Main;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/view/CSVfilterWiz.fxml"));
	
		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,550,600);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
