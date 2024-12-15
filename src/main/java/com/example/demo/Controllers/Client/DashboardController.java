package com.example.demo.Controllers.Client;

import com.example.demo.Models.Model;
import com.example.demo.Models.Transaction;
import com.example.demo.Views.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Text user_name;
    public Label checking_sum;
    public Label checking_acc_num;
    public Label savings_sum;
    public Label savings_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_list;
    public TextField payee_address;
    public TextField amount_dollars;
    public TextArea message_optional;
    public Button send_button;
    public Label login_date;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        bindData();
        initLatestTransactions();
        transaction_list.setItems(Model.getInstance().getLatestTransactions());
        transaction_list.setCellFactory(e->new TransactionCellFactory());
        send_button.setOnAction(e -> onSendMoney());
        accountSummary();
    }

    private void bindData(){
        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, " + LocalDate.now());
        checking_sum.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().balanceProperty().asString());
        checking_acc_num.textProperty().bind(Model.getInstance().getClient().checkingAccountProperty().get().accountNumberProperty());
        savings_sum.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().balanceProperty().asString());
        savings_acc_num.textProperty().bind(Model.getInstance().getClient().savingsAccountProperty().get().accountNumberProperty());

    }

    private void initLatestTransactions(){
        if(Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions();
        }
    }

    private void onSendMoney(){
        String receiver  = payee_address.getText();
        double amount = Double.parseDouble(amount_dollars.getText());
        String message = message_optional.getText();
        String sender = Model.getInstance().getClient().payeeAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        // Subtract from sender's savings account
        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        // Update savings account balance in the client object
        Model.getInstance().getClient().savingsAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSavingsAccountBalance(sender));
        // Record new transaction
        Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);
        // Clear the fields
        payee_address.setText("");
        amount_dollars.setText("");
        message_optional.setText("");
    }

    // Method calculates all expenses and income

    private void accountSummary(){
        double income = 0;
        double expenses = 0;
        if (Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }
        for(Transaction transaction : Model.getInstance().getAllTransactions()){
            if(transaction.senderProperty().get().equals(Model.getInstance().getClient().payeeAddressProperty().get())){
                expenses = expenses + transaction.amountProperty().get();
            }
            else{
                income = income + transaction.amountProperty().get();
            }
        }
        income_lbl.setText("+ $" + income);
        expense_lbl.setText("- $" + expenses);
    }

}
