package vttp2022.sdf.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MailMerge {
    private int lineNo;
    private List<List<String>> values;
    private File templateFile;

    private FileReader fr;
    private BufferedReader br;
    private String line;

    public MailMerge(int lineNo, List<List<String>> values, File file) {
        this.lineNo = lineNo;
        this.values = values;
        this.templateFile = file;
    }

    public void merge() {
        try {
            fr = new FileReader(templateFile);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("__")) {
                    if (line.split("__")[1].toLowerCase().equals("address")) {
                        String fullAddress = (values.get(lineNo).get(2)).replace("\\n", System.lineSeparator());
                        line = line.replace("__address__", fullAddress);
                    } else if (line.split("__")[1].toLowerCase().equals("first_name")) {
                        line = line.replace("__first_name__", values.get(lineNo).get(0));
                    } else if (line.split("__")[1].toLowerCase().equals("years")) {
                        line = line.replace("__years__", values.get(lineNo).get(3));
                    } else if (line.split("__")[1].toLowerCase().equals("last_name")) {
                        line = line.replace("__last_name__", values.get(lineNo).get(1));
                    }
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
