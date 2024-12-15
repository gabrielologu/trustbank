package com.example.demo;

import com.example.demo.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

// MAIN APP

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}

