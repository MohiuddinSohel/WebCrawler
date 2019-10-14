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
public class HybridAnalysisParser {
    private String searchUrlEnd = "?environmentId=100";
    private String hybridAnalysisSearchURL = "https://www.hybrid-analysis.com/sample/";
    
    public boolean parseAnalysisDetails(File file, File outFilePath) throws NoSuchAlgorithmException, IOException {
        if(outFilePath == null) return false;
        String sha256Hash = URLRepository.getSHA256OfAFile(file);
        String md5Hash = URLRepository.getMD5OfAFile(file);
        String url = this.hybridAnalysisSearchURL + sha256Hash;
        Document htmlDocument = null;
        try{
            htmlDocument = Jsoup.connect(url).timeout(10*1000).get();
        } catch(org.jsoup.HttpStatusException | java.net.UnknownHostException e){
            //e.printStackTrace();
            return false;
        }
        Elements sampleSignatures = htmlDocument.select("section#sample-signatures");
        if(sampleSignatures != null && sampleSignatures.size() > 0) {
//            Element table = sampleSignatures.get(0).select("table#antivirus-results").get(0);
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
//            }
//            System.out.println(url);
            URLRepository.appendToFile(md5Hash + "," + sha256Hash + "," + url + "\n", outFilePath);
            return true;
        } else {
//            System.out.println("Result not found");
            return false;
        }
    }
    
    public void creteStatsBetweenVirussignAndhybridAnalysis() {
        
    }
}
