/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrec;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class user extends Application {
    	private FXMLLoader loader;

    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        
        	try {
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("User.fxml"));
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("User Interface ");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
                
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
