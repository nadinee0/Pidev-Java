
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class CombinedWindow extends Application {
    
    public void start(Stage primaryStage) {
        try {
            Parent teamRoot = FXMLLoader.load(getClass().getResource("New.fxml"));
            Parent orgRoot = FXMLLoader.load(getClass().getResource("NewOrganisation.fxml"));
            
            Scene teamScene = new Scene(teamRoot);
            Scene orgScene = new Scene(orgRoot);
            
            TabPane tabPane = new TabPane();
            Tab teamTab = new Tab("Add a team now!", teamRoot);
            Tab orgTab = new Tab("Add an Organisation now!", orgRoot);
            tabPane.getTabs().addAll(teamTab, orgTab);
            
            primaryStage.setTitle("Combined Window");
            primaryStage.setScene(new Scene(tabPane));
            primaryStage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
