/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import static Controller.Admin.UsersManagmentController.selectedUserToUpdate;
import static Controller.Admin.UsersManagmentController.updateStage;
import Model.Accounts;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Yahya
 */
public class AccountsManagmentController implements Initializable {
    public static Accounts selectedAccountsToUpdate;
public static Stage updateStage;
    @FXML
    private Button usersManagmentPageBtn;
    @FXML
    private Button accountsPageBtn;
    @FXML
    private Button operationsPageBtn;
    @FXML
    private Button createNewAccountrBtn;
    @FXML
    private Button showAllAccountsBtn;
    @FXML
    private Button updateSelectedAccountBtn;
    @FXML
    private Button deleteSelectedAccountBtn;
    @FXML
    private Button searchAccountBtn;
    @FXML
    private TextField accontSearchTF;
      @FXML
    private TableView<Accounts> AccountsTableView;
    @FXML
    private TableColumn<Accounts, Integer> idCol;
    @FXML
    private TableColumn<Accounts, Integer> AccountsNumCol;
    @FXML
    private TableColumn<Accounts, String> UserNameCol;
    @FXML
    private TableColumn<Accounts, String> CurrencyCol;
    @FXML
    private TableColumn<Accounts, Double> BalanceCol;
    @FXML
    private TableColumn<Accounts, String> CreationDateCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idCol.setCellFactory(new PropertyValueFactory("id"));
        AccountsNumCol.setCellFactory(new PropertyValueFactory("AccountsNum"));
        UserNameCol.setCellFactory(new PropertyValueFactory("username"));
        CurrencyCol.setCellFactory(new PropertyValueFactory("currency"));
        BalanceCol.setCellFactory(new PropertyValueFactory(" Balance"));
        CreationDateCol.setCellFactory(new PropertyValueFactory("CreationDate"));
    }    

    @FXML
    private void showUsersManagmentPage(ActionEvent event) {
         ViewManager.adminPage.changeSceneToUsersManagment();
    }

    @FXML
    private void showAccountsPage(ActionEvent event) {
    }

    @FXML
    private void showOperationsPage(ActionEvent event) {
    }

    @FXML
    private void showAccountCreationPage(ActionEvent event) {
        ViewManager.adminPage.changeSceneToCreateAccounts();
    }

    @FXML
    private void showAllAccounts(ActionEvent event) throws SQLException, ClassNotFoundException {
          ObservableList<Accounts> AccountList =
      FXCollections.observableArrayList(Accounts.getAllAccounts());
      
      AccountsTableView.setItems(AccountList); 
      
    }

    @FXML
    private void updateSelectedAccount(ActionEvent event) throws IOException {
        //check if there is an Account selected from the TableView
        if(AccountsTableView.getSelectionModel().getSelectedItem() != null){
        //store the selected Accounts from the TableView in our global var Account selectedUserToUpdate   
        selectedAccountsToUpdate = AccountsTableView.getSelectionModel().getSelectedItem();
        //load update page fxml
        FXMLLoader loaderUpdate = new FXMLLoader(getClass().getResource("/View/AdminFXML/UpdateAccounts.fxml"));
        Parent rootUpdate = loaderUpdate.load();     
        Scene updateUserScene = new Scene(rootUpdate); 
        //store loaded fxml in our global stage updateStage and show it
        updateStage = new Stage();
        updateStage.setScene(updateUserScene);
        updateStage.setTitle("Update user " + selectedAccountsToUpdate.getUserName() );
        updateStage.show();
    }
    }
    @FXML
    private void deleteSelectedAccount(ActionEvent event) {
   
        //check if there is an user selected from the TableView
        if(AccountsTableView.getSelectionModel().getSelectedItem() != null){
            //store the selected user from the TableView in new user object
            Accounts selectedUser = AccountsTableView.getSelectionModel().getSelectedItem();
            
            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("Account delete");
            deleteConfirmAlert.setContentText(" sure to delete this Account ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    //delete the selected Account from database table using delete method in our Account model
                    selectedUser.delete();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AccountsManagmentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
            deletedSuccessAlert.setTitle("Account deleted");
            deletedSuccessAlert.setContentText("Account deleted");
            deletedSuccessAlert.show();  
            }
            });
        
        }else{
        Alert warnAlert = new Alert(Alert.AlertType.WARNING);
        warnAlert.setTitle("Select  Account");
        warnAlert.setContentText(" select  Account from  table view");
        warnAlert.show();  
        }
    }
    

    @FXML
    private void searchForAnAccount(ActionEvent event) throws SQLException {
                String accountSearch=accontSearchTF.getText();
        ObservableList<Accounts> AccountList =
      FXCollections.observableArrayList(Accounts.searchAccountByNumber(accountSearch));
      if (AccountList != null) {
      AccountsTableView.setItems(AccountList);
            } else {
             System.out.println("Account not found.");
             
            }
    }
    
}

