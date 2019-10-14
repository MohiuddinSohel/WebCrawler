/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import URLData.URLRepository;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author mahmed27
 */
public class ThreatExpertHtmlPerser {
    private String urlPath = "/report.aspx?md5=";
    private String[] searchString = {"worm", "command and control", "c&c", "command&control", "command & control", "exfiltration"};
    private String[] notFoundHashString = {"sign in", "log in"};
    private String outFilePath = "MalwareHash.txt";
    private String allOutFilePath = "UnavailableMalwareHash.txt";
    //private Document htmlDocument;
    public ThreatExpertHtmlPerser() {
        //htmlDocument = null;
    }
    
    public void parseHtmlDocument(String hash) throws IOException {
        Document htmlDocument = Jsoup.connect(URLRepository.threatExpertURL + urlPath + hash).get();
//        System.out.println(htmlDocument.select("td").text());
        System.out.println(URLRepository.threatExpertURL + urlPath + hash);
        String htmlContent = htmlDocument.select("td").text();
        if(this.searchString(htmlContent, this.searchString)) {
            this.appendToFile(hash + "\n", this.outFilePath);
        } else if(this.searchString(htmlContent, this.notFoundHashString)) {
            this.appendToFile(hash + "\n", this.allOutFilePath);
        }
    }
    
    private boolean searchString(String htmlContent, String[] textToSerch) {
        for(String query : textToSerch) {
            if(htmlContent.toLowerCase().contains(query)) {
                return true;
            }
        }
        return false;
    }
    
    public void appendToFile(String fileName, String outfile) {
        File newF = new File(outfile);
        BufferedWriter writer = null;
        try {
            newF.createNewFile();
            writer = new BufferedWriter(new FileWriter(newF, true));
            writer.write(fileName);
        } catch (IOException ex) {
            Logger.getLogger(ThreatExpertHtmlPerser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreatExpertHtmlPerser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
