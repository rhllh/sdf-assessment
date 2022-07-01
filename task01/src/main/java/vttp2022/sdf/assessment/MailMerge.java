package vttp2022.sdf.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MailMerge {
    private int lineNo;
    private List<String> columns;
    private List<List<String>> values;
    private File templateFile;
    private int numOfCols;

    private FileReader fr;
    private BufferedReader br;
    private String line;

    public MailMerge(int lineNo, List<String> columns, List<List<String>> values, File file) {
        this.lineNo = lineNo;       // for looping through csv
        this.columns = columns;
        this.values = values;
        this.templateFile = file;
        this.numOfCols = this.columns.size();
    }

    public void merge() {
        try {
            fr = new FileReader(templateFile);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("__")) {
                    // no hard coding 
                    
                    for (int i = 0; i < line.split("__").length; i++) {
                        // there could be more than one __ in the same line
                        String colName = line.split("__")[i+1].toLowerCase();
                        System.out.println(colName);
                        line = line.replace("__", values.get(lineNo).get(columns.indexOf(colName)));
                        i++;
                    }
                    
                }
                System.out.println(line);
            }
            System.out.println("-----------------------------------");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
