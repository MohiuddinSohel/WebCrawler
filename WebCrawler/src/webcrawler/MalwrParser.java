/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import URLData.URLRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.commons.io.FileUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mahmed27
 */
public class MalwrParser {
    private final String malwrSearchURL = "https://malwr.com";
    private boolean willGoNextPage = true; 
    
    public void toggleWillGo() {
        willGoNextPage = !willGoNextPage;
    }
    public static void downloadSample() throws MalformedURLException, IOException {
        String url2 = "https://malwr.com/";
        String url1 = "https://malwr.com/analysis/file/NjdkMzNkZjM3YTQzNDg0MDgxNTUxZDRhM2E2NTE2NTg/sample/88aeb2962b57fac22f52e5d624cd17c52ecfe2858eab7aee115fd798b816f687/";
        Response htmlDocument = null;
        try {
            htmlDocument = Jsoup.connect(url2).ignoreContentType(true).execute();
            Map<String, String> resHeader = htmlDocument.headers();
            System.out.println(resHeader.get("Connection"));
        } catch( Exception e) { 
            e.printStackTrace();
            return;
        } 
//        File destination = new File("/Users/mahmed27/Desktop/Dr. Jinpeng/ downloaded");
//        URL url = new URL(url1); 
//        FileUtils.copyURLToFile(url, destination);
    }
    
    public void parseAllPages(String pageUrl, File outFilePath) throws Exception {
        if(outFilePath == null) return;
        Document htmlDocument = null;
        try {
            
            htmlDocument = Jsoup.connect(malwrSearchURL + pageUrl).timeout(10*1000).get();
        } catch(org.jsoup.HttpStatusException | java.net.UnknownHostException e) { 
//            e.printStackTrace();
            return;
        } catch(java.net.SocketTimeoutException e) {
            htmlDocument = Jsoup.connect(malwrSearchURL + pageUrl).get();
        }
        
        Element pagination = htmlDocument.select("div.pagination").get(0);
        Element table = htmlDocument.select("table.table").get(0);
        String outText = parseMD5HashFromTable(table);
        URLRepository.appendToFile(outText, outFilePath);//"/Users/mahmed27/Desktop/Dr. Jinpeng/MalwrHashes.csv");
        
        String pageURL = null;
        while((pageURL = getNextPageURL(pagination)) != null || !pageURL.isEmpty()) {
            System.out.println(pageURL);
            parseAllPages(pageURL, outFilePath);
//            System.out.println(pageURL);
        }
    }
    
    public void parseInRangeReport(String pageUrl, String date, File outFilePath) throws IOException, ParseException {
        if(outFilePath == null) return;
        Document htmlDocument = null;
        try {
            System.out.println(malwrSearchURL + pageUrl);
            htmlDocument = Jsoup.connect(malwrSearchURL + pageUrl).timeout(10*1000).get();
        } catch(org.jsoup.HttpStatusException | java.net.UnknownHostException e) { 
            e.printStackTrace();
            return;
        } catch(java.net.SocketTimeoutException e) {
            htmlDocument = Jsoup.connect(malwrSearchURL + pageUrl).timeout(10*1000).get();
            e.printStackTrace();
            return;
        }
        
        Element pagination = htmlDocument.select("div.pagination").get(0);
        Element table = htmlDocument.select("table.table").get(0);
        String outText = MalwrParser.this.parseMD5HashFromTable(table, date);
        if(outText != null && !outText.isEmpty()) {
            URLRepository.appendToFile(outText, outFilePath);//"/Users/mahmed27/Desktop/Dr. Jinpeng/MalwrHashes.csv");
//           System.out.println(outText);
        }
        String pageURL = getNextPageURL(pagination);
        while(willGoNextPage && pageURL != null && !pageURL.isEmpty()) {
            System.out.println(pageURL);
            parseInRangeReport(pageURL, date, outFilePath);
        }
    }
    
    private String getNextPageURL(Element pagination) {
        if(!willGoNextPage) {
            return null;
        }
        Elements lists = pagination.select("li");
        for(Element item: lists) {
            if(item.text().contains("Next")) {
                return item.select("a").get(0).attr("href");
            }
        }
        return null;
    }
    
    private String parseMD5HashFromTable(Element table) {
        Elements rows = table.select("tbody").get(0).select("tr");
        String outLine = "";
        for(Element row : rows) {
            String hash = row.select("span.mono").get(0).text();
            String reportLink = malwrSearchURL + row.select("a").get(0).attr("href");
            outLine += hash + "," +  reportLink + ",\"" + row.select("td").get(0).text() + "\"\n";      
        }
        return outLine;
    }
    
    private String parseMD5HashFromTable(Element table, String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date specifiedDate = format.parse(date);
        Date date2 = new Date();
        Date currentDate = format.parse(format.format(date2));
        
        Elements rows = table.select("tbody").get(0).select("tr");
        String outLine = "";
        for(Element row : rows) {
            String reportDate = row.select("td").get(0).text().replaceAll("\\.", "");
            Date reportsDate = format.parse(reportDate);
            if(reportsDate.compareTo(specifiedDate) <= 0) {
                willGoNextPage = false;
                break;
            }
            else if(reportsDate.compareTo(currentDate) != 0) {
                String hash = row.select("span.mono").get(0).text();
                String reportLink = malwrSearchURL + row.select("a").get(0).attr("href");
                outLine += hash + "," +  reportLink + ",\"" + reportDate + "\"\n";
            }      
        }
        return outLine;
    }
    
    
    
}

