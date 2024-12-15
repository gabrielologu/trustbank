package com.example.demo.Controllers.Client;

import com.example.demo.Models.Model;
import com.example.demo.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label transaction_date;
    public Label lastname_sender_lbl;
    public Label firstname_sender_lbl;
    public Button bell_btn;
    public Label amount_transfered_lbl;

    private final Transaction transaction;

    public TransactionCellController(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lastname_sender_lbl.textProperty().bind(transaction.senderProperty());
        firstname_sender_lbl.textProperty().bind(transaction.receiverProperty());
        amount_transfered_lbl.textProperty().bind(transaction.amountProperty().asString());
        transaction_date.textProperty().bind(transaction.dateProperty().asString());
        bell_btn.setOnAction(event -> Model.getInstance().getViewFactory().showMessageWindow(transaction.senderProperty().get(), transaction.messageProperty().get()));
        transactionIcons();
    }

    private void transactionIcons(){
        if(transaction.senderProperty().get().equals(Model.getInstance().getClient().payeeAddressProperty().get())){
            in_icon.setFill(Color.rgb(240,240,240));
            out_icon.setFill(Color.RED);
        }
        else{
            in_icon.setFill(Color.GREEN);
            out_icon.setFill(Color.rgb(240,240,240));
        }
    }

}
