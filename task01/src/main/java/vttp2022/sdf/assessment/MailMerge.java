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

    private FileReader fr;
    private BufferedReader br;
    private String line;

    public MailMerge(int lineNo, List<String> columns, List<List<String>> values, File file) {
        this.lineNo = lineNo;       // for looping through csv
        this.columns = columns;
        this.values = values;
        this.templateFile = file;
    }

    public void merge() {
        try {
            fr = new FileReader(templateFile);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                // in every line,
                // check the substrings that begin and end with __ against COLUMN
                // replace these substrings including the __ with the corresponding VALUE
                // check if there are more than one to replace
                // reassign to line

                if (line.contains("__")) {          // check if there is something to replace
                    String[] terms = line.split("__");
                    for (int i = 0; i < terms.length; i++) {
                        if (columns.contains(terms[i])) {
                            line = line.replace("__" + terms[i] + "__", values.get(lineNo-1).get(columns.indexOf(terms[i])));
                            if (line.contains("\\n"))
                                line = line.replace("\\n","\r\n");
                        }
                        
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
