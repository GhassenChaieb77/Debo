/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produits.gui;

import Dialog.AlertDialog;
import Produit.entite.Produits;
import category.Entite.Category;
import category.Service.Servicecategory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import deboo.Utils.DataAccessObject;
import deboo.Utils.DataBase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import produit.Service.Serviceproduits;
import static sun.security.krb5.KrbException.errorMessage;

/**
 *
 * @author ghassen
 */
public class produitsController implements Initializable {
    private ObservableList<Category> data;
    private ObservableList<Produits> datap;
    private Connection con;
    private ResultSet rs=null;
    private PreparedStatement pst;
    private FileInputStream fis; 
    private FileChooser fileChooser;
    DataAccessObject dao;
    @FXML
    private Label error_idcat,error_idprod,error_nameprod,error_priceprod;
     @FXML
    private Label error_namecat;
    @FXML
    private TableView<Category> tab_cat;
        @FXML
    private TableView<Produits> tab_prod;
     @FXML
     private Pane pane_cat,pane_prod;
      @FXML
      private JFXButton btn_cat,btn_prod,add_cat,btn_export;
      @FXML
      private JFXComboBox<String> cmb_cat;
        @FXML
      private TableColumn<Category,?> Namec;
        @FXML
      private TableColumn<Category,?> Idc;
         @FXML
      private TableColumn<Produits,?> Namep;
        @FXML
      private TableColumn<Produits,?> Pricep;
         @FXML
      private TableColumn<Produits,?> idcp;
    @FXML
    private JFXTextField prod_search,tf_idcat,tf_nomcat,tf_idprod,tf_nameprod,tf_prodprice,tf_catid;
    
    
       @FXML
       private void handlebuttonAction(ActionEvent event){
            
           if (event.getSource() == btn_cat)
           {
               pane_cat.toFront();
           }
       
           else  if (event.getSource() == btn_prod)
           {
               pane_prod.toFront();
           }
       }
           @FXML
   public void ajoutercat(ActionEvent event) throws SQLException {
        boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
        if(isIdEmpty && isNameEmpty)
        {
            String idC = tf_idcat.getText();
            String NomC = tf_nomcat.getText();
            int i;
            Servicecategory cat = new Servicecategory();
            int idC1=Integer.valueOf(idC);
            Category c = new Category(idC1,NomC);
            
            i=cat.ajoutercategory(c);
              if(i==1)
        {
            AlertDialog.display("Info","categorie ajoutée");
            affichercat();
            loadDatacat();
            clearTextField();
            initcatcombo();
        }
          
        }     
          
           
        
    }
     @FXML
   public void ajouterprod(ActionEvent event) throws SQLException {
         boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         boolean isPriceEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_prodprice, error_priceprod, "price is require");
        
        if(isIdEmpty && isNameEmpty && isPriceEmpty)
        {
   String idC = tf_idcat.getText();
          
            int i;
           
            
   
            String idp = tf_idprod.getText();
            String Nomp = tf_nameprod.getText();
            Float Pricep=Float.valueOf(tf_prodprice.getText());
            String Namecat=cmb_cat.getSelectionModel().getSelectedItem();
            
   
              Category c = new Category(0,Namecat);
            Serviceproduits prod=new Serviceproduits();
            int idp1=Integer.valueOf(idp);
            Produits p = new Produits(idp1,Nomp,Pricep,c);
            i=prod.ajouterproduct(p);
              if(i==1)
        {
            AlertDialog.display("Info","Produit ajouté");
            afficherprod();
            
           loadDataprod();
            clearTextFieldprod();
        }
          
        }    
          
           
        
    }
   
   @FXML
    public void updatecat(ActionEvent event) throws SQLException {
        boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
        if(isIdEmpty && isNameEmpty)
        {
          
          
            String idC = tf_idcat.getText();
            String NomC = tf_nomcat.getText();
            int i;
            Servicecategory cat = new Servicecategory();
            int idC1=Integer.valueOf(idC);
            Category c = new Category(idC1,NomC);
            
            i=cat.updatecategory(idC1, NomC);
              if(i==1)
        {
            AlertDialog.display("Info","categorie modifiée");
            affichercat();
            loadDatacat();
            clearTextField();
            initcatcombo();
        }
          
        }     
          
           
        
    }
     public void updateprod(ActionEvent event) throws SQLException {
        //boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idcat, error_idcat, "id must be number");
        //boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nomcat, error_namecat, "Name is require");
     boolean isIdEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_idprod, error_idprod, "id must be number");
        boolean isNameEmpty=validation.TextFieldvalidation.isTextFieldNoEmpty(tf_nameprod, error_nameprod, "Name is require");
         boolean isPriceEmpty=validation.TextFieldvalidation.istextFieldTypeNumber(tf_prodprice, error_priceprod, "price is require");
        
        if(isIdEmpty && isNameEmpty && isPriceEmpty)
        {
        int i;
           
            
   
            String idp = tf_idprod.getText();
            String Nomp = tf_nameprod.getText();
            Float Pricep=Float.valueOf(tf_prodprice.getText());
            String Namecat=cmb_cat.getSelectionModel().getSelectedItem();
            
   
              Category c = new Category(0,Namecat);
            Serviceproduits prod=new Serviceproduits();
            int idp1=Integer.valueOf(idp);
            Produits p = new Produits(idp1,Nomp,Pricep,c);
            i=prod.updateproduct(p);
              if(i==1)
        {
            AlertDialog.display("Info","produit modifié");
            afficherprod();
            loadDataprod();
            clearTextFieldprod();
        }
          
        }  
        
    }
    @FXML
