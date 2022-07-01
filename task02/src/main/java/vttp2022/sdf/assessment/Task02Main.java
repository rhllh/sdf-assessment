package vttp2022.sdf.assessment;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Task 02
 *
 */
public class Task02Main 
{
    public static void main( String[] args )
    {
        // connect to server at port 80
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;


        try {
            socket = new Socket("68.183.239.26", 80);

            os = socket.getOutputStream();
            is = socket.getInputStream();
            oos = new ObjectOutputStream(os);
            ois = new ObjectInputStream(is);

            System.out.println("Connected successfully");

            // read request from server
            String request = ois.readUTF();
            //System.out.println(request);

            String requestID = request.split(" ")[0];
            String[] listOfNumbers = request.split(" ")[1].split(",");

            //System.out.println("ID: " + requestID);
            //System.out.println("List of numbers: " + Arrays.toString(listOfNumbers));
            
            float average = getAverage(listOfNumbers);
            //System.out.println("Average: " + average);

            String name = "Siti Rahillah Binte Yusri";
            String email = "rahillah.yusri@gmail.com";

            writeToServer((Object) requestID, oos);
            writeToServer((Object) name, oos);
            writeToServer((Object) email, oos);
            writeToServer((Object) average, oos);
            oos.flush();

            if (readBooleanFromServer(ois)) {
                System.out.println("SUCCESS");
            } else {
                String errorMsg = ois.readUTF();
                System.out.println(errorMsg);
                System.out.println("FAILED");
            }
        
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {}
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {}
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {}
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {}
            }
            System.out.println("Disconnected successfully");
        }
    
    }

    public static float getAverage(String[] listOfNumbers) {
        int sum = 0;

        for (int i = 0; i < listOfNumbers.length; i++) {
            sum += Integer.parseInt(listOfNumbers[i]);
        }

        float average = (float) sum / listOfNumbers.length;

        return average;
    }

    public static void writeToServer(Object obj, ObjectOutputStream oos) {
        try {
            if (obj instanceof String) {
                oos.writeUTF((String) obj);
            } else if (obj instanceof Float) {
                oos.writeFloat((float) obj);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }

    public static boolean readBooleanFromServer(ObjectInputStream ois) {
        boolean isTrueOrFalse = false;
        try {
            isTrueOrFalse = ois.readBoolean();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return isTrueOrFalse;
    }
}
