package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sleepycat.je.tree.IN;

import java.io.FileReader;

public class Build_FinalReport {

    public List<String> readfetch() throws IOException {
        FileReader fileReader = new FileReader("fetch_nytimes.csv");
        BufferedReader csvReader = new BufferedReader(fileReader);
    
        List<String> getCodes = new ArrayList<>();
        
        System.out.print(csvReader);

        String line;
        while ((line = csvReader.readLine()) != null) {
            
            String[] tokens = line.split(",");
            String statusString = tokens[1].trim();
            getCodes.add(statusString);
        }
        csvReader.close();
    
        return getCodes;
    }  
     
    
    public List<String> readurls() throws IOException {
        FileReader fileReader = new FileReader("urls_nytimes.csv");
        BufferedReader csvReader = new BufferedReader(fileReader);
    
        List<String> status = new ArrayList<>();
        
        System.out.print(csvReader);

        String line;
        while ((line = csvReader.readLine()) != null) {
            
            String[] tokens = line.split(",");
            String isOK = tokens[1].trim();
            status.add(isOK);
        }
       
        csvReader.close();
    
        return status;
    }  
    
    
    public void report() throws Exception {

        File newFile = new File("CrawlReport_nytimes.txt");
        newFile.delete();
        newFile.createNewFile();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(newFile, true));
        bw.write("Name: Meerza Ahmed\n");
        bw.write("USC ID: 8203-5365-10\n");
        bw.write("News site crawled: https://www.nytimes.com/\n");
        bw.write("Number of threads: " + "16" + "\n\n");

        int code200 = 0;
        for (String item : readfetch()) {
            if ("200".equals(item)) {
                code200++;
            }
        }
        
        bw.write("Fetch Statistics\n");
        bw.write("================\n");
        bw.write("# fetches attempted: " + readfetch().size() + "\n");
        bw.write("# fetches succeeded: " + code200 + "\n");
        bw.write("# fetches failed or aborted: " + (readfetch().size() - code200) + "\n\n");

        FileReader fileReader = new FileReader("visit_nytimes.csv");
        BufferedReader csvReader = new BufferedReader(fileReader);

        String line;
        List<String> nytimesURList = new ArrayList<>();
        List<String> downloadSizeList = new ArrayList<>();
        List<String> outgoingLinksList = new ArrayList<>();
        List<String> contentTypeList = new ArrayList<>();

        while ((line = csvReader.readLine()) != null) {
            if (line.isEmpty()) {
                continue; // Skip empty lines
            }
            
            String[] tokens = line.split(",");
                String nytimesURL = tokens[0].trim();
                String downloadSize = tokens[1].trim();
                String outgoinglinks = tokens[2].trim();
                String contentType = tokens[3].trim();

                nytimesURList.add(nytimesURL);
                downloadSizeList.add(downloadSize);
                outgoingLinksList.add(outgoinglinks);
                contentTypeList.add(contentType);

        }

        csvReader.close();

        int isOK = 0;
        for (String item : readurls()) {
            if ("OK".equals(item)) {
                isOK++;
            }
        }

        int isNotOK = 0;
        for (String item : readurls()) {
            if ("N_OK".equals(item)) {
                isNotOK++;
            }
        }

        bw.write("Outgoing URLs:\n");
        bw.write("==============\n");
       // bw.write("Total URLs extracted: " + outgoingLinksList.size() + "\n");
        bw.write("# unique URLs extracted: " + readurls().size() + "\n");
        bw.write("# unique URLs within News Site: " + isOK + "\n");
        bw.write("# unique URLs outside News Site: " + isNotOK + "\n\n");

        List<String> codes = readfetch();
        bw.write("Status Codes:\n");
        bw.write("=============\n");
        int _200 = 0, _301 = 0, _302 = 0,  _303 = 0, _304 = 0,  _305 = 0, _306 = 0,
        _307 = 0, _308 = 0, _400 = 0, _401 = 0, _403 = 0, _404 = 0, _405 = 0, _406 = 0,
        _408 = 0, _409 = 0, _410 = 0,_500 = 0, _501 = 0, _502 = 0, _503 = 0, _504 = 0;
        
