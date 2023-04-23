package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

public class TextFromImageReaderController extends Window {

    private String selectedFilePath;
    private File selectedFile;
    private static final String TESSDATA_PATH = "C:\\Users\\fikot\\IdeaProjects\\text-from-jpg-reader\\tessdata";
    private static final String TESSERACT_LANGUAGE = "pol";

    @FXML
    protected void chooseFile() throws TesseractException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("jpg","*.jpg"),
                new FileChooser.ExtensionFilter("png","*.png"));
        selectedFile = fileChooser.showOpenDialog(this);
        if (selectedFile != null) {
            selectedFilePath = selectedFile.getPath();
            tfPath.setText(selectedFilePath);
        }
        extractTextFromImage();
    }

    @FXML
    protected void openPreview() throws IOException {
        if (selectedFile != null) {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/image_preview.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setMinHeight(300);
            stage.setMinWidth(600);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
            Image image = new Image(selectedFilePath);
            ImagePreviewController imagePreviewController = fxmlLoader.getController();
            imagePreviewController.getImageView().setImage(image);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nie wybrano obrazu!", new ButtonType("Zamknij"));
            alert.setTitle("Błąd");
            alert.setHeaderText("Błąd!");
            alert.showAndWait();
        }
    }

    private void extractTextFromImage() throws TesseractException {
        File imageFile = new File(selectedFilePath);
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath(TESSDATA_PATH);
        tesseract.setLanguage(TESSERACT_LANGUAGE);
        String result = tesseract.doOCR(imageFile);
        taExtractedText.setText(result.replace("\n", " "));
    }

    @FXML
    private TextArea taExtractedText;
    @FXML
    private TextField tfPath;

}