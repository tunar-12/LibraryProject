package com.page;

import com.login.LoginPageController;
import com.dao.DaoImpl;
import com.model.Books;
import com.model.Users;
import java.net.URL;
import java.util.List;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CorePageController implements Initializable {
//       LoginPageController login = new LoginPageController();
//     String  userna = login.username1;
//    
//    private Users user1;
    Integer selectedId = 0;  // id-ni get etmek ucun bir de 
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
    private Button saveBtnNew;
    @FXML
    private Button updateBtnNew;
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
       welcomeLbl.setText(LoginPageController.username1);
        loadColumn();
        loadRows();
    }

//    public void setUser(Users selectedUser) {  
//        this.user1 = selectedUser;
//    }

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
        Books book = table.getSelectionModel().getSelectedItem();
        bookNew.setText(book.getName());
        authorNew.setText(book.getAuthor());
        pageCountNew.setText(book.getPageCount() + "");
        amountNew.setText(book.getAmount() + "");
        comboBoxNewLang.getSelectionModel().select(book.getLanguage());
        comboBoxNewTheme.getSelectionModel().select(book.getTheme());
        selectedId = book.getId();
    }

    @FXML
    private void showPr(ActionEvent event) {
        table.setVisible(true);
//        welcomeLbl.setText(user1.getName() + " " + user1.getSurname());
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
        refresh();
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

    private void loadColumn() {
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
    //table refreshing

    public void refresh() {
        table.getItems().clear();
        table.getItems().addAll(dao.getAllBooks());
    }

    @FXML
    private void saveBtnPressedNew(ActionEvent event) {
        warningLbl.setText("");
        if (bookNew.getText().equalsIgnoreCase("") || authorNew.getText().equalsIgnoreCase("") || pageCountNew.getText().equalsIgnoreCase("") || amountNew.getText().equalsIgnoreCase("")) {
            warningLbl.setText("Butun xanalari doldurun!");
        } else {
            try {
                Books newBook = new Books();
                newBook.setName(bookNew.getText());
                newBook.setTheme(comboBoxNewTheme.getSelectionModel().getSelectedItem());
                newBook.setAuthor(authorNew.getText());
                newBook.setPageCount(pageCountNew.getText());
                newBook.setAmount(Double.parseDouble(amountNew.getText()));
                newBook.setLanguage(comboBoxNewLang.getSelectionModel().getSelectedItem());
                if (dao.addBook(newBook)) {
                    warningLbl.setText("Yeni kitab ugurla elave olundu!");
                    refresh();
                } else {
                    warningLbl.setText("Elave oluna bilmedi!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void updateBtnPressedNew(ActionEvent event) {
        warningLbl.setText("");
        if (bookNew.getText().equalsIgnoreCase("") || authorNew.getText().equalsIgnoreCase("") || pageCountNew.getText().equalsIgnoreCase("") || amountNew.getText().equalsIgnoreCase("")) {
            warningLbl.setText("Butun xanalari doldurun!");
        } else {
            try {
                Books updateBook = new Books();
                updateBook.setId(selectedId);
                updateBook.setName(bookNew.getText());
                updateBook.setAuthor(authorNew.getText());
                updateBook.setPageCount(pageCountNew.getText());
                updateBook.setAmount(Double.parseDouble(amountNew.getText()));
                updateBook.setTheme(comboBoxNewTheme.getSelectionModel().getSelectedItem());
                updateBook.setLanguage(comboBoxNewLang.getSelectionModel().getSelectedItem());
                if (dao.updateBook(updateBook)) {
                    warningLbl.setText("Ugurla deyisdi!");
                    refresh();
                } else {
                    warningLbl.setText("Deyismek olmadi!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void filterAmountPressed(ActionEvent event) {
        warningLbl.setText("");
        if (minAmount.getText().trim().equalsIgnoreCase("") || maxAmount.getText().trim().equalsIgnoreCase("")) {
            warningLbl.setText("Zehmet olmasa butun xanalari doldurun!");
        } else {
            Double minAmountTf = Double.parseDouble(minAmount.getText().trim());
            Double maxAmountTf = Double.parseDouble(maxAmount.getText().trim());
            List<Books> result = dao.filterBookByAmount(minAmountTf, maxAmountTf);
            table.getItems().clear();
            table.getItems().addAll(result);
        }
    }

    @FXML
    private void filterPageCount(ActionEvent event) {
        warningLbl.setText("");
        if (minCount.getText().trim().equalsIgnoreCase("") || maxCount.getText().trim().equalsIgnoreCase("")) {
            warningLbl.setText("Zehmet olmasa butun xanalari doldurun!");
        } else {
            Double min = Double.parseDouble(minCount.getText().trim());
            Double max = Double.parseDouble(maxCount.getText().trim());
            List<Books> result = dao.filterBookByPageCount(min, max);
            table.getItems().clear();
            table.getItems().addAll(result);
        }
    }

    @FXML
    private void buyBtnPressed(ActionEvent event) {
        boolean isUpdated = dao.updateBookByStatus(selectedId);
        if (isUpdated) {
            refresh();
            warningLbl.setText("Status deyisdi!");
        } else {
            warningLbl.setText("Status deyisilemedi!");
        }

    }

    @FXML
    private void filterBtnPressedMore(ActionEvent event) {
        if (soldCheckBox.isSelected()) {
            List<Books> soldBook = dao.filterBookByStatus("Sold");
            table.getItems().clear();
            table.getItems().addAll(soldBook);
        } else if (unsoldCheckBox.isSelected()) {
            List<Books> unSoldBook = dao.filterBookByStatus("Not Sold");  
            table.getItems().clear();
            table.getItems().addAll(unSoldBook);
        } else if (allCheckBox.isSelected()) {
            refresh();
        }
    }

    @FXML
    private void DeleteBtnPressed(ActionEvent event) {
        if (dao.deleteBook(selectedId)) {
            refresh();
            warningLbl.setText("Silindi!");
        } else {
            warningLbl.setText("Siline bilmedi!");
        }
    }

    @FXML
    private void searchBtnOnKeyRealised(KeyEvent event) {
        String keyword = searchTxt.getText().toUpperCase().trim();
        if (keyword.equalsIgnoreCase("")) {
            refresh();
        } else {
            List resultList = dao.searchBook(keyword);
            table.getItems().clear();
            table.getItems().addAll(resultList);
        }

    }

}
