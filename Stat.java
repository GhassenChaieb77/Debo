
package gestionrec;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Asus
 */
public class Stat extends Application {
    	private FXMLLoader loader;
        
Parent root;
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
      try { 
          
          /*
          
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Stat.fxml"));
                        StatController controller = new StatController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("Stat.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Stat Interface ");
			primaryStage.show();
			*/
          
                             try {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new StatController());
        loader.setLocation(getClass().getResource("Stat.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();            
    } catch (Exception e) {
        e.printStackTrace();
    }
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
  public static void main(String[] args) {
        launch(args);
    }
    
    }
    

