package com.example.demo.Controllers;

import com.example.demo.Models.Model;
import com.example.demo.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

// LOGIN CONTROLLER // LOGIN CONTROLLER // LOGIN CONTROLLER // LOGIN CONTROLLER // LOGIN CONTROLLER

public class HelloController implements Initializable {
    public Label choose_account_label;
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_label;
    public TextField payee_address;
    public Label password_label;
    public TextField password_input;
    public Button login_button;
    public Label error_display;
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_button.setOnAction(event -> onLogin());
    }

    private void onLogin(){
        Stage stage = (Stage) error_display.getScene().getWindow();
        if(Model.getInstance().getViewFactory().getLoginAccountType()==AccountType.CLIENT){
            // Evaluate the credentials
            Model.getInstance().evaluateClientCred(payee_address.getText(), password_input.getText());
            if(Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                // Close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            }
            else{
                payee_address.setText("");
                password_input.setText("");
                error_display.setText("No Such Login Credentials");
            }

        }
        else {
            // Evaluate Admin Login Credentials
            Model.getInstance().evaluateAdminCred(payee_address.getText(), password_input.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAdminWindow();
                // Close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else{
                payee_address.setText("");
                password_input.setText("");
                error_display.setText("No Such Login Credentials");
            }
        }
    }

    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        // Change Payee Address Label Accordingly
        if(acc_selector.getValue()==AccountType.ADMIN){
            payee_address_label.setText("Username:");
        } else{
            payee_address_label.setText("Payee Address:");
        }
    }
}