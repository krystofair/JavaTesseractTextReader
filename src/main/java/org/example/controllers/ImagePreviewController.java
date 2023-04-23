package org.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class ImagePreviewController extends Window implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(mainVBox.widthProperty());
        imageView.fitHeightProperty().bind(mainVBox.heightProperty());
    }

    public ImageView getImageView() {
        return imageView;
    }

    @FXML
    private ImageView imageView;
    @FXML
    private VBox mainVBox;
}
