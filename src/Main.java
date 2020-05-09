import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("layout/HomeLayout.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("resources/style/HomeStyle.css");
        primaryStage.setTitle("Quiz Game");
        primaryStage.getIcons().add(new Image("file:resources/images/favicon-32x32.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
