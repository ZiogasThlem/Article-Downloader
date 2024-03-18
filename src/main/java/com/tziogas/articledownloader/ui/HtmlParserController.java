package com.tziogas.articledownloader.ui;

import com.tziogas.articledownloader.services.HTMLParser;
import com.tziogas.articledownloader.utils.LogWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Optional;

public class HtmlParserController {

    private static LogWrapper logger = LogWrapper.getLogger(MethodHandles.lookup().lookupClass());

    @FXML
    public Button submitButton;
    @FXML
    public ListView<String> content;

    @FXML
    public Label htmlTagLabel;

    @FXML
    private Label urlLabel;

    @FXML
    private Label targetElementLabel;

    @FXML
    private TextField urlTextField;

    @FXML
    private TextField targetElementTextField;

    @FXML
    private TextField htmlTagTextField;

    @FXML
    public void initialize () {
        submitButton.setOnAction(this::onSubmitButtonClick);
    }

    @FXML
    private void onSubmitButtonClick(ActionEvent actionEvent) {
        if (targetElementTextField.getText().isBlank() || urlTextField.getText().isBlank()) {
            return;
        }
        String element = targetElementTextField.getText();
        String url = urlTextField.getText();
        String tag = htmlTagTextField.getText();

        try {
            content.getItems().add(HTMLParser.parseHtmlToString(url, tag, element));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        showSimpleDialog(actionEvent, "sup", Alert.AlertType.CONFIRMATION);
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
