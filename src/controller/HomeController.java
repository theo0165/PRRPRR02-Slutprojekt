package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    StackPane root;

    @FXML
    Text title;

    @FXML
    HBox titleContainer;

    @FXML
    Button playBtn;

    @FXML
    Button settingsBtn;

    @FXML
    Text exitBtn;

    public void initialize(){
        //title.setFont(Font.loadFont("file:../resources/fonts/PressStart2P-Regular.ttf", 200));
        titleContainer.setPadding(new Insets(130, 0, 0, 0));
    }

    public void handleExit(MouseEvent mouseEvent) {
        Stage stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();
    }

    public void handleScoreboard(MouseEvent mouseEvent) {
    }

    public void handleSettings(MouseEvent mouseEvent) {
    }

    public void handlePlay(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) playBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../layout/GameLayout.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}