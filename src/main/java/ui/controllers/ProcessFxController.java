package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import ui.model.ProcessModel;

import java.util.function.Consumer;

public class ProcessFxController {
    @FXML
    private TextField nameEntry;
    @FXML
    private TextField arrivalEntry;
    @FXML
    private TextField serviceEntry;
    @FXML
    private TextField priorityEntry;
    @FXML
    private Text errorMsg;
    @FXML private Button addButton;

    private Consumer<ProcessModel> onAddBtn;
    public void setOnAdd(Consumer<ProcessModel> onAdd) {
        this.onAddBtn = onAdd;
    }

    @FXML
    protected void initialize() {
//        nameEntry.setOnAction(e -> arrivalEntry.requestFocus());
//        arrivalEntry.setOnAction(e -> serviceEntry.requestFocus());
//        serviceEntry.setOnAction(e -> priorityEntry.requestFocus());
//        priorityEntry.setOnAction(e -> addButton.fire());
    }

    public TextField getNameEntry() {
        return nameEntry;
    }

    @FXML
    private void addProcess() {
        String name = nameEntry.getText();
        String arrival = arrivalEntry.getText();
        String service = serviceEntry.getText();
        String priority = priorityEntry.getText();

        try {
            Integer.parseInt(arrival);
            Integer.parseInt(service);
        } catch (NumberFormatException e) {
            errorMsg.setText("Arrival time and Service time should be number!");
            return;
        }

        if (name == null || name.isEmpty()) {
            errorMsg.setText("Enter Process Name !");
            return;
        }

        try {
            Integer.parseInt(priority);
        } catch (NumberFormatException e) {
            errorMsg.setText("Priority should be number!");
            return;
        }

        ProcessModel newProcess = new ProcessModel(name, Integer.parseInt(arrival),Integer.parseInt(service),  Integer.parseInt(priority));
        if (onAddBtn != null) {
            onAddBtn.accept(newProcess);
        }

        errorMsg.setText("");
        Stage stage = (Stage) priorityEntry.getScene().getWindow();
        stage.close();
    }
}
