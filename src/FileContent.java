import java.util.ArrayList;
public class FileContent {
    private ArrayList<Readout> data;
    private int noOfInvalidRecords;
    String fileName;
    public FileContent(ArrayList<Readout> data, int noOfInvalidRecords,
                       String fileName) {
        this.data = data;
        this.noOfInvalidRecords = noOfInvalidRecords;
        this.fileName = fileName;
    }
    public ArrayList<Readout> getData() {
        return data;
    }
    public int getNoOfInvalidRecords() {
        return noOfInvalidRecords;
    }
    public String getFileName() {
        return fileName;
    }
}
