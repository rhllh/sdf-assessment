package vttp2022.sdf.assessment;

import java.io.File;
import java.util.List;

/**
 * Task 01
 *
 */
public class Task01Main 
{
    public static File csvFile;
    public static File templateFile;
    public static void main( String[] args )
    {
        if (args.length != 2) {
            System.out.println("Please enter <CSV file> <template file> as arguments");
            System.exit(1);
        }

        csvFile = new File(args[0]);
        templateFile = new File(args[1]);

        // check if files exist
        if (!csvFile.exists()) {
            System.out.println("CSV file does not exist");
            System.exit(1);
        } else if (!csvFile.canRead()) {
            System.out.println("CSV file is unreadable");
            System.exit(1);
        } else if (!templateFile.exists()) {
            System.out.println("Template file does not exist");
            System.exit(1);
        } else if (!templateFile.canRead()) {
            System.out.println("Template file cannot be read");
            System.exit(1);
        }

        // read files
        FileIO fio = new FileIO();

        List<String> columns = fio.getColumns(csvFile);
        List<List<String>> values = fio.getValues(csvFile);

        int numberOfMails = fio.getNumberOfLines(csvFile);

        for (int i = 1; i < numberOfMails; i++) {
            MailMerge mm = new MailMerge(i, columns, values, templateFile);
            mm.merge();
        }
        
    }
}
