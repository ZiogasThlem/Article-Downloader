package com.tziogas.articledownloader;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.HTMLParserController;

public class ArticleDownloaderApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        HTMLParserController htmlParserUI = new HTMLParserController(primaryStage);
        htmlParserUI.initUI();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
