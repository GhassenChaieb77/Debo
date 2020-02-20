/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionrec;

import GR.entities.Claim;
import GR.entities.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.Debug.id;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Asus
 */
public class FXMLDocumentController implements Initializable {
      private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Claim> c; 
  
    

 @FXML
    private ImageView img;

    @FXML
    private Pane pane_top;

    @FXML
    private Label label_title;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private TextField txt;
    @FXML
    private TableView<Claim> tbview;

    @FXML
    private TableColumn<Users, String> column_name;

    @FXML
    private TableColumn<Claim, String> column_message;

    @FXML
    private TableColumn<Claim, String> column_answer;

    @FXML
    private TableColumn<Claim, String> column_status;
    
    @FXML
    private TableColumn<Claim, String> column_idRec;

    @FXML
    private JFXButton btn_print;
 

    @FXML
    private AnchorPane anchorpane_left;

    @FXML
    private JFXTextArea text_answer;

    @FXML
    private JFXComboBox<String> combo_status;

    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;

    
    private final String status [] = {"Pending","Solved","Closed"};
    private String answer, stat;
    private boolean EDIT=false,ADD=false;
    @FXML
    private AnchorPane centre;
    @FXML
    private JFXButton notif;

    private void initStatus() {
        List<String> list = new ArrayList<>();

		// foreach loop
		for(String content:status) {
			list.add(content);
		}
		
		// convert list to observable list
                con = DataBase.getInstance().getConnection();
         c= FXCollections.observableArrayList();
		ObservableList obList = FXCollections.observableArrayList(list);
		combo_status.setItems(obList);
                searchClaim();
                loadDataFromDatabase();
                setCelluTable();
                
                  Image image = new Image("ll.png");
    img.setImage(image);
               

	}
   /* void showChat(ActionEvent event) {
          LoginWindow.main(status);
    }*/

  
	private void ajouter(){
            answer = text_answer.getText();
            stat = combo_status.getSelectionModel().getSelectedItem();
       
         Claim selected = tbview.getSelectionModel().getSelectedItem();
         int idRectb=selected.getIdRec();
         String message=selected.getMessage();
       
            ServiceReclamation sr= new ServiceReclamation();

            Users user = new Users(1,"nourhene", "nourhene", "nourhene", "client");
            
            Claim c=new Claim(idRectb,message, stat, answer, user);
            try{
            if(sr.ajouterreponseadmin(c)==1)
            {
                loadDataFromDatabase();
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
            
        }
        private void supp() {
            
         Claim selected = tbview.getSelectionModel().getSelectedItem();
         int idRectb=selected.getIdRec();
         String answertb=selected.getAnswer();
         String statustb = selected.getStatus();
         String messagetb = selected.getMessage();
        
            ServiceReclamation sr= new ServiceReclamation();
      
            Users user = new Users(1,"nourhene", "nourhene", "nourhene", "client");
            
            Claim c=new Claim(idRectb,answertb, statustb, messagetb, user);
            try{
            if(sr.delete(c)==1);
            {
                loadDataFromDatabase();
                
            }


            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
            
        }


 
     private void loadDataFromDatabase(){
            c.clear();
        try {
            pst = con.prepareStatement("select c.idRec as idRec , c.message as message,answer as answer ,status as status,u.id as id ,username as username from claim c JOIN users u on c.id=u.id");
            rs = pst.executeQuery();
            while(rs.next())
            {
               int idUser = rs.getInt("id");
               String username=rs.getString("username");
               Users user = new Users(idUser,username);
               int idRec = rs.getInt("idRec");
               String message=rs.getString("message");
               String answer=rs.getString("answer");
               String status=rs.getString("status");
               Claim c1=new Claim(idRec,answer, status, message , user);
     c.add(c1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       tbview.setItems(c);
    }
     private void setCelluTable(){      
         column_idRec.setCellValueFactory(new PropertyValueFactory<>("idRec"));
    column_name.setCellValueFactory(new PropertyValueFactory<>("user"));
    column_message.setCellValueFactory(new PropertyValueFactory<>("message"));
    column_answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        column_status.setCellValueFactory(new PropertyValueFactory<>("status"));

    
      }
     private void editClaim(){
         Claim selected = tbview.getSelectionModel().getSelectedItem();
         text_answer.setText(selected.getAnswer());
         combo_status.getSelectionModel().select(selected.getStatus());
   
     }
     private void saveClaim() throws SQLException{
         answer= text_answer.getText();
         stat= combo_status.getSelectionModel().getSelectedItem();
         if(EDIT){
                ajouterReponse();
         }
         text_answer.setText("");
         combo_status.getSelectionModel().select(0);
     }
     
    public void ajouterReponse(){
        
         answer= text_answer.getText();
         stat= combo_status.getSelectionModel().getSelectedItem();
         
         Claim selected = tbview.getSelectionModel().getSelectedItem();
         int idRectb=selected.getIdRec();
         String messagetb = selected.getMessage();
        
            ServiceReclamation sr= new ServiceReclamation();
      
            Users user = new Users(1,"nourhene", "nourhene", "nourhene", "client");
            
            Claim c=new Claim(idRectb,answer, stat, messagetb, user);
            try{
            if(sr.ajouterreponseadmin(c)==1);
            {
                loadDataFromDatabase();
            }
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    private void showStat() {
		try {
                    FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Stat.fxml"));
			StatController controller = new StatController();
			loader.setController(controller);
			loader.load();
			Scene scene = new Scene(loader.getRoot());
			scene.getStylesheets().add(getClass().getResource("stat.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
      private void showChat() {
		try {
                   
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
       @Override
    public void initialize(URL url, ResourceBundle rb) {
            initStatus();
           btn_save.setOnAction(e->{
                try {
                    saveClaim();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
		});
             btn_delete.setOnAction(e->{
              
                    supp();
               
		});
             
              
              btn_print.setOnAction(e->
              { showStat();
                try {
                    Notification.sendNotification("Deboo Team"," \n An Employee Has been added ." ,MessageType.WARNING);
                } catch (AWTException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
              }
              );
              
  con = DataBase.getInstance().getConnection();
                c= FXCollections.observableArrayList();
                setCelluTable();
                loadDataFromDatabase();
        // TODO
          btn_edit.setOnAction(e->{
              ADD=false;
              EDIT=true;
		editClaim();
                });
    }    

    public void searchClaim(){
txt.setOnKeyReleased(e->{
    if(txt.getText().equals("")){
        loadDataFromDatabase();
    }
    else{
        c.clear();
          String sql = "Select u.id ,c.idRec as idRec , c.message as message,answer as answer ,status as status,username as username from claim c JOIN users u on c.id=u.id where status LIKE '%"+txt.getText()+"%' ";
           
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
                 int idUser = rs.getInt("id");
               String username=rs.getString("username");
               Users user = new Users(idUser,username);
               int idRec = rs.getInt("idRec");
               String message=rs.getString("message");
               String answer=rs.getString("answer");
               String status=rs.getString("status");
               Claim c1=new Claim(idRec,answer, status, message , user);
     c.add(c1);
        }
        tbview.setItems(c);
    } catch (SQLException ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
}

    @FXML
    private void notif(ActionEvent event) throws AWTException, MalformedURLException {
        Notification.sendNotification("Deboo Team"," \n An Employee Has been added ." ,MessageType.WARNING);
    }
     
    

}

