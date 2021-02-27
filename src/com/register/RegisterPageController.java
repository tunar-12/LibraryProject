package com.register;

import com.dao.DaoImpl;
import com.model.Users;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterPageController implements Initializable {

    DaoImpl dao = new DaoImpl();
    @FXML
    private TextField nameTF;
    @FXML
    private TextField surnameTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
    private PasswordField confrimTF;
    @FXML
    private TextField mailTF;
    @FXML
    private TextField addressTF;
    @FXML
    private Button cancelBTN;
    @FXML
    private Button saveBTN;
    @FXML
    private Label warningLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void cancelPr(ActionEvent event) {
        nameTF.setText("");
        surnameTF.setText("");
        usernameTF.setText("");
        passwordTF.setText("");
        confrimTF.setText("");
        mailTF.setText("");
        addressTF.setText("");
warningLbl.setText("");
    }

    @FXML
    private void savePr(ActionEvent event) {
        String name = nameTF.getText().trim();
        String surname = surnameTF.getText().trim();
        String username = usernameTF.getText().trim();
        String password = passwordTF.getText().trim();
        String confirm = confrimTF.getText().trim();
        String mail = mailTF.getText().trim();
        String address = addressTF.getText().trim();

        if (name.equalsIgnoreCase("") || surname.equalsIgnoreCase("") || username.equalsIgnoreCase("") || password.equalsIgnoreCase("") || confirm.equalsIgnoreCase("") || mail.equalsIgnoreCase("") || address.equalsIgnoreCase("")) {
            warningLbl.setText("Zehmet olmazsa butun xanalari doldurun!");
        } else {
            if (password.equalsIgnoreCase(confirm)) {
                if(password.length()<6){
                   warningLbl.setText("Your password is least at 6 characters !");
                } else{
                    int a=0;
                    for(int i=0;i<mail.length();i++){
                        char herf=mail.charAt(i);
                        if(herf=='@'){
                            a++;
                        }
                    }
                    if(a==1){
                          if (dao.checkUserbyUsername(username)) {
                   warningLbl.setText("Nick istifade olunur!");
                } else {
                    Users user = new Users(0, name, surname, username, password, mail, address);
                    boolean isAdded=false;
                     isAdded = dao.createAccount(user);
                    if (isAdded) {
                        warningLbl.setText("Account ugurla yaradildi!");
                        Stage oldstage = (Stage) saveBTN.getScene().getWindow();
                        oldstage.close();
                    } else {
                        warningLbl.setText("Account yaradila bilmedi!");
                    }

                }
                    }else{
                      warningLbl.setText("Bele bir mail tapilmadi!");
                    }
              
                }
            } else {
               warningLbl.setText("Parolu sehv tekrarladiniz!");
            }

        }

    }

}
