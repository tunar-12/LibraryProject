package com.page;


import com.dao.DaoImpl;
import static com.login.LoginPageController.username1;
import com.model.Books;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CorePageController implements Initializable {
      DaoImpl dao = new DaoImpl();
    @FXML
    private Button logoutBtn;
    @FXML
    private TableColumn<Books, Integer> id;
    @FXML
    private TableColumn<Books, String> name;
    @FXML
    private TableColumn<Books, String> theme;
    @FXML
    private TableColumn<Books, String> author;
    @FXML
    private TableColumn<Books, Integer> pagecount;
    @FXML
    private TableColumn<Books, Double> amount;
    @FXML
    private TableColumn<Books, String> language;
    @FXML
    private TableColumn<Books, String> status;
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
    private ComboBox<String> comboBoxNewLang;
    @FXML
    private ComboBox<String> comboBoxNewTheme;
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
    private TableView<Books> table;
    @FXML
    private Button buyBtnMore;
    @FXML
    private Button filterBtnMore;
    @FXML
    private Button deleteBtnMore;
    @FXML
    private Button languageAdd;
    @FXML
    private Button themeAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLanguageCb();
        loadThemeCb();
        table.setVisible(false);
        welcomeLbl.setText(username1);
        loadColumn();
        loadRows();
    }

    @FXML
    private void logoutPr(ActionEvent event) {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure", "Warning", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            try {
                Stage s = new Stage();
                s.setTitle("Login and Register");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/login/loginPage.fxml"));
                s.getIcons().add(new Image("/com/icons/user.png"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                s.setScene(scene);
                s.show();
                Stage oldstage = (Stage) logoutBtn.getScene().getWindow();
                oldstage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
        }

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

    private void loadLanguageCb() {

        comboBoxNewLang.getItems().add("Azerbaycanca");
        comboBoxNewLang.getItems().add("Ingilis");
        comboBoxNewLang.getItems().add("Çinçe");
        comboBoxNewLang.getItems().add("Amerikanca");
          comboBoxNewLang.getItems().add("Turkce");
    }

    private void loadThemeCb() {
        comboBoxNewTheme.getItems().add("Science Fiction");
        comboBoxNewTheme.getItems().add("Roman");
        comboBoxNewTheme.getItems().add("Tarixi");
        comboBoxNewTheme.getItems().add("Derslik");
        comboBoxNewTheme.getItems().add("Elmi");
        comboBoxNewTheme.getItems().add("Dedektiv");
        comboBoxNewTheme.getItems().add("Psixoloq");
        comboBoxNewTheme.getSelectionModel().selectFirst();
    }

    @FXML
    private void languageAddBtnPr(ActionEvent event) {
        String newLang = JOptionPane.showInputDialog(null, "Your New Language");
        if (newLang.equalsIgnoreCase("")) {
            warningLbl.setText("You didn't write language!!");
        } else {
            if (comboBoxNewLang.getItems().contains(newLang)) {
                warningLbl.setText("It already exists!!");
            } else {
                if (comboBoxNewLang.getItems().add(newLang)) {
                    warningLbl.setText("New Language successfully added!");

                }
            }
        }
    }

    @FXML
    private void themeAddBtnPr(ActionEvent event) {
        String newTheme = JOptionPane.showInputDialog(null, "Your New Theme");
        if (newTheme.equalsIgnoreCase("")) {
            warningLbl.setText("You didn't write language!!");
        } else {
            if (comboBoxNewTheme.getItems().contains(newTheme)) {
                warningLbl.setText("It already exists!!");
            } else {
                if (comboBoxNewTheme.getItems().add(newTheme)) {
                    warningLbl.setText("New Theme successfully added!!");

                }
            }
        }
    }

    private void loadColumn(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        pagecount.setCellValueFactory(new PropertyValueFactory<>("pageCount"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        language.setCellValueFactory(new PropertyValueFactory<>("language"));
        theme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
      private void loadRows() {
        table.getItems().addAll(dao.getAllBooks());
    }

}
