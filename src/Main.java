import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path="/Users/tynka/IdeaProjects/as1dataseries/inputData/";
        //odczytywanie pliku a
        BufferedReader data= new BufferedReader(new FileReader(path+"data_a1.txt"));
        String line;
        ArrayList<Readout> liczby=new ArrayList<>();
        while ((line = data.readLine()) != null){
            liczby.add(new Readout(Double.parseDouble(line)));
        }
        System.out.println(getOutputInfo(liczby, 0, "Task I.1", "data_a1.txt"));
        data.close();

        //odczytywanie pliku b
        BufferedReader data2= new BufferedReader(new FileReader(path+"data_b1.txt"));
        String line2;
        ArrayList<Readout> liczby2=new ArrayList<>();
        int noOfInvalidRecords=0;
        while ((line2 = data2.readLine()) != null){
            try {
                liczby2.add(new Readout(Double.parseDouble(line2)));
            }
            catch(NumberFormatException e){
                noOfInvalidRecords+=1;
            }
        }
        System.out.println(getOutputInfo(liczby2, noOfInvalidRecords, "Task I.2", "data_b1.txt"));
        data2.close();

        //odczytywanie pliku c
        BufferedReader data3= new BufferedReader(new FileReader(path+"data_c1.txt"));
        String line3;
        ArrayList<Readout> liczby3=new ArrayList<>();
        int noOfInvalidRecords3=0;
        while ((line3 = data3.readLine()) != null){
            try {
                //POLECENIE 8
                String[] parts = line3.split(" id:");
                double value = Double.parseDouble(parts[0]);
                String uuid = parts[1];
                liczby3.add(new ReadoutWithUuid(value, uuid));
            }
            catch(NumberFormatException | ArrayIndexOutOfBoundsException e){
                noOfInvalidRecords3+=1;
            }
        }

        System.out.println(getOutputInfo(liczby3, noOfInvalidRecords3, "Task II.1", "data_c1.txt"));
        data3.close();
    }
    //wartość maksymalna
    static Readout getMax(ArrayList<Readout> data){
        Collections.sort(data);
        return data.get(data.size()-1);
    }
    //wartość minimalna
    static Readout getMin(ArrayList<Readout> data){
        Collections.sort(data);
        return data.get(0);
    }
    //średnia
    static double getMean(ArrayList<Readout> data){
        double mean=0;
        for(int i=0; i<data.size(); i++){
            mean+=data.get(i).getValue();
        }
        mean=mean/data.size();
        return mean;
    }
    //mediana - dla wszystkich przypadków (pamiętając, ze indeksy ArrayList są od 0
    static double getMedian(ArrayList<Readout> data){
        Collections.sort(data);
        int n = data.size();
        if(n%2==0){
            return (data.get(n/2-1).getValue()+(data.get(n/2)).getValue())/2;
        }
        else {
            return data.get(n/2).getValue();
        }
    }
    //elementy centralne
    static int noOfCentralElements(ArrayList<Readout> data){
        double epsilon=(getMax(data).getValue()-getMin(data).getValue())/100;
        int numberOfCentralEl=0;
        for(int i=0; i<data.size(); i++){
            if((getMean(data)-data.get(i).getValue()<epsilon)&&(data.get(i).getValue()-getMean(data)<epsilon)){
                numberOfCentralEl++;
            }
        }
        return numberOfCentralEl;
    }
    //string końcowy
    static String getOutputInfo(ArrayList<Readout> data, int noOfInvalidRecords, String title, String filename){
        String str="";
        String separator= "\n------------------------------\n";
        str = str + title + "\nMartyna Pieczka, 297955" + separator + "Data filename: " + filename + "\nLength of the series: " + data.size() + "\nMax value: " +getMax(data).toString() + "\nMin value: " + getMin(data).toString() + String.format("\nMean value: %.3f", getMean(data)) + String.format("\nMedian %.3f", getMedian(data)) + "\nNumber of central elements: " + noOfCentralElements(data);
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