public void deletecat(ActionEvent event) throws SQLException {
        
        
            String idC = tf_idcat.getText();
         
            int i;
            Servicecategory cat = new Servicecategory();
            int idC1=Integer.valueOf(idC);
           
            
            i=cat.deletecategory(idC1);
              if(i==1)
        {
            AlertDialog.display("Info","categorie supprimée");
            affichercat();
            loadDatacat();
            afficherprod();
            loadDataprod();
            clearTextField();
            initcatcombo();
        }
} 
                @FXML
public void deleteprod(ActionEvent event) throws SQLException {
        
        
            String idp = tf_idprod.getText();
         
            int i;
            Serviceproduits prod=new Serviceproduits();
            int idp1=Integer.valueOf(idp);
           
            
            i=prod.deleteproduct(idp1);
              if(i==1)
        {
            AlertDialog.display("Info","produit supprimé");
            afficherprod();
            loadDataprod();
            clearTextFieldprod();
        }
              
          
             
          
           
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        con = DataBase.getInstance().getConnection();
         data= FXCollections.observableArrayList();
         datap= FXCollections.observableArrayList();
        affichercat();
       loadDatacat();
        setCellValueFromTableToTextField();
        cmb_cat.setOnMouseClicked(e->{initcatcombo();});
        
        afficherprod();
        loadDataprod();
        setCellValueFromTableToTextFieldprod();
        searchProduct();
        //exportProduct();
    }
   
 private void affichercat(){

             Namec.setCellValueFactory(new PropertyValueFactory <>("categoryName"));
    }
 private void afficherprod(){

             Namep.setCellValueFactory(new PropertyValueFactory <>("productName"));
             Pricep.setCellValueFactory(new PropertyValueFactory <>("productPrice"));
             idcp.setCellValueFactory(new PropertyValueFactory <>("CategoryName"));
    }
  private void loadDataprod() {
   datap.clear();
         try {
           pst =con.prepareStatement("Select idProduct as idProduct , productName as productName , productPrice as productPrice ,categoryName as categoryName from product");

    rs=pst.executeQuery();
    
     while (rs.next()) {  
         int idProduct=rs.getInt("idProduct");
         String productName=rs.getString("productName");
         Float productPrice=rs.getFloat("productPrice");
         Category c = new Category(rs.getString("categoryName"));
             datap.add(new Produits(idProduct,productName,productPrice,c.getCategoryName()));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(Servicecategory.class.getName()).log(Level.SEVERE, null, ex);
       }
       tab_prod.setItems(datap);
    }
 private void loadDatacat() {
   data.clear();
         try {
           pst =con.prepareStatement("Select * from category");

    rs=pst.executeQuery();
     while (rs.next()) {                
             data.add(new Category(rs.getInt(1),rs.getString("categoryName")));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(Servicecategory.class.getName()).log(Level.SEVERE, null, ex);
       }
        tab_cat.setItems(data);
    }
    private void setCellValueFromTableToTextFieldprod(){
    tab_prod.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Produits prod=tab_prod.getItems().get(tab_prod.getSelectionModel().getSelectedIndex());
int i=prod.getIdProduct();
String idprod=Integer.toString(i);
tf_idprod.setText(idprod);
tf_nameprod.setText(prod.getProductName());
tf_prodprice.setText(Float.toString(prod.getProductPrice()));
        cmb_cat.getSelectionModel().select(prod.getCategoryName());}
});
    }
