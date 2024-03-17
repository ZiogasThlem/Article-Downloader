package com.tziogas.articledownloader;

import com.tziogas.articledownloader.utils.LogWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class HtmlParserController {

    private static LogWrapper logger = LogWrapper.getLogger(MethodHandles.lookup().lookupClass());

    @FXML
    public Button submitButton;

    @FXML
    private Label label;

    @FXML
    private TextField textField;

    @FXML
    public void initialize () {
        submitButton.setOnAction(event -> {
            showSimpleDialog(event, "sup", Alert.AlertType.CONFIRMATION);
        });
    }


    private boolean showSimpleDialog(ActionEvent actionEvent, String text, Alert.AlertType alertType) {

        Stage mainStage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        Alert alert = new Alert(alertType);
        alert.initOwner(mainStage);
        alert.setTitle("Confirmation Action");
        alert.setHeaderText(text);
        alert.getButtonTypes().clear();

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().add(okButton);

        if (alertType.equals(Alert.AlertType.CONFIRMATION)) {
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().add(cancelButton);
        }

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == okButton;
    }

}
