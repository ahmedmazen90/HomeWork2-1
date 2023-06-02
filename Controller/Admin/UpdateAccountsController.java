/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Accounts;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MSI GF
 */
public class UpdateAccountsController implements Initializable {
    private Accounts oldAccounts;

    @FXML
    private Button updateAccountsBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField AccountsNumTF;
    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField BalanceTF;
    
    @FXML
    private TextField CurrnecyTF;
    @FXML
    private TextField CreationDateTF1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.oldAccounts = Controller.Admin.AccountsManagmentController.selectedAccountsToUpdate;
        
        //set text field's data the same as updatedUser data
        AccountsNumTF.setText(oldAccounts.getAccountNumber());
        UserNameTF.setText(oldAccounts.getUserName());
        CurrnecyTF.setText(oldAccounts.getCurreny());
        BalanceTF.setText(oldAccounts.getBalance());
        CreationDateTF1.setText(oldAccounts.getCreation_date());
      
    }    

    @FXML
    private void saveNewAccount(ActionEvent event) throws SQLException, ClassNotFoundException {
         
        String accountNumber= this.AccountsNumTF.getText();
        String username = UserNameTF.getText();
        String curreny = CurrnecyTF.getText();
        String balance = BalanceTF.getText();
        String creationDate = CreationDateTF1.getText();
      
       
        
        Accounts newUser = new Accounts(accountNumber,username,curreny,balance,creationDate);
        
     
        newUser.setId(oldAccounts.getId());
        
     
        newUser.update();
        
      
        Controller.Admin.AccountsManagmentController.updateStage.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account updated sccesse");
        alert.setContentText(" updated");
        alert.showAndWait();
    }

    @FXML
    private void cancelAccountCreation(ActionEvent event) {
        Controller.Admin.AccountsManagmentController.updateStage.close();
    }
    
}