        for (String item : codes) {
            if ("200".equals(item)) {
                _200++;
            }
            if ("301".equals(item)) {
                _301++;
            }
            if ("302".equals(item)) {
                _302++;
            }
            if ("303".equals(item)) {
                _303++;
            }
            if ("304".equals(item)) {
                _304++;
            }
            if ("305".equals(item)) {
                _305++;
            }
            if ("306".equals(item)) {
                _306++;
            }
            if ("307".equals(item)) {
                _307++;
            }
            if ("308".equals(item)) {
                _308++;
            }
            if ("400".equals(item)) {
                _400++;
            }
            if ("401".equals(item)) {
                _401++;
            }
            if ("403".equals(item)) {
                _403++;
            }
            if ("404".equals(item)) {
                _404++;
            }
            if ("405".equals(item)) {
                _405++;
            }
            if ("406".equals(item)) {
                _406++;
            }
            if ("408".equals(item)) {
                _408++;
            }
            if ("409".equals(item)) {
                _409++;
            }
            if ("410".equals(item)) {
                _410++;
            }
            if ("500".equals(item)) {
                _500++;
            }
            if ("501".equals(item)) {
                _501++;
            }
            if ("502".equals(item)) {
                _502++;
            }
            if ("503".equals(item)) {
                _503++;
            }
            if ("504".equals(item)) {
                _504++;
            }
        }
        
        if (_200 > 0) {
            bw.write("200 OK: " + _200 + "\n");
        }
        if (_301 > 0) {
            bw.write("301 Moved Permanently: " + _301 + "\n");
        }
        if (_302 > 0) {
            bw.write("302 Moved Temporarily: " + _200 + "\n");
        }
        if (_303 > 0) {
            bw.write("303: See Other Resource: " + _302 + "\n");
        }
        if (_304 > 0) {
            bw.write("304: Not Modified: " + _304 + "\n");
        }
        if (_305 > 0) {
            bw.write("305: Use Proxy: " + _305 + "\n");
        }
        if (_306 > 0) {
            bw.write("306: Switch Proxy: " + _306 + "\n");
        }
        if (_307 > 0) {
            bw.write("307: Temporary Redirect: " + _307 + "\n");
        }
        if (_308 > 0) {
            bw.write("308: Permanently Redirect: " + _308 + "\n");
        }
        if (_400 > 0) {
            bw.write("400 Bad Request Response: " +_400 + "\n");
        }
        if (_401 > 0) {
            bw.write("401 Unauthorized Request: " + _401 + "\n");
        }
        if (_403 > 0) {
            bw.write("403 Access to Resource Forbidden: " + _403+ "\n");
        }
        if (_404 > 0) {
            bw.write("404 Resource Not Found: " + _404 + "\n");
        }
        if (_405 > 0) {
            bw.write("405 Method Not Allowed: " + _405+ "\n");
        }
        if (_406 > 0) {
            bw.write("406 Not Acceptable: " + _406 + "\n");
        }
        if (_408 > 0) {
            bw.write("408 Request Timeout: " + _408 + "\n");
        }
        if (_409 > 0) {
            bw.write("409 Conflict: " +_409 + "\n");
        }
        if (_410 > 0) {
            bw.write("410 Gone: " + _410 + "\n");
        }
        if (_500 > 0) {
            bw.write("500 Internal Server Error: " + _500 + "\n");
        }
        if (_501 > 0) {
            bw.write("501 Method Not Supported: " + _501 + "\n");
        }
        if (_502 > 0) {
            bw.write("502 Gateway Error: " + _502 + "\n");
        }
        if (_503 > 0) {
            bw.write("503 Service Unavailable: " +_503 + "\n");
        }
        if (_504 > 0) {
            bw.write("504 Gateway Timeout: " +_504 + "\n");
        }
        bw.write("\n");
         
        int oKB = 0, tKB = 0, hKB = 0, oMB = 0, mMB = 0;
        for (String tt : downloadSizeList) {
            try {
                int byt = Integer.parseInt(tt);
                int KB = 1024;
                if (byt < KB) {
                    oKB++;
                } else if (byt < 10 * KB) {
                    tKB++;
                } else if (byt < 100 * KB) {
                    hKB++;
                } else if (byt < KB * KB) {
                    oMB++;
                } else {
                    mMB++;
                }
            } catch (NumberFormatException e) {
                // Handle invalid string (e.g., log an error or skip)
                System.err.println("Invalid string: " + tt);
            }
        }

        bw.write("File Sizes:\n");
        bw.write("===========\n");
        bw.write("< 1KB: " + oKB + "\n");
        bw.write("1KB ~ <10KB: " + tKB + "\n");
        bw.write("10KB ~ <100KB: " + hKB + "\n");
        bw.write("100KB ~ <1MB: " + oMB + "\n");
        bw.write(">= 1MB: " + mMB + "\n\n");

        // Content type
        bw.write("Content Types:\n");
        bw.write("==============\n");
      
       
        bw.close();
        
    }
}