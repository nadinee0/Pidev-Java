package tn.leaguestorm.tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BackWindow extends Application {
    private double x, y;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
        Parent root = FXMLLoader.load(getClass().getResource("Back.fxml"));
        primaryStage.setScene(new Scene(root));

        primaryStage.initStyle(StageStyle.UNDECORATED);


        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
            
        });
        primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
