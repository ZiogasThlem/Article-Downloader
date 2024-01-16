module com.tziogas.articledownloader {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.jsoup;
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires static lombok;
    requires annotations;

    opens com.tziogas.articledownloader to javafx.fxml;
    exports com.tziogas.articledownloader;
    exports utils;
    exports ui;
    opens ui to javafx.fxml;
    exports com.tziogas.articledownloader.services;
}