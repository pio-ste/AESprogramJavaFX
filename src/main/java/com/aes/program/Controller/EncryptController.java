package com.aes.program.Controller;

import com.aes.program.Model.Form;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EncryptController {

    Form form = new Form();

    List<com.aes.program.Model.File> files = new ArrayList<com.aes.program.Model.File>();

    @FXML
    private PasswordField password;

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
    private CheckBox ecbCheckBox;

    @FXML
    private CheckBox cbcCheckBox;

    @FXML
    private CheckBox multiThreadingCheckBox;

    @FXML
    private MenuButton keyValueMenu;

    @FXML
    private TextField textFieldKeyValue;

    /**Metoda w która wykonuje się po załadowaniu pliku fxml, ustawia checkbox domyślnie na true*/
    @FXML
    void initialize(){
        aesCheckBox.setSelected(true);
    }

    /**Metoda powrotu do menu ładuje tylko plik fxml*/
    @FXML
    public void goToMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/com/aes/program/FXML/primary.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    /**Metoda która odpowiada za wybranie plików do szyfrowania oraz dodaje inofrmacje o plikach do listy*/
    public void chooseFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++){
                listViewFile.getItems().add(selectedFiles.get(i).getAbsolutePath());
                Path path = Paths.get(selectedFiles.get(i).getAbsolutePath());
                Path fileName = path.getFileName();
                byte[] fileBytes = Files.readAllBytes(selectedFiles.get(i).toPath());
                /** Dwie poniższe linijki wyodrębniają rozszerzenie pliku*/
                String filePath = path.toString();
                String fileExtension =  filePath.substring(filePath.indexOf("."));
                files.add(i, new com.aes.program.Model.File(fileBytes, fileName.toString(), path.toString(), fileExtension));
            }
        } else {
            System.out.println("Brak plików");
        }
    }

    public void chooseCertFile(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            byte[] certificateFileByte = Files.readAllBytes(selectedFile.toPath());
            /**Przypisywanie wartości klucza*/
            form.setKey(certificateFileByte);  // setKey
            for (int i = 0; i < certificateFileByte.length; i++){
                System.out.print((char) certificateFileByte[i]);
            }
            listViewCert.getItems().add(selectedFile.getAbsolutePath());
        } else {
            System.out.println("Brak plików");
        }
    }
    /** Metoda która wywołuje się po naciśnięciu przcisku szyfruj w oknie tutaj można wywołać metodę do szyfrowania*/
    public void encryptFile(ActionEvent actionEvent) {
        byte[] bytePassword = password.getText().getBytes();
        form.setPassword(bytePassword);
        form.setFiles(files);
        for (com.aes.program.Model.File file : files) {
            System.out.println(file.getData());
            System.out.println(file.getFileName());
            System.out.println(file.getFilePath());
            System.out.println(file.getFileType());
        }
    }

    /**checkBox gdzie można wybrać */
    @FXML
    public void handleAEScheckBox() {
        if (aesCheckBox.isSelected()) {
            desCheckBox.setSelected(false);
            keyValueMenu.setVisible(true);
            textFieldKeyValue.setVisible(true);
            Form.algorithm algorithm= Form.algorithm.AES;
        }
    }
    /**Jak metoda powyżej tylko do DES*/
    @FXML
    public void handleDEScheckBox() {
        if (desCheckBox.isSelected()) {
            aesCheckBox.setSelected(false);
            keyValueMenu.setVisible(false);
            textFieldKeyValue.setVisible(false);
            textFieldKeyValue.setText(null);
            Form.algorithm algorithm= Form.algorithm.DES;
        }
    }

    /**Przypisanie KeyLength wartośći po wybraniu długości klucza z rozwijanego menu*/
    @FXML
    public void firstOptionMenu(ActionEvent actionEvent) {
        System.out.println("128");
        textFieldKeyValue.setText("128");
        form.setKeyLength(128);
    }
    /**Jak powyżej tylko inna wartość*/
    @FXML
    public void secondOptionMenu(ActionEvent actionEvent) {
        System.out.println("192");
        textFieldKeyValue.setText("192");
        form.setKeyLength(192);
    }
    /**Tak samo jak wyżej*/
    @FXML
    public void thirdOptionMenu(ActionEvent actionEvent) {
        System.out.println("256");
        textFieldKeyValue.setText("256");
        form.setKeyLength(256);
    }
    /**Metoda obsługująca checkBox do wyboru czy program ma działać na wielu wątkach*/
    @FXML
    public void handleMultiThreading(ActionEvent actionEvent) {
        if (multiThreadingCheckBox.isSelected()){
            System.out.println("wielowątkowość");
            form.setMultithread(true);
        }
    }
    /**Metoda do wyboru pracy algorytmu ECB*/
    @FXML
    public void handleEcbCheckBox(ActionEvent actionEvent) {
        if (ecbCheckBox.isSelected()) {
            cbcCheckBox.setSelected(false);
            Form.cipherMode cipherMode = Form.cipherMode.ECB;
        }
    }
    /**Metoda do wyboru pracy algorytmu CBC*/
    @FXML
    public void handleCbcCheckBox(ActionEvent actionEvent) {
        if (cbcCheckBox.isSelected()){
            ecbCheckBox.setSelected(false);
            Form.cipherMode cipherMode = Form.cipherMode.CBC;
        }
    }
}
