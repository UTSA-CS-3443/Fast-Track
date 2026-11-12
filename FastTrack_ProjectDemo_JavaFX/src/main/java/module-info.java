module edu.utsa.cs3443.fasttrack_projectdemo_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.fasttrack_projectdemo_javafx to javafx.fxml;
    exports edu.utsa.cs3443.fasttrack_projectdemo_javafx;
}