using HtmlAgilityPack;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ThesaurousCrawler
{
    static class Program
    {
       
        private static string thesaurusUrl = "http://www.thesaurus.com/browse/";
        private static string path = @"";
        private static string recommendationPath = @"D:\project\Recommendation\";
        private static string statsPath = @"D:\project\statisticsUp.csv";
        private static string reportPath = @"D:\project\ThreatReportAll\";
        private static SortedDictionary<int, int> dict = new SortedDictionary<int, int>();


  /*      static void Main()
        {
            string word = Console.ReadLine();
            Program.appendUrlToFile(Program.getAllSynonyms(word, "verb"));

        }*/
        private static string symmantecUrl = "https://www.symantec.com";

        static void Main(string[] args)
        {

            //callStatics();
            callReport();
            //callRecommendation();
            
        }

        static void callStatics() {
            traverseAllLetterForDate();
            writeStatsToFile();
        }

        static void callReport() {
            traverseAllLetterForReport();
        }

        static void callRecommendation() {
            traverseAllLetter();
        }

        static string traverseAllLetter()
        {
            string htmlContent = null;
            for (char letter = 'A'; letter <= 'Z'; letter++)
            {
                htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=" + letter);
                if (!String.IsNullOrEmpty(htmlContent)) traverseTheTable(htmlContent);
            }
            htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=_1234567890");
            if (!String.IsNullOrEmpty(htmlContent)) traverseTheTable(htmlContent);
            return null;
        }

        static void traverseTheTable(string htmlContent)
        {
            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            if (htmlContent != null)
            {
                doc.LoadHtml(htmlContent);
                //Console.WriteLine(htmlContent);
                HtmlNode table = doc.DocumentNode.SelectSingleNode("//table[@class='defaultTableStyle tableFontMD tableNoBorder']");
                if (table != null)
                {
                    HtmlNodeCollection nodeList = table.SelectSingleNode("tbody").SelectNodes("tr");
                    if (nodeList == null) return;
                    foreach (HtmlNode row in nodeList)
                    {
                        //Console.WriteLine(row.InnerText);

                        HtmlNode node = row.SelectSingleNode(".//td//a");
                        if (node != null)
                        {
                            string recommendation = getTheRecommendations(node.Attributes["href"].Value);
                            string fileName = Program.recommendationPath + MakeValidFileName(node.InnerText) + "_recommendation.txt";
                            if (!String.IsNullOrEmpty(recommendation)) writeRecommendationToFile(fileName, recommendation);
                        }
                    }
                }
            }
        }

        static string traverseAllLetterForReport()
        {
            string htmlContent = null;
            for (char letter = 'A'; letter <= 'Z'; letter++)
            {
                htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=" + letter);
                if (!String.IsNullOrEmpty(htmlContent)) traverseTheTableForReport(htmlContent);
            }
            htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=_1234567890");
            if (!String.IsNullOrEmpty(htmlContent)) traverseTheTableForReport(htmlContent);
            return null;
        }

        static void traverseTheTableForReport(string htmlContent)
        {
            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            if (htmlContent != null)
            {
                doc.LoadHtml(htmlContent);
                HtmlNode table = doc.DocumentNode.SelectSingleNode("//table[@class='defaultTableStyle tableFontMD tableNoBorder']");
                if (table != null)
                {
                    HtmlNodeCollection nodeList = table.SelectSingleNode("tbody").SelectNodes("tr");
                    if (nodeList == null) return;
                    foreach (HtmlNode row in nodeList) {
                        int i = 0;
                        string url = null;
                        string fileName = "";
                        /*foreach (HtmlNode cell in row.SelectNodes("td"))  {
                            i++;
                            string inString = cell.InnerText.Trim();
                            if (i == 2 ) {
                                if (inString.ToLower().Contains("android") || inString.ToLower().Contains("adware"))
                                {
                                    break;
                                }
                                else { 
                                    HtmlNode node = cell.SelectSingleNode(".//a");
                                    url = node.Attributes["href"].Value;
                                    fileName = Program.reportPath + MakeValidFileName(inString) + "_ThreatReport.txt";
                                }
                            }
                            else if (i == 3 && !inString.ToLower().Equals("trojan")) {
                                break;
                            }
                            else if (url != null)
                            {
                                string report = getTheReport(url);
                                if (!String.IsNullOrEmpty(report)) writeRecommendationToFile(fileName, report);
                            }
                   
                        }*/
                        HtmlNode node = row.SelectSingleNode(".//td//a");
                        if (node != null)
                        {
                            url = node.Attributes["href"].Value;
                            fileName = Program.reportPath + MakeValidFileName(node.InnerText) + "_ThreatReport.txt";
                            string report = getTheReport(url);
                            if (!String.IsNullOrEmpty(report)) writeRecommendationToFile(fileName, report);
                        }
                    }
                }
            }
        }


        static string traverseAllLetterForDate() {
            string htmlContent = null;
            int totalRow = 0;
            for (char letter = 'A'; letter <= 'Z'; letter++)
            {
                Console.WriteLine("Page: " + letter);
                htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=" + letter);
                if (!String.IsNullOrEmpty(htmlContent)) totalRow += traverseTheTableForDate(htmlContent);
            }
            Console.WriteLine("Page: " + "_1234567890");
            htmlContent = GetWebContent(symmantecUrl + "/security_response/landing/azlisting.jsp?azid=_1234567890");
            if (!String.IsNullOrEmpty(htmlContent)) totalRow += traverseTheTableForDate(htmlContent);
            Console.WriteLine("Total Row: " + totalRow);
            return null;
        }

        static int traverseTheTableForDate(string htmlContent)
        {
            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            if (htmlContent != null)
            {
                doc.LoadHtml(htmlContent);
                HtmlNode table = doc.DocumentNode.SelectSingleNode("//table[@class='defaultTableStyle tableFontMD tableNoBorder']");
                if (table != null)
                {
                    HtmlNodeCollection nodeList = table.SelectSingleNode("tbody").SelectNodes("tr");
                    if (nodeList == null) return 0;
                    Console.WriteLine("Per page Count: " + nodeList.Count);
                    int year = 1980;
                    foreach (HtmlNode row in nodeList) {
                        
                        int i = 0;
                        foreach(HtmlNode cell in row.SelectNodes("td")) {
                            i++;
                            if (i == 4) {
                                DateTime dDate;
                                string inString = cell.InnerText.Trim();
                                
                                if (DateTime.TryParse(inString, out dDate))
                                {
                                    String.Format("MM/dd/yyyy", dDate);
                                    year = dDate.Year;
                                    if (dict.ContainsKey(year)) {
                                        dict[year] += 1;
                                    }
                                    else {
                                        dict.Add(year, 1);
                                    }
                                }
                                else {
                                    /*if (dict.ContainsKey(year))
                                    {
                                        dict[year] += 1;
                                    }
                                    else
                                    {
                                        dict.Add(year, 1);
                                    }*/

                                    if (dict.ContainsKey(2019))
                                    {
                                        dict[2019] += 1;
                                    }
                                    else
                                    {
                                        dict.Add(2019, 1);
                                    }
                                }
                                if (dict.ContainsKey(2020))
                                {
                                    dict[2020] += 1;
                                }
                                else
                                {
                                    dict.Add(2020, 1);
                                }
                            }
                        }
                    }
                    return nodeList.Count;
                }
            }
            return 0;
        }

        static string getTheRecommendations(String url) {
            string htmlContent = GetWebContent(symmantecUrl + url + "&tabid=2");
            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            if (!String.IsNullOrEmpty(htmlContent))
            {
                doc.LoadHtml(htmlContent);
                String recommendation = "";

                HtmlNodeCollection recommendationList = doc.DocumentNode.SelectNodes("//ul[@class='listSQbl']//li");
                if (recommendationList == null) return null;
                foreach (HtmlNode node in recommendationList)
                {
                    recommendation += " " + node.InnerText;
                }
                return recommendation;
            }
            return null;
        }

        static string getTheReport(string url) {
            string htmlContent = GetWebContent(symmantecUrl + url + "&tabid=2");
            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            if (!String.IsNullOrEmpty(htmlContent))
            {
                htmlContent = htmlContent.Replace("</li>", "<br></li>");
                htmlContent = htmlContent.Replace("</LI>", "<br></LI>");
                htmlContent = htmlContent.Replace("<br>", "\n");
                doc.LoadHtml(htmlContent);

                HtmlNode wholeReport = doc.DocumentNode.SelectSingleNode("//div[@class='imgMrgnTopLG imgMrgnBtmLG listBullet']");
                if (wholeReport == null) return null;
                return wholeReport.InnerText;
            }
            return null;
        }

        static string GetWebContent(string url) {
            //Console.WriteLine(url);
            WebRequest req = HttpWebRequest.Create(url);
            req.Method = "GET";

            string source = null;
            using (StreamReader reader = new StreamReader(req.GetResponse().GetResponseStream()))
            {
                source = reader.ReadToEnd();
            }

            return source;
        }


        static List<string> getAllSynonyms(string wordToSearch, string partsOfSpeech) {
            string url = Program.thesaurusUrl;
            url += wordToSearch + "/" + partsOfSpeech;
            return Program.collectAllSynonymsByPOS(Program.GetWebContent(url), partsOfSpeech);
        }

        static void getPartsOfSpeechFilterList(HtmlNode filter) {
            Console.Write(filter.InnerHtml);
            HtmlNodeCollection listItems = filter.SelectNodes(".//select[@id='part-of-speech-filter']//option");
            //Console.Write(listItems.InnerHtml);
           // HtmlNodeCollection tablerow = listItems.SelectNodes("");
            //Console.Write(tablerow);
            foreach (HtmlNode tr in listItems)
            {
                Console.Write(tr.InnerText);
                Console.Write("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            }
        }

        static List<string> collectAllSynonymsByPOS(string pageContent, string partsOfSpeach)
        {

            HtmlAgilityPack.HtmlDocument doc = new HtmlAgilityPack.HtmlDocument();
            HtmlNode.ElementsFlags.Remove("option");
            doc.LoadHtml(pageContent);

            //getPartsOfSpeechFilterList(doc.DocumentNode.SelectSingleNode("//div[@class='part-of-speech']//div[@class='main']//div[@class='part-of-speech-filter']"));
            HtmlNode synonyms = doc.DocumentNode.SelectSingleNode("//div[@id='container']//div[@id='content']//div[@class='main-content']//div[@class='main-content-holder']//div[@class='synonyms_wrapper']//div[@id='synonyms-0']//div[@class='filters']//div[@class='relevancy-block']//div[@class='relevancy-list']");
            HtmlNodeCollection listItems = synonyms.SelectNodes("ul");
            int i = 0;
            List<string> synonymList = new List<string>();
            foreach (HtmlNode tr in listItems) {
                HtmlNodeCollection inners = tr.SelectNodes(".//li");
                foreach(HtmlNode li in inners) {
                   HtmlNode text = li.SelectSingleNode(".//a/span[@class='text']");
                   synonymList.Add(text.InnerText);
                   //Console.Write(text.InnerText);
                   //Console.WriteLine(" ");
                  // i++;
                }
            }
            return synonymList;
        }

        

        static void appendUrlToFile(List<string> synonymList) {
            //if (!File.Exists(Program.path)) {
                using (StreamWriter sw = File.CreateText(Program.path))
                {
                    
                    foreach(string s in synonymList) {
                        sw.WriteLine(s);
                    }
                    
                }
           // }
            /*else {
                using (StreamWriter sw = File.AppendText(path))
                {

                    foreach (string s in synonymList)
                    {
                        sw.WriteLine(s);
                    }
                }
            }*/

        }
        private static string MakeValidFileName(string name)
        {
            string invalidChars = System.Text.RegularExpressions.Regex.Escape(new string(System.IO.Path.GetInvalidFileNameChars()));
            string invalidRegStr = string.Format(@"([{0}]*\.+$)|([{0}]+)", invalidChars);

            return System.Text.RegularExpressions.Regex.Replace(name, invalidRegStr, "_");
        }

        static void writeStatsToFile() {
            if (!File.Exists(Program.statsPath))
            {
                using (StreamWriter sw = File.CreateText(Program.statsPath))
                {
                    int tCount = 0;
                    foreach (var item in dict) {
                        tCount += item.Value;
                        sw.WriteLine(item.Key + "," + item.Value + "," + tCount);
                    }
                    
                    sw.Close();
                }
            }
        }
        
        static void writeRecommendationToFile(string filename, string recommendation)
        {
            if (!File.Exists(filename))
            {
                using (StreamWriter sw = File.CreateText(filename))
            {
                sw.Write(recommendation);
                sw.Close();
            }
             }
            /*else {
                using (StreamWriter sw = File.AppendText(filename))
                {
                    sw.Write(recommendation);
                    sw.Close();
                }
            }*/

        }



    }
}
