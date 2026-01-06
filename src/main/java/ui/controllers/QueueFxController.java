package ui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import ui.model.QueueConfig;

import java.util.function.Consumer;

public class QueueFxController {
    @FXML private TextField sliceEntry;
    @FXML private ComboBox<String> algoEntry;
    @FXML private TextField policyEntry;
    @FXML private Text errorMsg;
    @FXML private Button addButton;


    private Consumer<QueueConfig> onAddBtn;
    public void setOnAdd(Consumer<QueueConfig> onAdd) {
        this.onAddBtn = onAdd;
    }

    @FXML
    public void initialize() {
        algoEntry.getItems().addAll("FCFS", "SJF", "SRTF", "HRRN", "RR");
        sliceEntry.setDisable(true);
        policyEntry.setDisable(true);
        algoEntry.setOnAction(event -> {
            String algo = algoEntry.getValue();
            if ("RR".equals(algo)) {
                sliceEntry.setDisable(false);
                policyEntry.setDisable(false);
            } else {
                sliceEntry.clear();
                policyEntry.clear();
                sliceEntry.setDisable(true);
                policyEntry.setDisable(true);
            }
        });
//        algoEntry.setOnAction(e -> sliceEntry.requestFocus());
//        sliceEntry.setOnAction(e -> policyEntry.requestFocus());
//        policyEntry.setOnAction(e -> addButton.fire());
    }

    public ComboBox getAlgoEntry() {
        return algoEntry;
    }

    @FXML
    private void addQueue() {
        String algo = algoEntry.getValue();
        String slice = sliceEntry.getText();
        String policy = policyEntry.getText();

        if ("RR".equals(algo)) {
            if(slice == null || slice.isEmpty()){
                errorMsg.setText("Add Time Slice!");
                return;
            }
            try {
                Integer.parseInt(slice);
            } catch (NumberFormatException e) {
                errorMsg.setText("Time Slice should be number!");
                return;
            }
        }

        if ("RR".equals(algo)){
            if(policy == null || policy.isEmpty()){
                errorMsg.setText(" Enter the Policy!");
                return;
            }
            try {
                Integer.parseInt(policy);
            } catch (NumberFormatException e) {
                errorMsg.setText("Policy should be number!");
                return;
            }
            if(Integer.parseInt(policy)!=1 && Integer.parseInt(policy)!=2){
                errorMsg.setText("Policy should be 1 or 2.");
                return;
            }
        }

        if(algo==null || algo.isEmpty()){
            errorMsg.setText("Enter Algorithm");
            return;
        }


        QueueConfig newQueue;
        if(algo.equals("RR")){
            newQueue = new QueueConfig(algo , Integer.parseInt(slice),Integer.parseInt(policy));
        }else {
            newQueue = new QueueConfig(algo);
        }
        if (onAddBtn != null) {
            onAddBtn.accept(newQueue);
        }

        errorMsg.setText("");
        Stage stage = (Stage) algoEntry.getScene().getWindow();
        stage.close();
    }
}
