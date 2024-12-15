package com.example.demo.Controllers.Admin;

import com.example.demo.Models.Client;
import com.example.demo.Models.Model;
import com.example.demo.Views.ClientCellFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    public TextField deposit_sum;
    public TextField payee_search_box;
    public Button search_btn_payee;
    public TextField amount_deposit_field;
    public Button deposit_amount_btn;
    public ListView<Client> result_listview;

    private Client client;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn_payee.setOnAction(e-> onClientSearch());
        deposit_amount_btn.setOnAction(e-> onDeposit());
    }

    private void onClientSearch(){
        ObservableList<Client> searchResults = Model.getInstance().searchClient(payee_search_box.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e->new ClientCellFactory());
        client = searchResults.get(0);
    }

    private void onDeposit() {
        double amount = Double.parseDouble(amount_deposit_field.getText());
        double newBalance = amount + client.savingsAccountProperty().get().balanceProperty().get();
        if(amount_deposit_field.getText() != null){
            Model.getInstance().getDatabaseDriver().depositSavings(client.payeeAddressProperty().get(), newBalance);
        }
        emptyFields();
    }

    private void emptyFields(){
        payee_search_box.setText("");
        amount_deposit_field.setText("");
    }

}
