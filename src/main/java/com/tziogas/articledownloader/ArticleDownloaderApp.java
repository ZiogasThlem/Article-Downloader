package com.tziogas.articledownloader;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;
import com.tziogas.articledownloader.utils.Constants;
import com.tziogas.articledownloader.utils.Kernel32Ex;
import com.tziogas.articledownloader.utils.LogWrapper;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class ArticleDownloaderApp extends Application {

    private static final LogWrapper logger = LogWrapper.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ArticleDownloaderApp.class.getResource("html-parser-controller.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Constants.APP_WINDOW_WIDTH, Constants.APP_WINDOW_HEIGHT);
        stage.setTitle(Constants.APP_NAME + " " + Constants.APP_VERSION);

//        scene.getStylesheets().add(getClass().getResource("/fxml/window-style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Prevent multiple instances of Cosec-Provisioning-App using Win32 Mutex.
     */
    private void checkForOtherRunningInstance() {
        final Kernel32Ex Win32 = Native.load("kernel32", Kernel32Ex.class, W32APIOptions.UNICODE_OPTIONS);

        WinNT.HANDLE handle = Win32.CreateMutex(null,
                true,
                "Cosec-Provisioning-App-Mutex");

        if (handle == null || Win32.GetLastError() == 183) {
            logger.error("Unable to acquire Cosec-Provisioning-App-Mutex lock.");
            logger.error("Another instance of Cosec-Provisioning-App is already running. This instance will be terminated.");
            Win32.CloseHandle(handle);
            displayAlertWithTimeout(Alert.AlertType.WARNING, "Another instance of Cosec-Provisioning-App is already running. This instance will be terminated.", 10000L);
            logger.error("Cosec-Provisioning-App Instance is now terminating...");
            Platform.exit();
            System.exit(0);
        } else {
            logger.info("Cosec-Provisioning-App-Mutex acquired successfully.");
        }
    }

    private void displayAlertWithTimeout(Alert.AlertType alertType, String message, long millis) {
        Alert alert = new Alert(alertType, message);
        Thread thread = new Thread(() -> {
            try {
                // Wait for millis
                Thread.sleep(millis);
                if (alert.isShowing()) {
                    Platform.runLater(() -> alert.close());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        alert.showAndWait();
    }
}
