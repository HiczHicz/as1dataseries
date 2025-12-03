import java.util.ArrayList;

public class Sensor {
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
    public ArrayList<Readout> getData(){
        return data;
    }
 }
