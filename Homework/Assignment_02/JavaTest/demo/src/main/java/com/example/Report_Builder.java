package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


public class Report_Builder {

 
    public void reportFiles(StringBuilder output, String s) throws IOException {
        PrintWriter r = new PrintWriter(s, StandardCharsets.UTF_8);
        r.println(output.toString().trim());
        r.flush();
        r.close();
    }

}

