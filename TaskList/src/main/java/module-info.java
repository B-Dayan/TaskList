module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.taskList to javafx.fxml;
    exports org.example.taskList;
}