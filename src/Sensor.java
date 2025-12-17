import java.util.ArrayList;
import java.util.Collections;

public class Sensor implements Comparable<Sensor> {
    private String name;
    private ArrayList<Readout> data=new ArrayList<>();

    public Sensor(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void addReadout(Readout readout){
        data.add(readout);
    }
    // size
    public int getLengthOfData(){
        return data.size();
    }
    //wartość maksymalna
    public Readout getMax() {
        Collections.sort(data);
        int size=data.size()-1;
        return data.get(size);
    }
    //wartość minimalna
    public Readout getMin() {
        Collections.sort(data);
        return data.get(0);
    }
    //średnia
    public double getMean() {
        double mean = 0;
        for (int i = 0; i < data.size(); i++) {
            mean += data.get(i).getValue();
        }
        mean = mean / data.size();
        return mean;
    }
    //mediana
    public MedianWrapper getMedian() {
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
    public int noOfCentralElements(Logger logger) {
        double epsilon = (getMax().getValue() - getMin().getValue()) / 100;
        int numberOfCentralEl = 0;
        for (int i = 0; i < data.size(); i++) {
            if ((getMean() - data.get(i).getValue() < epsilon) && (data.get(i).getValue() - getMean() < epsilon)) {
                numberOfCentralEl++;
                logger.log(Logger.Level.CENTRAL_ELEM, "Central element for sensor [" + getName() + "]: " + data.get(i));
            }
        }
        //for (int i=0; i<numberOfCentralEl; i++){
        //    logger.log(Logger.Level.CENTRAL_ELEM, "Central element for sensor [" + getName() + "]: " + data.get(i));
        //}
        return numberOfCentralEl;
    }

    @Override
    public int compareTo(Sensor sns) {
            return this.name.compareTo(sns.name);
    }
}
