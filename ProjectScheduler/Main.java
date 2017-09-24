package ProjectScheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("Group Free Time v0.0.0.0.0.1.0.1");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //saves on close
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            Controller.fileH.saveFile(Controller.gridSize, Controller.list);
            primaryStage.close();
                });
    }


    public static void main(String[] args) {
        launch(args);
    }


}
