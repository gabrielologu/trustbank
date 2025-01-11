package com.example.demo;

import com.example.demo.Models.Model;
import javafx.application.Application;
import javafx.stage.Stage;

// MAIN APP
// make sure you are using -cp demo on the build and run configuration

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}

