module com.tziogas.articledownloader {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;
    requires com.sun.jna.platform;
    requires okhttp3;


    opens com.tziogas.articledownloader to javafx.fxml;
    exports com.tziogas.articledownloader;
}