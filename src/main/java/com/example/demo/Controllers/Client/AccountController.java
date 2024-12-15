package com.example.demo.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountController implements Initializable {
    public Label checking_acc_num;
    public Label checking_limit;
    public Label checking_date_created;
    public Label checking_balance;
    public Label savings_acc_num;
    public Label savings_withdraw_lim;
    public Label savings_date_created;
    public Label savings_balance;
    public TextField ammount_to_sv;
    public Button transfer_to_sv_btn;
    public TextField amount_to_ch;
    public Button transfer_to_ch_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
