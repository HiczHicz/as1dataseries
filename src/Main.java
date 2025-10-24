import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String oddzielenie= new String("\n------------------------------\n");
        File file=new File("data_a1.txt");
        //odczytywanie pliku
        BufferedReader data= new BufferedReader(new FileReader("/Users/tynka/IdeaProjects/as1dataseries/inputData/data_a1.txt"));
        String line;
        ArrayList<Double> liczby=new ArrayList<>();
        while ((line = data.readLine()) != null){
            liczby.add(Double.valueOf(line));
        }

        System.out.printf("Task I.1 \nMartyna Pieczka, 297955" + oddzielenie);
        System.out.println("Data filename: "+file.getName());
        System.out.println("Length of the series: "+liczby.size());
        System.out.format("Max value: %.3f\n", getMax(liczby));
        System.out.format("Min value: %.3f\n", getMin(liczby));
        System.out.format("Mean value: %.3f\n", getMean(liczby));
        System.out.format("Median value: %.3f\n", getMedian(liczby));
        System.out.println("Number of central elements: "+ noOfCentralElements(liczby) + oddzielenie);
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
}