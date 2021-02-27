
package com.page;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class CorePageController implements Initializable {
 
    @FXML
    private Button logoutBtn;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> theme;
    @FXML
    private TableColumn<?, ?> author;
    @FXML
    private TableColumn<?, ?> pagecount;
    @FXML
    private TableColumn<?, ?> amount;
    @FXML
    private TableColumn<?, ?> language;
    @FXML
    private TableColumn<?, ?> status;
    @FXML
    private Button showBtn;
    @FXML
    private Button hideBtn;
    @FXML
    private Label warningLbl;
    @FXML
    private Label welcomeLbl;
    @FXML
    private TextField bookNew;
    @FXML
    private TextField authorNew;
    @FXML
    private TextField pageCountNew;
    @FXML
    private TextField amountNew;
    @FXML
    private ComboBox<?> comboBoxNewLang;
    @FXML
    private Label addBtnNew;
    @FXML
    private ComboBox<?> comboBoxNewTheme;
    @FXML
    private Label addBtnNew1;
    @FXML
    private Label saveBtnNew;
    @FXML
    private Label updateBtnNew;
    @FXML
    private TextField searchTxt;
    @FXML
    private TextField minAmount;
    @FXML
    private TextField maxAmount;
    @FXML
    private Button filterAmount;
    @FXML
    private TextField minCount;
    @FXML
    private TextField maxCount;
    @FXML
    private Button filterPageCount;
    @FXML
    private Button clearAllFilters;
    @FXML
    private CheckBox soldCheckBox;
    @FXML
    private CheckBox unsoldCheckBox;
    @FXML
    private CheckBox allCheckBox;
    @FXML
    private TableView<?> table;
    @FXML
    private Button buyBtnMore;
    @FXML
    private Button filterBtnMore;
    @FXML
    private Button deleteBtnMore;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void logoutPr(ActionEvent event) {
      try {
          Stage s=new Stage();
        s.setTitle("Login and Register");
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/loginPage.fxml"));
           s.getIcons().add(new Image("/com/icons/user.png"));
           Parent root = loader.load();
           Scene scene = new Scene(root);
           s.setScene(scene);
           s.show();
         
       }catch (Exception e){
           e.printStackTrace();
       }
       Stage oldstage = (Stage) logoutBtn.getScene().getWindow();
                        oldstage.close();
    }

    @FXML
    private void labelViewOrMousePressed(MouseEvent event) {
    }

    @FXML
    private void showPr(ActionEvent event) {
    table.setVisible(true);
    
    }

    @FXML
    private void hidePr(ActionEvent event) {
    table.setVisible(false);
    }

    @FXML
    private void clearAllFiltersPr(ActionEvent event) {
    searchTxt.setText("");
    minAmount.setText("");
    maxAmount.setText("");
    minCount.setText("");
    maxCount.setText("");
    }
    
}
