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
import org.jsoup.select.Elements;

/**
 *
 * @author mahmed27
 */
public class MitreCWEHtmlParser {
    private String outFilePath = "/Users/mahmed27/Desktop/CWE-Mitigation/";
    private String cweListUrlPath = "699.html";
    private String queryString[] = {"firewall", "IDS", "IPS", "NIDS", "NIPS", "HIDS", "HIPS", "intrusion detection system", "anomaly", "signature"};
    public void parseAllCWEList() throws IOException {
        Document htmlDocument = Jsoup.connect(URLRepository.mitreCWEURL + this.cweListUrlPath).get();
        Elements elements = htmlDocument.select("div.group");
        elements.forEach((e) -> {
            try {
                //            System.out.println(e.attr("cweid"));
                this.parseMitigationStrategy(e.attr("cweid").trim());
            } catch (IOException ex) {
                Logger.getLogger(MitreCWEHtmlParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void parseMitigationStrategy(String cweId) throws IOException {
        Document htmlDocument = Jsoup.connect(URLRepository.mitreCWEURL + cweId + ".html").get();
        Elements elements = htmlDocument.select("div#Potential_Mitigations");
        elements.forEach((e) -> {
            this.writeMitigationToFile(e.select("div.detail").text(), outFilePath + cweId + ".txt");
        });
    }
    
    private void writeMitigationToFile(String text, String filePath) {
        File newF = new File(filePath);
        BufferedWriter writer = null;
        try {
            newF.createNewFile();
            writer = new BufferedWriter(new FileWriter(newF, true));
            writer.write(text);
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
