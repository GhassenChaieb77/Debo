/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrec;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Asus
 */
public class GestionRec extends Application {
    	private FXMLLoader loader;

                
    /*@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));   
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();
    }*/
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
        
        	try {
			 
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
			FXMLDocumentController controller = new FXMLDocumentController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Dashboard Interface ");
			primaryStage.show();
                        
                        
                         /* Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("Style.css");       
        stage.setScene(scene);
        stage.show();*/
                        
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
