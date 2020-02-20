package gestionrec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import GR.entities.Claim;
import GR.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Asus
 */
public class ServiceReclamation {
    

    private Connection con;
    private Statement ste;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    
    
 
    public int ajouter(Claim t) throws SQLException {
         ste = con.createStatement();
   
               
         String requeteInsert = "INSERT INTO `debooDBB`.`claim` (`message`, `answer`, `status`,`id`) SELECT '" +t.getMessage()+ "', '" + null + "', '" + t.getStatus()+"', '" + t.getUser().getId()+ "' FROM DUAL WHERE EXISTS("
                 + "SELECT users.id,users.roles FROM users WHERE (users.id=" + t.getUser().getId() + " AND users.roles='client'))"
                 + ";";
   
        return ste.executeUpdate(requeteInsert);
    }

 
    public int delete(Claim t) throws SQLException {
         String requestDelete = "delete from claim where idRec= ? AND id=?";
                 PreparedStatement statement = con.prepareStatement(requestDelete);
                 statement.setInt(1, t.getIdRec());
                 statement.setInt(2, t.getUser().getId());
                 return statement.executeUpdate();
    }

        

    public boolean update(Claim t) throws SQLException {
         
          String requestUpdate= "UPDATE `debooDBB`.`claim` SET `answer`=?,`status`=?,`message`=? WHERE EXISTS(SELECT users.id,users.roles FROM users WHERE (users.id=" + t.getUser().getId() + " AND users.roles='client')) AND claim.id =?"+ 
                  ";";
        PreparedStatement statement = con.prepareStatement(requestUpdate);
        
        statement.setString(1, t.getAnswer());
        statement.setString(2, t.getStatus());
        statement.setString(3, t.getMessage());
        
        statement.setInt(4, t.getUser().getId());
        
        if(statement.executeUpdate()==-1){
        return false;
        }
        return true;
        
    }

    public int ajouterreponseadmin(Claim t) throws SQLException {
         
          String requestUpdate= "UPDATE `debooDBB`.`claim` SET `answer`=?,`status`=? WHERE claim.idRec =?"+ 
                  ";";
        PreparedStatement statement = con.prepareStatement(requestUpdate);
        
        statement.setString(1, t.getAnswer());
        statement.setString(2, t.getStatus());        
        statement.setInt(3, t.getIdRec());
        
        return statement.executeUpdate();
        
    }
    
    public List<Claim> readAll() throws SQLException {
         List<Claim> claim=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select c.message,answer,status,u.id as id ,username,email,password,roles from claim c JOIN users u on c.id=u.id");
     while (rs.next())
     {                
               int idUser = rs.getInt("id");
               String username=rs.getString("username");
               String email=rs.getString("email");
               String password=rs.getString("password");
               String roles=rs.getString("roles");
               Users user = new Users(idUser,username,email,password,roles);
               String message=rs.getString("message");
               String answer=rs.getString("answer");
               String status=rs.getString("status");

               Claim c=new Claim(answer, status, message , user);
     claim.add(c);
     }
    return claim;
    }
/*
    stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery("SELECT * FROM order_line");
      
         ResultSetMetaData rm=rs.getMetaData();
        for(int i = 2 ; i <= rm.getColumnCount(); i++)
        System.out.print("  " + rm.getColumnName(i).toUpperCase() + " \t ");
                System.out.println("\n------------------------------------------");

        while (rs.next())
     {                

           for(int i = 2 ; i <= rm.getColumnCount(); i++)
             System.out.print("\t" + rs.getObject(i).toString() + "\t| ");
                    System.out.println("\n------------------------------------------");

     }
        }catch  (Exception e) {
          e.printStackTrace();
            
        }
    */
 
    public void afficherTrier() throws SQLException{
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT * from claim order by `status` ASC ");
             System.out.println("CIN\t\t\tMessage\t\tAnswer\t\tStatus:");  

     while (rs.next()){
         System.out.print(rs.getInt("id"));
         System.out.print("\t\t"+rs.getString("message"));
         System.out.print("\t\t"+rs.getString("answer"));
         System.out.print("\t\t"+rs.getString("status"));
         System.out.println();
}
    }
                    
    
    
 
     public  void stat() throws SQLException
      {
          
               ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT STATUS,COUNT(STATUS) as coutstatus FROM claim GROUP BY STATUS ");
                 System.out.println("Statistique des reclamations");  

             System.out.println("Status\t\tNombre de reclamations");  

     while (rs.next()){
                  System.out.print(rs.getString("status"));

         System.out.print("\t\t"+rs.getInt("coutstatus"));
       
         System.out.println();
}

           
      }
     
     
     public void rechercheAvance(String id)throws SQLException{
        ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select firstName , lastName,message,answer,status from claim join users using(id) where `id` ="+id);
             System.out.println("Name\t\t\tMessage\t\tAnswer\t\tStatus:");  

     while (rs.next()){
         
         System.out.print(rs.getString("firstName")+" "+rs.getString("lastName"));
         System.out.print("\t\t"+rs.getString("message"));
         System.out.print("\t\t"+rs.getString("answer"));
         System.out.print("\t\t"+rs.getString("status"));
         System.out.println();
     }
     
     }

  
    public void noterEmp() throws SQLException {
        
    }


    
    
}
 