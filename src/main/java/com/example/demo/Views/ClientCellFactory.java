package com.example.demo.Views;

import com.example.demo.Controllers.Client.ClientCellController;
import com.example.demo.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {

    @Override
    protected void updateItem(Client item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ClientCell.fxml"));
            ClientCellController controller = new ClientCellController(item);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
