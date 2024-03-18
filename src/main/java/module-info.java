module com.tziogas.articledownloader {
    requires javafx.controls;
    requires javafx.fxml;
    requires log4j;
    requires com.sun.jna.platform;
    requires okhttp3;
    requires annotations;
    requires org.jsoup;


    opens com.tziogas.articledownloader to javafx.fxml;
    exports com.tziogas.articledownloader;
    exports com.tziogas.articledownloader.ui;
    opens com.tziogas.articledownloader.ui to javafx.fxml;
}