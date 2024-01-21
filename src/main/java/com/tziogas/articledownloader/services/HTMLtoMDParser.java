package com.tziogas.articledownloader.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.Constants;
import utils.LogWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class HTMLtoMDParser {

    public static LogWrapper logger = LogWrapper.getLogger(MethodHandles.lookup().lookupClass());

    public static String parseHTMLtoMD(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        return doc.body().text();
    }

    private static void parseElement(Element element, StringBuilder markdownContent) {
        logger.info("Element Found!");
        markdownContent.append(element.text());
    }

    public static void createMarkdownFile(String directoryPath, String fileName, String markdownContent) {
        logger.info("DIRECTORY PATH: " + directoryPath);
        logger.info("FILE NAME: " + fileName);
        try {
            File markdownFile = new File(directoryPath, fileName + ".md");
            FileWriter writer = new FileWriter(markdownFile);
            writer.write(markdownContent);
            logger.info("Markdown file created successfully: " + markdownFile.getAbsolutePath());
            writer.close();
        } catch (IOException e) {
            logger.error("Error: " + e.getMessage());
        }
    }
}
