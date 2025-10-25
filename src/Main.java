import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //odczytywanie pliku a
        BufferedReader data= new BufferedReader(new FileReader("/Users/tynka/IdeaProjects/as1dataseries/inputData/data_a1.txt"));
        String line;
        ArrayList<Double> liczby=new ArrayList<>();
        while ((line = data.readLine()) != null){
            liczby.add(Double.valueOf(line));
        }

        System.out.println(getOutputInfo(liczby, 0, "Task I.1 \nMartyna Pieczka, 297955", "data_a1.txt"));

        //odczytywanie pliku b
        BufferedReader data2= new BufferedReader(new FileReader("/Users/tynka/IdeaProjects/as1dataseries/inputData/data_b1.txt"));
        String line2;
        ArrayList<Double> liczby2=new ArrayList<>();
        int noOfInvalidRecords=0;
        while ((line2 = data2.readLine()) != null){
            try {
                liczby2.add(Double.valueOf(line2));
            }
            catch(NumberFormatException e){
                noOfInvalidRecords+=1;
            }
        }
        System.out.println(getOutputInfo(liczby2, noOfInvalidRecords, "Task I.2 \nMartyna Pieczka, 297955", "data_b1.txt"));
    }

    static double getMax(ArrayList<Double> data){
        Collections.sort(data);
        return data.get(data.size()-1);
    }
    static double getMin(ArrayList<Double> data){
        Collections.sort(data);
        return data.get(0);
    }
    static double getMean(ArrayList<Double> data){
        double mean=0;
        for(int i=0; i<data.size(); i++){
            mean+=data.get(i);
        }
        mean=mean/data.size();
        return mean;
    }
    static double getMedian(ArrayList<Double> data){
        Collections.sort(data);
        int n = data.size();
        if(n%2==0){
            return (data.get(n/2-1)+(data.get(n/2)))/2;
        }
        else {
            return data.get(n/2);
        }
    }
    static int noOfCentralElements(ArrayList<Double> data){
        double epsilon=(getMax(data)-getMin(data))/100;
        int numberOfCentralEl=0;
        for(int i=0; i<data.size(); i++){
            if((getMean(data)-data.get(i)<epsilon)&&(data.get(i)-getMean(data)<epsilon)){
                numberOfCentralEl++;
            }
        }
        return numberOfCentralEl;
    }
    static String getOutputInfo(ArrayList<Double> data, int noOfInvalidRecords, String title, String filename){
        String str="";
        String separator= "\n------------------------------\n";
        str = str + title + separator + "Data filename: " + filename + "\nLength of the series: " + data.size() + String.format("\nMax value: %.3f", getMax(data)) +String.format("\nMin value: %.3f", getMin(data)) + String.format("\nMean value: %.3f", getMean(data)) + String.format("\nMedian %.3f", getMedian(data)) + "\nNumber of central elements: " + noOfCentralElements(data);
        if(noOfInvalidRecords==0) {
            str += separator;
            return str;
        }
        else{
            str=str+"\nNumber of invalid records: " + noOfInvalidRecords + separator;
            return str;
        }
    }
}