/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webcrawler;

import URLData.FileSelector;
import URLData.URLRepository;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mahmed27
 */
public class WebCrawler {

    /**
     * @param args the command line arguments
     */
//    public static void testVirusTotalAPI() {
//        String fileId = "5f518c65bfcba055ab35e49a32ddc24b92235aaceba706eab2378287bf2c391b";
//        VirusTotalAPI virusTotal = VirusTotalAPI.configure("7ce84136860c6912dc819e02c4b28c08f50ab1d0fec0cf83a02b5bb1d60375db");
//        FileScanReport fileReport = virusTotal.getFileReport(fileId);
//        Map scans = fileReport.getScans();
//        Date firstDetected = null;
//        for( Object o : scans.entrySet()) {
//            Entry<String, FileScan> entry = (Entry) o;
//            String scan = entry.getKey();
//            FileScan report = entry.getValue();
//            System.out.println("Scan Engine : " + scan);
//            if (report.isDetected()) {
//                System.out.println("Version : " + report.getVersion());
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//                Date date = null;
//                try {
//                    date = formatter.parse(report.getUpdate());
//                } catch (ParseException ex) {
//                    Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println("Update : " + date);
//                System.out.println("Update : " + report.getUpdate());
//                System.out.println("Malware : " + report.getMalware());
//               
//                if(firstDetected == null) {
//                    firstDetected = date;
//                } else if(firstDetected.compareTo(date) > 0) {
//                    firstDetected = date;
//                }
//            } else {
//                System.out.println("No Virus Detected");
//            }
//            
//        }
//        System.out.println("Detection date : " + firstDetected);
// /*       scans.keySet().stream().forEach((scan) -> {
//            FileScan report = (FileScan) scans.get(scan);
//            System.out.println("Scan Engine : " + scan);
//            if (report.isDetected()) {
//                System.out.println("Version : " + report.getVersion());
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//                Date date = null;
//                try {
//                    date = formatter.parse(report.getUpdate());
//                } catch (ParseException ex) {
//                    Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                System.out.println("Update : " + date);
//                System.out.println("Update : " + report.getUpdate());
//                System.out.println("Malware : " + report.getMalware());
////               previous = date;
////                if(previous != null && previous.compareTo(date) <= 0) {
////                }
//            } else {
//                System.out.println("No Virus Detected");
//            }
//        });*/
//    
//    }
    
//    void traverseDirectoryForMalware(File node) {
//        if(node.isDirectory()) {
//            String[] nodeList = node.list();
//            for(String lowerNode : nodeList) {
//                traverseDirectoryForMalware(new File(node, lowerNode));
//            }
//        } else {
//        }
//    }
    
    public static void main(String[] args) throws Exception {
//        String date = "Aug 8., 2017, 11:16 a.m.";
//        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//        Date specifiedDate = format.parse(date.replaceAll("\\.", ""));
//        System.out.println(specifiedDate);
//        testVirusTotalAPI();
//        MalwrParser.downloadSample();
//        MalwrParser p = new MalwrParser();
//        p.creteStatsBetweenVirusTotalAndMalwr();
//        p.parseAllPages("/analysis/");
//        p.parseInRangeReport("/analysis/?page=21", "June 24, 2017");
        // TODO code application logic here
        VirusTotalParser a = new VirusTotalParser();
        a.parseAnalysisDetails(null);
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FileSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        //</editor-fold>
//
//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FileSelector().setVisible(true);
            }
        });
    }
    
}
