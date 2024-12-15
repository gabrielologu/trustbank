package com.example.demo.Controllers.Client;

import com.example.demo.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {

    public Label fName_lbl;
    public Label lName_lbl;
    public Label handle_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label today_date_lbl;
    public Button delete_btn_lbl;

    private final Client item;

    public ClientCellController(Client item) {
        this.item = item;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.textProperty().bind(item.firstNameProperty());
        lName_lbl.textProperty().bind(item.lastNameProperty());
        handle_lbl.textProperty().bind(item.payeeAddressProperty());
        ch_acc_lbl.textProperty().bind(item.checkingAccountProperty().asString());
        sv_acc_lbl.textProperty().bind(item.savingsAccountProperty().asString());
        today_date_lbl.textProperty().bind(item.dateCreatedProperty().asString());

    }
}
