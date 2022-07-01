package vttp2022.sdf.assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileIO {
    private FileReader fr;
    private BufferedReader br;
    private String line;
    private String[] input;

    private List<String> columns;
    private List<List<String>> values = new ArrayList<>();

    public FileIO() {
        
    }

    public int getNumberOfLines(File file) {
        int count = 0;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                count++;
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public List<String> getColumns(File file) {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                input = line.split(",");
                if (line.toLowerCase().startsWith("first_name")) {
                    columns = Arrays.asList(input);
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return columns;
    }

    public List<List<String>> getValues(File file) {
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            line = null;
            while ((line = br.readLine()) != null) {
                input = line.split(",");
                if (!line.toLowerCase().startsWith("first_name")) {
                    values.add(Arrays.asList(input));
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return values;
    }
}
