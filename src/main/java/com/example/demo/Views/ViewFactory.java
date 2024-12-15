package com.example.demo.Views;

import com.example.demo.Controllers.Admin.AdminController;
import com.example.demo.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private AccountType loginAccountType;

    // Client Views

    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;


    // Admin Views

    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane createClientsView;
    private AnchorPane ClientsView;
    private AnchorPane DepositView;

    public ViewFactory() {
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public AnchorPane getDashboardView() {
        if(dashboardView == null) {
            try{
                dashboardView = new FXMLLoader(getClass().getResource("/FXML/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if(transactionsView == null) {
            try{
                transactionsView = new FXMLLoader(getClass().getResource("/FXML/Transactions.fxml")).load();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if(accountsView == null) {
            try{
                accountsView = new FXMLLoader(getClass().getResource("/FXML/Accounts.fxml")).load();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

        // ADMIN VIEWS SECTION //

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getCreateClientsView() {
        if(createClientsView == null) {
            try{
                createClientsView = new FXMLLoader(getClass().getResource("/FXML/CreateClient.fxml")).load();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createClientsView;
    }

    public AnchorPane getClientsView() {
        if(ClientsView == null) {
            try{
                ClientsView = new FXMLLoader(getClass().getResource("/FXML/Clients.fxml")).load();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ClientsView;
    }

    public AnchorPane getDepositView() {
        if(DepositView == null) {
            try{
                DepositView = new FXMLLoader(getClass().getResource("/FXML/Deposit.fxml")).load();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return DepositView;
    }

    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        createStage(loader);
    }

    public void showMessageWindow(String pAddress, String messageText){
        StackPane pane = new StackPane();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        vBox.getChildren().addAll(sender, message);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane, 300, 100);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank2.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Message");
        stage.setScene(scene);
        stage.show();
    }


    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try{
            scene = new Scene(loader.load());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank2.png"))));
        stage.setResizable(false);
        stage.setTitle("Trust Bank");
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }




}
