import helpers.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager.primaryStage = primaryStage;
        SceneManager.root = FXMLLoader.load(getClass().getResource("layout/HomeLayout.fxml"));
        SceneManager.scene = new Scene(SceneManager.root, 800, 600);
        primaryStage.setTitle("Quiz Game");
        primaryStage.getIcons().add(new Image("file:/resources/images/favicon-32x32.png"));
        primaryStage.setScene(SceneManager.scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
