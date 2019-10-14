/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URLData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import webcrawler.MalwrParser;
import webcrawler.ThreatExpertHtmlPerser;

/**
 *
 * @author mahmed27
 */
public class URLRepository {
    public static final String threatExpertURL = "http://www.threatexpert.com";
    public static final String mitreCWEURL = "https://cwe.mitre.org/data/definitions/";
    public static Map<String, String> vsHashes = new HashMap();
    
    public static String getMD5OfAFile(File selectedFile) throws NoSuchAlgorithmException, IOException {
        MessageDigest md5Digest = MessageDigest.getInstance("MD5");
        String checksum = getFileChecksum(md5Digest, selectedFile);
//        System.out.println("MD5 Hash:" + checksum);
        return checksum;
    }
        
    public static String getSHA256OfAFile(File selectedFile) throws NoSuchAlgorithmException, IOException {
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        String checksum = getFileChecksum(sha256Digest, selectedFile);
//        System.out.println("SHA256 Hash:" + checksum);
        return checksum;
    }
    
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
   
        FileInputStream fis = new FileInputStream(file);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static void appendToFile(String outText, File newF) {
//        File newF = new File(fileName);
        BufferedWriter writer = null;
        try {
            newF.createNewFile();
            writer = new BufferedWriter(new FileWriter(newF, true));
            writer.write(outText);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(ThreatExpertHtmlPerser.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
              ex.printStackTrace();
            Logger.getLogger(ThreatExpertHtmlPerser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void collectAllHashesOfVirusSignSample(File file, File outFilePath) throws NoSuchAlgorithmException, IOException {
        if(outFilePath == null) return ;
        String sha256Hash = URLRepository.getSHA256OfAFile(file).toLowerCase();
        String md5Hash = URLRepository.getMD5OfAFile(file).toLowerCase();
        String filePath = file.getAbsolutePath();
        String outLine = md5Hash + "," + sha256Hash + "," + filePath + "\n";
        URLRepository.appendToFile(outLine, outFilePath);// "/Users/mahmed27/Desktop/Dr. Jinpeng/AllVirusSignSampleHashesMD5SHA256.csv");
    }
    
    public static void collectAllHashesOfVirusSignSamplemd5(File file, File outFilePath) throws NoSuchAlgorithmException, IOException {
        if(outFilePath == null) return ;
        String md5Hash = URLRepository.getMD5OfAFile(file).toLowerCase();
        String filePath = file.getAbsolutePath();
        String outLine = md5Hash + "," + filePath + "\n";
        URLRepository.appendToFile(outLine, outFilePath);//"/Users/mahmed27/Desktop/Dr. Jinpeng/VirusSignSampleHashesmd5.csv");
    }
    
    public static boolean collectmalwrComAvailablereportbasedOnVirusSignSample(File malwareSample
            , File malwrHashFilePath, File vsHashFilePath, File outFilePath) throws NoSuchAlgorithmException, IOException {
        if(malwrHashFilePath == null || vsHashFilePath == null || outFilePath == null) {
            return false;
        }
        if(vsHashes.size() < 2) {
            try (BufferedReader br = new BufferedReader(new FileReader(malwrHashFilePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                   // process the line.
                   if(line.contains("MD5")) continue;
                   String in[] = line.trim().split(",");
                   if(in.length > 1) {
                       vsHashes.put(in[0].toLowerCase().trim(), in[1].toLowerCase().trim());
                   }
            }
            br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IOException ex) {
                Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            System.out.println("virus sign sample size:" + vsHashes.size());
        }
        String sha256Hash = URLRepository.getSHA256OfAFile(malwareSample).toLowerCase();
        String md5Hash = URLRepository.getMD5OfAFile(malwareSample).toLowerCase();
        
        if(vsHashes.containsKey(md5Hash)) {
            String outText = md5Hash + "," + sha256Hash + "," +  vsHashes.get(md5Hash) + "\n";
            String outText1 = md5Hash + "," + sha256Hash + "," +  malwareSample.getAbsolutePath() + "\n";
            URLRepository.appendToFile(outText, outFilePath);
            URLRepository.appendToFile(outText1, vsHashFilePath);
            System.out.println(md5Hash);
            return true;
        } else {
            return false;
        }
    }
    
    public static void creteStatsBetweenVirusSignAndMalwr(File vsHashFile, File malwrHashFile, File outputFile) {
        if(malwrHashFile == null || vsHashFile == null || outputFile == null) return;
        int matched = 0;
        int notfound = 0;
        
        if(vsHashes.size() < 2) {
            try (BufferedReader br = new BufferedReader(new FileReader(malwrHashFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                   // process the line.
                   if(line.contains("MD5")) continue;
                   String in[] = line.trim().split(",");
                   if(in.length > 1) {
                       vsHashes.put(in[0].toLowerCase().trim(), in[1].toLowerCase().trim());
                   }
                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("virus sign sample size:" + vsHashes.size());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(vsHashFile))) {
            String line;
            while ((line = br.readLine()) != null) {
               // process the line.
               if(line.contains("MD5")) {
                   continue;
               }
               String in[] = line.trim().split(",");
               
               if(in.length > 1) {
                   if(vsHashes.containsKey(in[0].toLowerCase().trim())) {
                       matched++;
                       String outText = in[0].toLowerCase().trim() + "," + in[1].toLowerCase().trim() + "," +  vsHashes.get(in[0]) + "\n";
                       URLRepository.appendToFile(outText, outputFile);
                       System.out.println(in[0]);
                   } else {
                       notfound++;
                   }
               }
            }
//            System.out.println(line);
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MalwrParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Matched: " + matched + " Not Matched: " + notfound);
    }
}
