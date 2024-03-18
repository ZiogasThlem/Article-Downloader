package com.tziogas.articledownloader.services;

import com.tziogas.articledownloader.utils.LogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.List;

public class HTMLParser {

    private static LogWrapper logger = LogWrapper.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Parses HTML content from a specified URL and retrieves the content of an element by ID.
     *
     * @param url      The URL of the HTML page to parse.
     * @param targetId
     * @return A String representing the HTML content of the element with the specified ID, or {@code null} if the element is not found.
     * @throws IOException If an I/O error occurs while connecting to the URL.
     * @implNote This method uses Jsoup to connect to the specified URL, retrieve the HTML content, and then selects an element by ID.
     * If the element is found, its HTML representation is returned; otherwise, {@code null} is returned.
     * @see Jsoup
     */
    @NotNull
    public static String parseHtmlToString(String url, String htmlTag, String targetId) throws IOException {
        Document document = Jsoup.connect(url).get();
        List<Element> foundElements = document.select(htmlTag + targetId);

        StringBuilder result = new StringBuilder();
        foundElements.forEach(element -> {
            result.append(element.toString());
        });
        return result.toString();
    }

    public static String extractArticleTitle(String url) {
        //find title via regex
        return "title";
    }

}
