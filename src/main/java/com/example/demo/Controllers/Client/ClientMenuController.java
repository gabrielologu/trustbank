package com.example.demo.Controllers.Client;

import com.example.demo.Models.Model;
import com.example.demo.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transactions_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(event -> onDashboard());
        transactions_btn.setOnAction(event -> onTransactions());
        accounts_btn.setOnAction(event-> onAccounts());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onDashboard(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }


    private void onTransactions(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts(){
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout(){
        // Get Stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        // close the client windows
        Model.getInstance().getViewFactory().closeStage(stage);
        // show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Client Login Success Flag To False
        Model.getInstance().setClientLoginSuccessFlag(false);
    }

}
