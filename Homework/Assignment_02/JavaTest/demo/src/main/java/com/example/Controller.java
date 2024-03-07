package com.example;

import java.io.IOException;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception { 
            
        Report_Builder csv = new Report_Builder();

        Build_FinalReport report = new Build_FinalReport();

        report.report();

        String crawlStorageFolder = "crawl"; 
        int numberOfCrawlers = 16; 
        
        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder); 
        config.setMaxPagesToFetch(30);
        config.setMaxDepthOfCrawling(16);
        config.setPolitenessDelay(360);
        config.setIncludeBinaryContentInCrawling(true);
        config.setUserAgentString(
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

 
        /* 
         * Instantiate the controller for this crawl. 
         */ 
        PageFetcher pageFetcher = new PageFetcher(config); 
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig(); 
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher); 
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer); 
 
        /* 
         * For each crawl, you need to add some seed urls. These are the first 
         * URLs that are fetched and then the crawler starts following links 
         * which are found in these pages 
         */ 
        controller.addSeed("http://www.nytimes.com/"); 
        /* 
         * Start the crawl. This is a blocking operation, meaning that your code 
         * will reach the line after this only when crawling is finished. 
         */
        controller.start(MyCrawler.class, numberOfCrawlers); 

        StringBuilder fetch = new StringBuilder("URL,Status\n");
        StringBuilder visit = new StringBuilder("URL,Download Size,# Of Outgoing Links,Content-type\n");
        StringBuilder urll = new StringBuilder("URL,Status\n");

        for (Object t : controller.getCrawlersLocalData()) {
            String[] csvfiles = (String[]) t;
            fetch.append(csvfiles[0]);
            visit.append(csvfiles[1]);
            urll.append(csvfiles[2]);
        }

        try {
            csv.reportFiles(fetch, "fetch_nytimes.csv");
            csv.reportFiles(visit, "visit_nytimes.csv");
            csv.reportFiles(urll, "urls_nytimes.csv");
            System.out.println("it Worked; HOLY SHIT");
        } catch (IOException e) {
            System.err.println("This is what happened ->" + e.getMessage());
        }

    } 
}