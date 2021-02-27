
package com.login;

import com.model.Users;
import com.dao.DaoImpl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginPageController implements Initializable {
      DaoImpl dao=new DaoImpl();
    @FXML
    private Label exceptionLbl;
    @FXML
    private Label redTick;
    @FXML
    private TextField nameTxt;
    @FXML
    private PasswordField passTxt;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Label redTick2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       redTick.setVisible(false);
       redTick2.setVisible(false);
    }    

    @FXML
    private void checkName(KeyEvent event) {
   exceptionLbl.setText("");
    if(nameTxt.getText().trim().equalsIgnoreCase("")){
        redTick.setVisible(true);
    }else{
        redTick.setVisible(false);
        if(nameTxt.getText().trim().contains(",")){
            redTick.setVisible(true);
            exceptionLbl.setText("Yazinizda vergul isaresi var !");
            
        }
    }
    
    }

    @FXML
    private void checkPass(KeyEvent event) {
      exceptionLbl.setText("");
    if(passTxt.getText().trim().equalsIgnoreCase("")){
        redTick2.setVisible(true);
    }else{
        redTick2.setVisible(false);
        if(passTxt.getText().trim().contains(",")){
            redTick2.setVisible(true);
            exceptionLbl.setText("Yazinizda vergul isaresi var !");
            
        }
    }
    
    }

    @FXML
    private void loginPr(ActionEvent event) {
    String username = nameTxt.getText().trim();
    String password = passTxt.getText().trim();
  if(username.equalsIgnoreCase("")||password.equalsIgnoreCase("")){
      exceptionLbl.setText("Butun xanalari doldurun !");
  }else{
      Users user = dao.checkUser(username, password);
      if(user==null){
          exceptionLbl.setText("Login ve ya parol yanlisdir !");
      }else{
          
           try {
        Stage stage = new Stage();
           stage.setTitle("Library");
           stage.setResizable(false);
           stage.initModality(Modality.APPLICATION_MODAL);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/page/corePage.fxml"));
           stage.getIcons().add(new Image("com/icons/books.png"));
           Parent root = loader.load();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
           Stage oldstage = (Stage) loginButton.getScene().getWindow();
                        oldstage.close();
    }catch (Exception e){
        e.printStackTrace();
    }
         
      }
      
      
      
      
  }
    
    
    }

    @FXML
    private void registerPr(ActionEvent event) {
    try {
        Stage stage = new Stage();
           stage.setTitle("Register");
           stage.setResizable(false);
           stage.initModality(Modality.APPLICATION_MODAL);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/register/registerPage.fxml"));
           stage.getIcons().add(new Image("com/icons/edit.png"));
           Parent root = loader.load();
           Scene scene = new Scene(root);
           stage.setScene(scene);
           stage.show();
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    
}