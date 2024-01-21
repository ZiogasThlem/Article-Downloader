package ui;

import com.tziogas.articledownloader.services.HTMLParser;
import com.tziogas.articledownloader.services.HTMLtoMDParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.AppException;
import utils.Constants;

import java.io.IOException;

public class HTMLParserController {

    private static final Logger logger = LoggerFactory.getLogger(HTMLParserController.class);

    @FXML
    private final Stage primaryStage;
    @FXML
    protected Label resultLabel;
    @FXML
    protected TextField urlField;
    @FXML
    protected Button parseButton;
    @FXML
    protected Button downloadMdButton;
    private String parsedResult = "";

    public HTMLParserController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initUI() {
        urlField = new TextField();
        parseButton = new Button("Parse HTML");
        downloadMdButton = new Button(("Download MD"));
        resultLabel = new Label();
        downloadMdButton.setVisible(false);

        parseButton.setOnAction(this::onParseButtonClick);
        downloadMdButton.setOnAction(this::onDownloadButtonClick);

        VBox root = new VBox(10, urlField, parseButton, downloadMdButton, resultLabel);
        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle(Constants.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    private void onParseButtonClick(ActionEvent actionEvent) {
        parseButton.requestFocus();
        logger.info("Parse button clicked " + actionEvent.getEventType().getName());
        parseButton.setOnAction(event -> {
            String url = urlField.getText();
            try {
                parsedResult = HTMLParser.parseHTML(url);
                resultLabel.setText("Found content:\n" + parsedResult);
                downloadMdButton.setVisible(true);
            } catch (IOException | AppException e) {
                resultLabel.setText("Error fetching or parsing HTML.");
                logger.error("Error fetching or parsing HTML.");
            }
        });
    }

    @FXML
    private void onDownloadButtonClick(ActionEvent actionEvent) {
        downloadMdButton.requestFocus();
        downloadMdButton.setOnAction(event -> {
            HTMLtoMDParser.createMarkdownFile(
                    Constants.FILE_DIRECTORY_PATH,
                    Constants.FILE_NAME,
                    HTMLtoMDParser.parseHTMLtoMD(parsedResult)
            );
            resultLabel.setText("File saved!");
            logger.info("File saved");
        });
    }
}
