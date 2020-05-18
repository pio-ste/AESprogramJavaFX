package com.aes.program.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DecryptController {

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
    private CheckBox desCheckBox;

    @FXML
    private CheckBox aesCheckBox;

    @FXML
    private MenuButton keyValueMenu;

    @FXML
    private TextField textFieldKeyValue;

    @FXML
    void initialize(){
        aesCheckBox.setSelected(true);
    }
    @FXML
    public void goToMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/aes/program/FXML/primary.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    public void chooseFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++){
                listViewFile.getItems().add(selectedFiles.get(i).getAbsolutePath());
                byte[] fileBytes = Files.readAllBytes(selectedFiles.get(i).toPath());
                for (int y = 0; y < fileBytes.length; y++){
                    System.out.print((char) fileBytes[y]);
                }
            }
        } else {
            System.out.println("Brak plików");
        }
    }

    public void chooseCertFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll( new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            byte[] fileByte = Files.readAllBytes(selectedFile.toPath());
            for (int i = 0; i < fileByte.length; i++){
                System.out.print((char) fileByte[i]);
            }
            listViewCert.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("Brak plików");
        }
    }

    public void decryptFile(ActionEvent actionEvent) {

    }

    @FXML
    public void handleAEScheckBox() {
        if (aesCheckBox.isSelected()) {
            desCheckBox.setSelected(false);
            keyValueMenu.setVisible(true);
            textFieldKeyValue.setVisible(true);
        }
    }

    @FXML
    public void handleDEScheckBox() {
        if (desCheckBox.isSelected()) {
            aesCheckBox.setSelected(false);
            keyValueMenu.setVisible(false);
            textFieldKeyValue.setVisible(false);
            textFieldKeyValue.setText(null);
        }
    }

    @FXML
    public void firstOptionMenu(ActionEvent actionEvent) {
        System.out.println("128");
        textFieldKeyValue.setText("128");
    }

    @FXML
    public void secondOptionMenu(ActionEvent actionEvent) {
        System.out.println("192");
        textFieldKeyValue.setText("192");
    }

    @FXML
    public void thirdOptionMenu(ActionEvent actionEvent) {
        System.out.println("256");
        textFieldKeyValue.setText("256");
    }
}
