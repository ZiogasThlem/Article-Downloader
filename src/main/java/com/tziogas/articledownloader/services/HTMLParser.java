package com.tziogas.articledownloader.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.AppException;

import java.io.IOException;

public class HTMLParser {

    private static final Logger logger = LoggerFactory.getLogger(HTMLParser.class);

    public static String parseHTML(String url) throws IOException, AppException {
        Document document = Jsoup.connect(url).get();
        Elements paragraphs = document.select("p");
        StringBuilder result = new StringBuilder();
        for (Element paragraph : paragraphs) {
            result.append(paragraph.text()).append("\n");
        }
        return result.toString();
    }

    public static String extractArticleTitle(String url) {
        return "";
    }
}