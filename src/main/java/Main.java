import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
        Scene scene = new Scene(root,1000,700);
        stage.setTitle("MLQ Problem Solver");
        stage.setScene(scene);
        stage.show();
    }
    public  static void main(String[] args){
        launch(args);
    }
}
