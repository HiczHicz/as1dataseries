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

    //wartość maksymalna
    static Readout getMax(ArrayList<Readout> data) {
        Collections.sort(data);
        return data.get(data.size() - 1);
    }

    //wartość minimalna
    static Readout getMin(ArrayList<Readout> data) {
        Collections.sort(data);
        return data.get(0);
    }

    //średnia
    static double getMean(ArrayList<Readout> data) {
        double mean = 0;
        for (int i = 0; i < data.size(); i++) {
            mean += data.get(i).getValue();
        }
        mean = mean / data.size();
        return mean;
    }

    //mediana
    static MedianWrapper getMedian(ArrayList<Readout> data) {
        Collections.sort(data);
        int n = data.size();
        if (n % 2 == 0) {
            Readout elem1 = data.get(n / 2 - 1);
            Readout elem2 = data.get(n / 2);
            return new MedianWrapper(elem1, elem2);
        } else {
            Readout elem = data.get(n / 2);
            return new MedianWrapper(elem);
        }
    }

    //elementy centralne
    static int noOfCentralElements(ArrayList<Readout> data) {
        double epsilon = (getMax(data).getValue() - getMin(data).getValue()) / 100;
        int numberOfCentralEl = 0;
        for (int i = 0; i < data.size(); i++) {
            if ((getMean(data) - data.get(i).getValue() < epsilon) && (data.get(i).getValue() - getMean(data) < epsilon)) {
                numberOfCentralEl++;
            }
        }
        return numberOfCentralEl;
    }

    //string końcowy
    static String getOutputInfo(FileContent fContent, String title) {
        String str = "";
        String separator = "\n------------------------------\n";

        int noOfInvalidRecords = fContent.getNoOfInvalidRecords();
        for(Sensor sensor: fContent.getSensors()){
            ArrayList<Readout> data = sensor.getData();
            String filename = fContent.getFileName();

            str = str + title + "\nMartyna Pieczka, 297955" + separator + "Data filename: " + filename +
                    "\nLength of the series: " + data.size() + "\nMax value: " + getMax(data).toString() +
                    "\nMin value: " + getMin(data).toString() + String.format("\nMean value: %.3f", getMean(data))
                    + "\nMedian: " + getMedian(data) + "\nNumber of central elements: " + noOfCentralElements(data);

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

