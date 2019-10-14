/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URLData;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import webcrawler.HybridAnalysisParser;
import webcrawler.MalwareParse;
import webcrawler.MalwrParser;
import webcrawler.MitreCWEHtmlParser;
import webcrawler.ThreatExpertHtmlPerser;
import webcrawler.VirusTotalParser;

/**
 *
 * @author mahmed27
 */
public class FileSelector extends javax.swing.JFrame {

    /**
     * Creates new form FileSelector
     */
    public FileSelector() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Select File for ThreatExpert");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select file for VirusTotal");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Select file for Hybrid analysis");
        jButton3.setToolTipText("Collect all available URL from hybrid analysis");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Download Mitigation from Mitre CWE");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Malwr- By page");
        jButton5.setToolTipText("Collect hashes from provided page and afterwards");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Malwr- By date");
        jButton6.setToolTipText("Collect all hash within current and provided date(Exclusive)");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Collect VirusSign sample Hash");
        jButton7.setToolTipText("Collect All Hashes of Virus Sign sample");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Find Malwr stats");
        jButton8.setToolTipText("Collect online Matched report from malwr.com ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("VirusSign Hash and Malwr Stats");
        jButton9.setActionCommand("Collect and Calculate VirusSign Hash and malwr Stats");
        jButton9.setAutoscrolls(true);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Parse Rank");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addGap(0, 42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            ThreatExpertHtmlPerser parser = new ThreatExpertHtmlPerser();
            for (File file : files) {
                int beginIndex = file.getName().indexOf("_");
                int endIndex = file.getName().lastIndexOf(".");
                try {
                    parser.parseHtmlDocument(file.getName().substring(beginIndex + 1, endIndex));
                }catch (IOException ex) {
                    Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(this, "Searching for WORM finished");
        } else if(result == JFileChooser.CANCEL_OPTION) {}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
            VirusTotalParser parser = new VirusTotalParser();
            int matched = 0,  notmatched = 0;
            for (File file : files) {
                try {
                    if(parser.parseAnalysisDetails(file)){
                        matched++;
                    }else{
                        notmatched++;
                    }
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            System.out.println("Matched: " + matched + " Not Matched: " + notmatched);
            JOptionPane.showMessageDialog(this, "Matched: " + matched + " Not Matched: " + notmatched);
        } else if(result == JFileChooser.CANCEL_OPTION) {}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        MitreCWEHtmlParser p = new MitreCWEHtmlParser();
        try {
            p.parseAllCWEList();
        } catch (IOException ex) {
            Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(this, "Collecting all CWE mitigations are completed");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        File outputFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
//            String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
            outputFile = this.selectFile("Select the output file");
            HybridAnalysisParser parser = new HybridAnalysisParser();
            int matched = 0,  notmatched = 0;
            for (File file : files) {
                try {
                    if(parser.parseAnalysisDetails(file, outputFile)) matched++;
                    else notmatched++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            System.out.println("Matched: " + matched + " Not Matched: " + notmatched);
            JOptionPane.showMessageDialog(this, "Matched: " + matched + " Not Matched: " + notmatched);
        } else if(result == JFileChooser.CANCEL_OPTION) {}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {                                         
            // TODO add your handling code here:
            String date = JOptionPane.showInputDialog(this, "Input the date in MMMM d, yyyy format(Reports between provided and current date(Exclusive) will be collected)");
            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
            try {
                Date d = format.parse(date.trim());
            } catch (ParseException ex) {
                Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Date is not correct", "Format Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
            File outputFile = this.selectFile("Select the output File");
            MalwrParser p = new MalwrParser();
            p.parseInRangeReport("/analysis/", date, outputFile);
            JOptionPane.showMessageDialog(this, "Collecting Reports between provided and current date(Exclusive) have been completed");
        } catch (IOException|ParseException ex) {
            JOptionPane.showMessageDialog(this, "Exception");
            Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        File outputFile = null;
        try {                                         
            String pageNo = JOptionPane.showInputDialog(this, "Input the starting page number");
            int page = Integer.parseInt(pageNo.trim());
//            String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
            outputFile = this.selectFile("Select the output file");
            MalwrParser p = new MalwrParser();
            p.parseAllPages("/analysis/?page=" + page, outputFile);
            JOptionPane.showMessageDialog(this, "Collecting reports from provided page and afterwards have been completed");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Exception");
//            Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here: 
        File outputFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
//            String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
            outputFile = this.selectFile("Select the output file");
            for (File file : files) {
                try {
                    URLRepository.collectAllHashesOfVirusSignSample(file, outputFile);
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(this, "Collecting all hashes has been completed");
        } else if(result == JFileChooser.CANCEL_OPTION) {}
    }//GEN-LAST:event_jButton7ActionPerformed
   
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
//        String virusSignHashPath = JOptionPane.showInputDialog(this, "Input the virusSignHash FILE NAME with path");
//        String malwrHashPath = JOptionPane.showInputDialog(this, "Input the malwrHash FILE NAME with path");
//        String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
        File virusSignHashFile = this.selectFile("Select the virussign.com Hash file");
        File malwrHashFile = this.selectFile("Select the malwr.com Hash file");
        File outputFile = this.selectFile("Select the output file");
        URLRepository.creteStatsBetweenVirusSignAndMalwr(virusSignHashFile, malwrHashFile, outputFile);
        JOptionPane.showMessageDialog(this, "Finding the online malwr URL is completed");
    }//GEN-LAST:event_jButton8ActionPerformed

    File selectFile(String title) {
        File selectedFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
        }
        return selectedFile;
    }
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
         
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles();
//            String virusSignHashPath = JOptionPane.showInputDialog(this, "Input the virusSignHash FILE NAME with path");
//            String malwrHashPath = JOptionPane.showInputDialog(this, "Input the malwrHash FILE NAME with path");
//            String outFilePath = JOptionPane.showInputDialog(this, "Input the output FILE NAME with path");
            
            File virusSignHashFile = this.selectFile("Select the virussign.com Hash file");
            File malwrHashFile = this.selectFile("Select the malwr.com Hash file");
            File outputFile = this.selectFile("Select the output file");
            for (File file : files) {
                try {
                    URLRepository.collectmalwrComAvailablereportbasedOnVirusSignSample(file, malwrHashFile, virusSignHashFile, outputFile);
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JOptionPane.showMessageDialog(this, "Collecting all hashes has been completed");
        } else if(result == JFileChooser.CANCEL_OPTION) {}
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        File inputFileName = this.selectFile("Select Input File");
        File outputFileName = this.selectFile("Select Output file");
        if(inputFileName != null && outputFileName != null ) {
            MalwareParse parser = new MalwareParse();
            try {
                parser.createAnalysisFile(inputFileName, outputFileName);
            } catch (Exception ex) {
                Logger.getLogger(FileSelector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    // End of variables declaration//GEN-END:variables
}