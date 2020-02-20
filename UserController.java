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
import com.jfoenix.controls.JFXTextField;
import doryan.windowsnotificationapi.fr.Notification;
import impl.org.controlsfx.skin.DecorationPane;
import java.awt.AWTException;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UserController implements Initializable {
       private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<Claim> c;

    @FXML
    private AnchorPane pane_top;

    @FXML
    private AnchorPane anchorpane_center;

    @FXML
    private TableView<Claim> tbview;
@FXML
    private ImageView img;
    @FXML
    private JFXButton btn_save;

    @FXML
    private JFXTextField txt_message;
    
    @FXML
    private TableColumn<?, ?> column_message;

    @FXML
    private TableColumn<?, ?> column_answer;

    @FXML
    private TableColumn<?, ?> column_status;
    
    
    private String message;
    @FXML
    private AnchorPane anchorpane_centre;
      @FXML
    private JFXButton add;
     @FXML
    private Rating note;
    @FXML
    private DecorationPane deco;

    @FXML
    void noter(ActionEvent event)  {
         System.out.println("Employee rating given by user :"+note.getRating());

    }

    private void ajouterClaim(){
            ServiceReclamation sr= new ServiceReclamation();

             message = txt_message.getText();

            Users user = new Users(1,"nourhene", "nourhene", "nourhene", "client");
            
            Claim c=new Claim(null,"Pending",message, user);
           try {
             
               if (sr.ajouter(c)==1){
               loadDataFromDatabase(user);
               }
              
               
                Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Claim has been created.");
            alert.showAndWait();
           } catch (SQLException ex) {
               Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        }
    
    
    private void loadDataFromDatabase(Users user){
            c.clear();
        try {
            pst = con.prepareStatement("select idRec , message ,answer , status from claim where id=" + user.getId());
            rs = pst.executeQuery();
            while(rs.next())
            {
               String message=rs.getString("message");
               String answer=rs.getString("answer");
               String status=rs.getString("status");
               Claim c1=new Claim(answer, status, message , user);
     c.add(c1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       tbview.setItems(c);
    }
    
         private void setCelluTable(){      
    column_message.setCellValueFactory(new PropertyValueFactory<>("message"));
    column_answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
    column_status.setCellValueFactory(new PropertyValueFactory<>("status"));

    
      }
         
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          btn_save.setOnAction(e->{
           
                  ajouterClaim();
             
		});
           Users user = new Users(1,"nourhene", "nourhene", "nourhene", "client");

           con = DataBase.getInstance().getConnection();
                c= FXCollections.observableArrayList();
                setCelluTable();
                loadDataFromDatabase(user);
                     Image image = new Image("ll.png");
    img.setImage(image);
               
    }
    
}
