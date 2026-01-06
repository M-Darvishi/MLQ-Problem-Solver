package ui.controllers;

import core.model.ProcessView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.Drawing;
import ui.SchedulerRunner;
import ui.model.QueueConfig;
import ui.model.ProcessModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFxController {

    @FXML private TableView<QueueConfig> queueTable;
    @FXML private TableColumn<QueueConfig, Integer> queuePriority;
    @FXML private TableColumn<QueueConfig, String> queueAlgo;
    @FXML private TableColumn<QueueConfig, Integer> queueSlice;
    @FXML private TableColumn<QueueConfig, Integer> queuePolicy;

    @FXML private TableView<ProcessModel> processTable;
    @FXML private TableColumn<ProcessModel, String> processName;
    @FXML private TableColumn<ProcessModel, Integer> processArrival;
    @FXML private TableColumn<ProcessModel, Integer> processServiceTime;
    @FXML private TableColumn<ProcessModel, Integer> processPriority;

    @FXML private Canvas ganttCanvas;
    @FXML private  ScrollPane scrollPane_N0;
    @FXML private Label awtLabel;
    @FXML private Label attLabel;

    private ToggleGroup algoGroup;
    @FXML private RadioButton mlqRadio;
    @FXML private RadioButton mlfqRadio;
    @FXML private AnchorPane mlqPage;
    @FXML private AnchorPane mlfqPage;
    @FXML private VBox avgVBox;

    @FXML
    protected void initialize() {

        algoGroup = new ToggleGroup();
        mlfqRadio.setToggleGroup(algoGroup);
        mlqRadio.setToggleGroup(algoGroup);

        mlqPage.setVisible(true);
        mlqRadio.setSelected(true);
        mlfqPage.setVisible(false);

        mlqRadio.setOnAction(e -> {
            avgVBox.setVisible(true);
            mlqPage.setVisible(true);
            mlfqPage.setVisible(false);
        });

        mlfqRadio.setOnAction(e -> {
            avgVBox.setVisible(false);
            mlqPage.setVisible(false);
            mlfqPage.setVisible(true);
        });
    }


    @FXML
    protected void openAddQueueWindow() throws IOException {
        Stage queueStage = new Stage();
        queueStage.setTitle("Add Queue");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddQueue.fxml"));
        Parent queuePage = loader.load();
        queuePriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        queueAlgo.setCellValueFactory(new PropertyValueFactory<>("algo"));
        queueSlice.setCellValueFactory(new PropertyValueFactory<>("timeSlice"));
        queuePolicy.setCellValueFactory(new PropertyValueFactory<>("policy"));
        QueueFxController controller = loader.getController();
        controller.setOnAdd(queue -> {
            int autoPriority = queueTable.getItems().size();
            queue.setPriority(autoPriority);
            queueTable.getItems().add(queue);
        });
        Scene newScene = new Scene(queuePage, 300 , 200);
        queueStage.setScene(newScene);
        queueStage.show();
        Platform.runLater(() -> controller.getAlgoEntry().requestFocus());
    }
    @FXML
    protected void openAddProcessWindow() throws IOException{
        Stage processStage = new Stage();
        processStage.setTitle("Add Process");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddProcess.fxml"));
        Parent processPage = loader.load();
        processName.setCellValueFactory(new PropertyValueFactory<>("name"));
        processArrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        processServiceTime.setCellValueFactory(new PropertyValueFactory<>("service"));
        processPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        ProcessFxController controller = loader.getController();
        controller.setOnAdd(process -> processTable.getItems().add(process));
        Scene newScene = new Scene(processPage, 300 , 200);
        processStage.setScene(newScene);
        processStage.show();
        Platform.runLater(() -> controller.getNameEntry().requestFocus());
    }

    @FXML
    protected void resetProcesses(){
        processTable.getItems().clear();
        awtLabel.setText("");
        attLabel.setText("");
        ganttCanvas.getGraphicsContext2D().clearRect(0, 0, ganttCanvas.getWidth(), ganttCanvas.getHeight());
    }

    @FXML
    protected void resetQueues(){
        queueTable.getItems().clear();
        awtLabel.setText("");
        attLabel.setText("");
        ganttCanvas.getGraphicsContext2D().clearRect(0, 0, ganttCanvas.getWidth(), ganttCanvas.getHeight());
    }



    @FXML
    protected  void runAction(){
        if (mlqRadio.isSelected()) {
            mlqRunner();
        }else if (mlfqRadio.isSelected()) {
            mlfqRunner();
        }
    }

    @FXML
    protected  void paintQueues(){
//        List<QueueConfig> queues = new ArrayList<>(queueTable.getItems());
//        List<ProcessModel> processes = new ArrayList<>(processTable.getItems());
//        SchedulerRunner runner = new SchedulerRunner(queues,processes);

    }

    private void mlqRunner(){
        List<QueueConfig> queues = new ArrayList<>(queueTable.getItems());
        List<ProcessModel> processes = new ArrayList<>(processTable.getItems());

        SchedulerRunner runner = new SchedulerRunner(queues,processes);
        List<ProcessView> gantt = runner.doTheRun();
        List<ProcessModel> finalProcesses=runner.setProcessModels();

        double unitWidth=30;
        double totalWidth=  gantt.get(gantt.size()-1).getCompletionTime() * unitWidth;
        ganttCanvas.setWidth(totalWidth + 50);
        scrollPane_N0.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane_N0.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        Drawing drawing = new Drawing(ganttCanvas);
        drawing.draw(gantt);


        double totalWaiting = 0;
        double totalTurnaround = 0;
        int n = processes.size();
        for(ProcessModel p : finalProcesses){
            int tat=  p.getCompletionTime() - p.getArrival();
            int wt = tat - p.getService();
            totalTurnaround += tat;
            totalWaiting += wt;
        }
        double awt = totalWaiting / n;
        double att = totalTurnaround / n;

        awtLabel.setText("AWT = " + String.format("%.2f", awt));
        attLabel.setText("ATT = " + String.format("%.2f", att));
    }

    private void mlfqRunner(){

    }

}
