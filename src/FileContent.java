import java.util.ArrayList;
public class FileContent {
    private ArrayList<Sensor> sensors;
    private int noOfInvalidRecords;
    String fileName;
    public FileContent(ArrayList<Sensor> sensors, int noOfInvalidRecords,
                       String fileName) {
        this.sensors = sensors;
        this.noOfInvalidRecords = noOfInvalidRecords;
        this.fileName = fileName;
    }
    public ArrayList<Sensor> getSensors() {
        return sensors;
    }
    public int getNoOfInvalidRecords() {
        return noOfInvalidRecords;
    }
    public String getFileName() {
        return fileName;
    }
}
