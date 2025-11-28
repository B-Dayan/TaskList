package org.example.taskList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaskListController {
    @FXML private TextField textField;
    @FXML private Button removeBttn;
    @FXML private Button completeBttn;

    private final ObservableList<CheckBox> taskList = FXCollections.observableArrayList();
    @FXML private ListView<CheckBox> listView = new ListView<>(taskList);

    @FXML
    protected void onAdd() {
        //if the TextField is empty, don't attempt to add a task
        if ( textField.getText().isEmpty() ) {
            return;
            //there is no need to display an error message when the user attempts to add an empty string
            // since it is logical that adding "nothing" to the list would not work
        }

        //if there were no tasks in the manager before this add action,
        // enable the "Remove Task" and "Complete Task" buttons
        if ( listView.getItems().isEmpty() ) {
            removeBttn.setDisable(false);
            completeBttn.setDisable(false);
        }

        //get the String from the TextField, add it to the end of the list of tasks, and clear the TextField
        CheckBox newTask = new CheckBox( textField.getText() );
        int endIndex = listView.getItems().size();
        listView.getItems().add(endIndex, newTask);
        textField.clear();
    }

    @FXML
    protected void onRemove() {
        //remove the selected item by its index
        try {
            listView.getItems().remove( listView.getSelectionModel().getSelectedIndex() );
        }
        catch (IndexOutOfBoundsException e) {
            //if the user somehow attempts to remove a selected task that does not exist in the list of tasks,
            // log the error and don't attempt to do anything else
            e.printStackTrace();
            System.out.println("Error: the task with an index of " + listView.getSelectionModel().getSelectedIndex()
                    + " does not exist since the application only contains " + listView.getItems().size() + " tasks.");
            return;
        }

        //if the last task was removed,
        // don't allow the user to press the "Remove Task" or "Complete Task" buttons
        if ( listView.getItems().isEmpty() ) {
            removeBttn.setDisable(true);
            completeBttn.setDisable(true);
        }
    }

    @FXML
    protected void onComplete() {
        //check the selected CheckBox
        try {
            listView.getSelectionModel().getSelectedItem().fire();
        }
        catch (IndexOutOfBoundsException e) {
            //if the user somehow attempts to check a selected task that is not in the task list, log the error
            e.printStackTrace();
            System.out.println("Error: the task with an index of " + listView.getSelectionModel().getSelectedIndex()
                    + " does not exist since the application only contains " + listView.getItems().size() + " tasks.");
        }

        //if an item is already checked, pressing the "Complete Task" button will uncheck the item
        //this could be changed by replacing the above code with:
        // listView.getSelectionModel().getSelectedItem().setSelected(true);
    }

    @FXML
    protected void initialize() {
        //don't allow the user to press the "Remove Task" or "Complete Task" buttons
        // since there are initially no tasks in the manager
        removeBttn.setDisable(true);
        completeBttn.setDisable(true);
    }
}
