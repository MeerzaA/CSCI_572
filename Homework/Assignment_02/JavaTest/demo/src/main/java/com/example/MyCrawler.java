package com.example;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Set;

// import org.apache.http.HttpStatus;
// import org.apache.http.Header;


public class MyCrawler extends WebCrawler { 
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|mp2|mp3|mp4|gif|jpg" 
    + "mpeg|mpg|ram|m4v|json|webmanifest|ttf|svg|wav|avi|mov||wma|wmv|mid|txt|zip|rar|gz|exe|ico))$"); 
    private HashSet<String> inList = new HashSet<>();
    /** 
    * This method receives two parameters. The first parameter is the page 
    * in which we have discovered this new url and the second parameter is 
    * the new url. You should implement this function to specify whether 
    * the given url should be crawled or not (based on your crawling logic). 
    * In this example, we are instructing the crawler to ignore urls that 
    * have css, js, git, ... extensions and to only accept urls that start 
    * with "http://www.viterbi.usc.edu/". In this case, we didn't need the 
    * referringPage parameter to make the decision. 
    */
    
    private String fetch = "";
    @Override
    protected void handlePageStatusCode(WebURL webUrl, int statusCode, String statusDescription) {
        String url = webUrl.getURL().toLowerCase().replaceAll(",", "_");
        fetch += url + "," + statusCode + "\n";
    }

    // protected void onUnexpectedStatusCode(String urlStr, int statusCode, String contentType,
    // String description) {
    // logger.warn("Skipping URL: {}, StatusCode: {}, {}, {}", urlStr, statusCode, contentType,
    // description); }
    // // Do nothing by default (except basic logging)
    // // Sub-classed can override this to add their custom functionality
    // https://github.com/yasserg/crawler4j/blob/master/crawler4j/src/main/java/edu/uci/ics/crawler4j/crawler/WebCrawler.java#L174

    private String urll = "";
    @Override 
    public boolean shouldVisit(Page referringPage, WebURL url) { 
        String href = url.getURL().toLowerCase().replace(",","_").replaceFirst("^(https?://)?(www.)?", "");
        
        inList.add(href);
        if (href.endsWith("/")) {
            href = href.substring(0, href.length() - 1);
        }

        boolean NotInList = !inList.contains(href);
        boolean nytimes = href.startsWith("nytimes.com");
        if (nytimes)
            urll += href + ",OK\n";
        else urll += href + ",N_OK\n";
        
        return !FILTERS.matcher(href).matches() && nytimes && NotInList;
        
    } 

    /** 
     * This function is called when a page is fetched and ready 
     * to be processed by your program. 
     */ 
    private String visit = "";

    @Override 
    public void visit(Page page) { 

        String url = page.getWebURL().getURL().toLowerCase().replace(",","_").replaceFirst("^(https?://)?(www.)?", "");
        int totalOutlinks = 0;
        int memory = page.getContentData().length;
        String contentType = page.getContentType().split(";")[0];

        boolean isCorrectType = contentType.contains("pdf") 
                            | contentType.contains("image") 
                            |contentType.contains("doc") 
                            | contentType.contains("html");
        if (!isCorrectType)
            return;

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();
            totalOutlinks += links.size();
        }

        visit += url + "," + memory + "," + totalOutlinks + "," + contentType + "\n";

   }

   @Override
   public Object getMyLocalData() {
       return new String[]{fetch, visit, urll};
   }
}
 