private void setCellValueFromTableToTextField(){
    tab_cat.setOnMouseClicked(new EventHandler<MouseEvent>(){
        @Override
        public void handle(MouseEvent event) {
Category cat=tab_cat.getItems().get(tab_cat.getSelectionModel().getSelectedIndex());
int i=cat.getIdcategory();
String idcat=Integer.toString(i);
tf_idcat.setText(idcat);
tf_nomcat.setText(cat.getCategoryName());
 
        }
});
    
}
private void clearTextField(){
    tf_idcat.clear();
    tf_nomcat.clear();
   
    
}
private void clearTextFieldprod(){
   tf_idprod.clear();
    tf_prodprice.clear();
    tf_nameprod.clear();
   cmb_cat.getSelectionModel().select(0);
    
}
 
private void initcatcombo() {
    ObservableList datacat=FXCollections.observableArrayList();
   cmb_cat.getSelectionModel().clearSelection();
   try {
           pst =con.prepareStatement("SELECT * from category");

    rs=pst.executeQuery();
     while (rs.next()) {                
             datacat.add(rs.getString(2));
     }       }
       catch (SQLException ex) {
           Logger.getLogger(Servicecategory.class.getName()).log(Level.SEVERE, null, ex);
       }
cmb_cat.setItems(datacat);

}
@FXML
public void exportProduct(ActionEvent event){
//btn_export.setOnAction(e->{
 String query ="Select * from product";
    try {
        pst=con.prepareStatement(query);
        rs= pst.executeQuery();
        
        
        XSSFWorkbook wb= new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("ProducDetails");
        XSSFRow header =sheet.createRow(0);
        header.createCell(0).setCellValue("IDproduct");
        header.createCell(1).setCellValue("productName");
        header.createCell(2).setCellValue("productPrice");
        header.createCell(3).setCellValue("CategoryName");
        
        int index=1;
        while(rs.next()){
            XSSFRow row=sheet.createRow(index);
            row.createCell(0).setCellValue(rs.getString("idProduct"));
            row.createCell(1).setCellValue(rs.getString("productName"));
            row.createCell(2).setCellValue(rs.getString("productPrice"));
            row.createCell(3).setCellValue(rs.getString("categoryName"));
            index++;
        }
        
        FileOutputStream fileOut= new FileOutputStream("Product.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        Alert alert =new Alert(AlertType.INFORMATION);
        alert.setTitle("information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Product Details Exported in Escel Sheet.");
        alert.showAndWait();
        
        pst.close();
        rs.close();
        
    } catch (SQLException  | FileNotFoundException ex) {
        Logger.getLogger(produitsController.class.getName()).log(Level.SEVERE, null, ex);} catch (IOException ex) {
        Logger.getLogger(produitsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
//});
}
public void searchProduct(){
prod_search.setOnKeyReleased(e->{
    if(prod_search.getText().equals("")){
        loadDataprod();
    }
    else{
        datap.clear();
          String sql = "Select * from product where productName LIKE '%"+prod_search.getText()+"%'"
                + "UNION Select * from product where productPrice LIKE '%"+prod_search.getText()+"%'" 
                   + "UNION Select * from product where categoryName LIKE '%"+prod_search.getText()+"%'";
    try {
      
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
        while(rs.next())
        {
            int idProduct=rs.getInt("idProduct");
         String productName=rs.getString("productName");
         Float productPrice=rs.getFloat("productPrice");
         Category c = new Category(rs.getString("categoryName"));
                        datap.add(new Produits(idProduct,productName,productPrice,c.getCategoryName()));
 
        }
        tab_prod.setItems(datap);
    } catch (SQLException ex) {
        Logger.getLogger(produitsController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
});
}
}
