package com.aes.program.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EncryptDecryptController {

    @FXML
    private Button chooseFileBTN;

    @FXML
    private Button certFileBTN;

    @FXML
    private Button encryptBtn;

    @FXML
    private Button decryptBTN;

    @FXML
    private ListView listViewFile;

    @FXML
    private ListView listViewCert;

    @FXML
    void initialize(){
    }
    @FXML
    public void goToMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/aes/program/FXML/primary.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++){
                listViewFile.getItems().add(selectedFiles.get(i).getAbsolutePath());
            }
        } else {
            System.out.println("Brak plików");
        }
    }

    public void chooseCertFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            listViewCert.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("Brak plików");
        }
    }

    public void encryptFile(ActionEvent actionEvent) {

    }

    public void decryptFile(ActionEvent actionEvent) {

    }
}
