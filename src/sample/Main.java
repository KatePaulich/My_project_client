package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class - start class
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //стартовая страница
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/enter/sample.fxml"));
        primaryStage.setTitle("Журнал расходов");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
