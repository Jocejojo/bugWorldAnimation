package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class BugWorldAnimator extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {			
			
			primaryStage.setTitle("Bug World Animation");
	        World world = new World();
	        Scene scene = new Scene(world.getPane());
			
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
