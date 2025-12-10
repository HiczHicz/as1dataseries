import java.io.IOException;
import java.util.ArrayList;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {

        //odczytywanie pliku a
        processOneFile("data_a1.txt", "data_a.log", "Task I.1");

        //odczytywanie pliku b
        processOneFile("data_b1.txt", "data_b.log", "Task I.2");

        //odczytywanie pliku c
        processOneFile("data_c1.txt", "data_c.log", "Task II.1");
    }

    //string końcowy
    static String getOutputInfo(FileContent fContent, String title) {
        String str = "";
        String separator = "\n------------------------------\n";

        int noOfInvalidRecords = fContent.getNoOfInvalidRecords();
        for(Sensor sensor: fContent.getSensors()){
            String filename = fContent.getFileName();

            str = str + title + "\nMartyna Pieczka, 297955" + separator + "Data filename: " + filename +
                    "\nLength of the series: " + sensor.getLengthOfData() + "\nMax value: " + sensor.getMax() +
                    "\nMin value: " + sensor.getMin().toString() + String.format("\nMean value: %.3f", sensor.getMean())
                    + "\nMedian: " + sensor.getMedian() + "\nNumber of central elements: " + sensor.noOfCentralElements();

        }
        str = str + "\nNumber of invalid records: " + noOfInvalidRecords + separator;
        return str;
    }


    static void processOneFile(String filename, String logFilename, String title) throws IOException{
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        System.out.println(getOutputInfo(fContent, title));
        logger.flush();
    }

}

