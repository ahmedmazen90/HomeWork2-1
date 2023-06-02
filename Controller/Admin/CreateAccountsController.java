/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Accounts;
import Model.User;
import View.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author MSI GF
 */
public class CreateAccountsController implements Initializable {

   
    @FXML
    private Button cancelBtn;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField CreationDateTF;
    @FXML
    private TextField CurrencyTF;
    @FXML
    private TextField BalanceTF;
    @FXML
    private Button SaveAccountBtn;
    @FXML
    private TextField AccountsNumTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    

 @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
          ViewManager.adminPage.changeSceneToAccountsManagment();
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
        String accountNumber=AccountsNumTF.getText();
        String username = UserNameTF.getText();
        String currency=CurrencyTF.getText();
        String balance=BalanceTF.getText();
        String creationDate = CreationDateTF.getText();

        
        Accounts account = new Accounts(accountNumber,username,currency,balance,creationDate);
        account.save();
        AccountsNumTF.setText("");
        UserNameTF.setText("");
        CurrencyTF.setText("");
        BalanceTF.setText("");
        CreationDateTF.setText("");
        

        ViewManager.adminPage.changeSceneToAccountsManagment();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account inserted");
        alert.setContentText("Account inserted");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
         ViewManager.adminPage.changeSceneToAccountsManagment();
    }
    
}
