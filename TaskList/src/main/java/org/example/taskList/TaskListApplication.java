package org.example.taskList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TaskListApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //create scene from the .fxml file and the controller
        FXMLLoader fxmlLoader = new FXMLLoader(org.example.taskList.TaskListApplication.class.getResource("taskList.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 320);
        stage.setScene(scene);

        //name stage and display it
        stage.setTitle("Task List");
        stage.show();
    }
}
