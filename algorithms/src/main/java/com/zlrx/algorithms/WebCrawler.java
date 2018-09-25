package com.zlrx.algorithms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {

    private Queue<String> queue;
    private List<String> discoveredWebsiteList;
    private Pattern urlPattern;

    public WebCrawler() {
        queue = new LinkedList<>();
        discoveredWebsiteList = new ArrayList<>();
        urlPattern = Pattern.compile("http://(\\w+\\.)*(\\w+)");
    }

    private void discoverWeb(String root) {
        queue.add(root);
        discoveredWebsiteList.add(root);
        while (!queue.isEmpty()) {
            String currentNode = queue.remove();
            String rawHtml = readURL(currentNode);
            collectUrlsFromPage(rawHtml);
        }
    }

    private void collectUrlsFromPage(String rawHtml) {
        Matcher matcher = urlPattern.matcher(rawHtml);
        while (matcher.find()) {
            String actualUrl = matcher.group();
            if (!discoveredWebsiteList.contains(actualUrl)) {
                System.out.println("Found: " + actualUrl);
                discoveredWebsiteList.add(actualUrl);
                queue.add(actualUrl);
            }
        }
    }

    private String readURL(String currentNode) {
        StringBuilder rawHtml = new StringBuilder();
        try {
            URL url = new URL(currentNode);
            try (InputStreamReader streamReader = new InputStreamReader(url.openStream())) {
                try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        rawHtml.append(inputLine);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rawHtml.toString();
    }

    public static void main(String[] args) {
        WebCrawler webCrawler = new WebCrawler();
        webCrawler.discoverWeb("http://www.bbc.com");
    }


}
