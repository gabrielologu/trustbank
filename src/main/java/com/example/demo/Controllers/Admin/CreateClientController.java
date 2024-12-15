package com.example.demo.Controllers.Admin;

import com.almasb.fxgl.entity.SpawnData;
import com.example.demo.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {

    public TextField firstname_box;
    public TextField lastname_box;
    public TextField password_box;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox ch_acc_box;
    public TextField checking_acc_balance_box;
    public CheckBox sv_acc_box;
    public TextField savings_acc_balance_box;
    public Button create_client_btn;
    public Label error_label;

    private String payeeAddress;
    private boolean createCheckingAccountFlag = false;
    private boolean createSavingsAccountFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());
        pAddress_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        });
        ch_acc_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                createCheckingAccountFlag = true;
            }
        });
        sv_acc_box.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                createSavingsAccountFlag = true;
            }
        });
    }

    private void createClient() {
        // create checking account
        if(createCheckingAccountFlag) {
            createAccount("Checking");
        }

        if(createSavingsAccountFlag) {
            createAccount("Savings");
        }

        // Create Client

        String fName = firstname_box.getText();
        String lName = lastname_box.getText();
        String password = password_box.getText();
        Model.getInstance().getDatabaseDriver().createClient(fName,lName,payeeAddress, password, LocalDate.now());
        error_label.setStyle("-fx-text-fill:#25f225; -fx-font-size:1.3em; -fx-font-weight:bold");
        error_label.setText("Client created successfully");
        emptyFields();

    }

    private void createAccount(String accountType) {
        double balance = Double.parseDouble(checking_acc_balance_box.getText());
        // Generate Account Number
        String firstSection = "3201";
        String lastSection = Integer.toString((new Random()).nextInt(9999)+1000);
        String accountNumber = firstSection + " " + lastSection;
        // Create the checking account or savings
        if(accountType.equals("Checking")){
            Model.getInstance().getDatabaseDriver().createCheckingAccount(payeeAddress, accountNumber,10, balance);
        }
        else{
            Model.getInstance().getDatabaseDriver().createSavingsAccount(payeeAddress,accountNumber,2000, balance);
        }
    }

    private void onCreatePayeeAddress(){
        if(firstname_box.getText() != null & lastname_box.getText() != null){
            pAddress_lbl.setText(payeeAddress);
        }
    }

    private String createPayeeAddress(){
        int id = Model.getInstance().getDatabaseDriver().getLastClientsId() + 1;
        char fChar = Character.toLowerCase(firstname_box.getText().charAt(0));
        return "@"+fChar+lastname_box.getText()+id;
    }

    private void emptyFields() {
        firstname_box.setText("");
        lastname_box.setText("");
        password_box.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        ch_acc_box.setSelected(false);
        checking_acc_balance_box.setText("");
        sv_acc_box.setSelected(false);
        savings_acc_balance_box.setText("");

    }


}
