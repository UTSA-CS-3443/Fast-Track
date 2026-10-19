module edu.utsa.cs3443.appityappapp {
    requires javafx.controls;
    requires javafx.fxml;
    opens edu.utsa.cs3443.appityappapp to javafx.fxml;
    exports edu.utsa.cs3443.appityappapp;
}