module com.example.orgslf4l {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.orgslf4l to javafx.fxml;
    exports com.example.orgslf4l;
}