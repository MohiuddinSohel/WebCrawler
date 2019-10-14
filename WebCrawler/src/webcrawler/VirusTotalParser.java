/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import URLData.URLRepository;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author mahmed27
 */
public class VirusTotalParser {
    private String searchUrlEnd = "/analysis/";
    private String virusTotalSearchURL = "https://www.virustotal.com/en/file/";
    
    public boolean parseAnalysisDetails(File file) throws NoSuchAlgorithmException, IOException {
        String sha256Hash = URLRepository.getSHA256OfAFile(file);
        String url = this.virusTotalSearchURL + sha256Hash + this.searchUrlEnd;
        Document htmlDocument = null;
        try {
            htmlDocument = Jsoup.connect(url).followRedirects(true).get();
        } catch(org.jsoup.HttpStatusException | java.net.UnknownHostException e) { 
            //e.printStackTrace();
            return false;
        }
        Elements element = htmlDocument.select("div#analysis");
        if(element != null && element.size() > 0) {
//            Element table = element.get(0).select("table#antivirus-results").get(0);
//            Elements rows = table.select("tr");
//            int i = 0;
//            for(Element row: rows) {
//                if(i == 0) {
//                    i++;
//                    continue;
//                }
//                Elements cols = row.select("td");
//                System.out.println(cols.text());
//                System.out.println(cols.get(1).text() + " Size: " + cols.size());
            System.out.println(url);
            return true;
//            }
        } else {
//            System.out.println("Result not found");
            return false;
        }
    }
    
}
