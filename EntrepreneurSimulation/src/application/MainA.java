
package application;

import controllers.*;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import static java.lang.System.in;
import java.util.Hashtable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

public class MainA extends Application 
{
     Stage primaryStage;
    private AnchorPane root;
    public Hashtable<String, Employee> employees = new Hashtable<String,Employee>();
    public Hashtable<String, Employee> employeesHired = new Hashtable<String,Employee>();
    
    @Override
    public void start(Stage primaryStage) 
    {
         try 
        {
            //BufferedReader br = new BufferedReader(new FileReader("src\\dataFiles\\employeeData.txt"));
            BufferedReader br = new BufferedReader(new FileReader("src/dataFiles/employeeData.txt"));
            String strLine;
       
            while ((strLine = br.readLine()) != null) 
            {
                 JSONObject obj = new JSONObject(strLine);                  //extract employee info from datafile
                 String name = obj.get("name").toString();
                 int wage = (int)obj.get("wage");
                 
                 Employee empObject = new Employee(name, wage);      //create employee objects
                 employees.put(name, empObject);                               //store employee object into hashtable
                 
                 //System.out.println(name + " "+wage);
                
            }
            in.close();
        } 
        catch (Exception e) 
        {   //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        } 
        System.out.println("Employee Size is " +employees.size());
         System.out.println("Hired Size is " +employeesHired.size());
         
        //Image image = new Image("images/employeeImage.png");
         //ImageView imageView1 = new ImageView("images/employeeImage.png");
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The Entreprenuership Game");
        openHRScreen();
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }
    //___________________________________________________HR SCREEN
    public void openHRScreen() 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainA.class.getResource("/fxmlFiles/HR.fxml"));

            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
           HRController controller = loader.getController();
           //controller.setMainA(this);
           controller.setMainA(this);
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    //____________________________________________________HIRE SCREEN
    public void openHireScreen()
    {
         try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainA.class.getResource("/fxmlFiles/hire.fxml"));
            
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            HireController controller = loader.getController();
           controller.setMainA(this);
        } 
         catch (IOException e)
         {
             e.printStackTrace();
         }
    }
    //____________________________________________________FIRE SCREEN
    public void openFireScreen()
    {
         try 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainA.class.getResource("/fxmlFiles/fire.fxml"));
            
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            FireController controller = loader.getController();
            controller.setMainA(this);
        } 
         catch (IOException e)
         {
             e.printStackTrace();
         }
        
    }
}
