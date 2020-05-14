package controller;

/*
Settings:
   * Language
   * Upload quiz
   * Reset highscores
 */

import helpers.DataHelper;
import helpers.LanguageHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SettingsController {
    @FXML
    Button sweSelectBtn;

    @FXML
    Button engSelectBtn;

    @FXML
    Button uploadBtn;

    @FXML
    Button resetBtn;

    @FXML
    Button saveBtn;

    String newLang = "";

    public void initialize(){}

    public void handleSave(MouseEvent event) {
        System.out.println(newLang);
        if(!newLang.equals("")){
            if(newLang.equals("sv")){
                DataHelper.changeLang("sv");
            } else if (newLang.equals("en")) {
                DataHelper.changeLang("en");
            }
        }

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"), LanguageHelper.getCurrentLanguage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleReset(MouseEvent event) {
        DataHelper.clearHighscore();
    }

    public void handleUpload(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON Files", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog((Stage) saveBtn.getScene().getWindow());
        DataHelper.importQuiz(file.getPath());

        Stage stage = (Stage) saveBtn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../layout/HomeLayout.fxml"), LanguageHelper.getCurrentLanguage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleLanguageSwe(MouseEvent event) {
        newLang = "sv";
    }

    public void handleLanguageEng(MouseEvent event) {
        newLang = "en";
    }
}
