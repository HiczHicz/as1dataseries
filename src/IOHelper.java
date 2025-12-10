import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class IOHelper {
    //odczytywanie plików Task III.1
    static FileContent readFile(String filename, Logger logger) throws IOException {
        Sensor dummySensor=new Sensor("<N/A>");

        String path = "inputData/";
        BufferedReader data = new BufferedReader(new FileReader(path + filename));
        String line;
        ArrayList<Sensor> sensors = new ArrayList<>();

        int noOfInvalidRecords = 0;
        while ((line = data.readLine()) != null) {
            try {
                String[] parts = line.split(" ");
                if (parts.length ==1){
                    dummySensor.addReadout(new Readout(Double.parseDouble(line)));
                }
                else if (parts.length==2){
                    double value = Double.parseDouble(parts[0]);
                    String uuid = parts[1];
                    dummySensor.addReadout(new ReadoutWithUuid(value, uuid));
                }
                else{
                    double value = Double.parseDouble(parts[0]);
                    String uuid = parts[1];
                    String sensorName = parts[2];
                    addReadoutToSensor(sensors, sensorName,new ReadoutWithUuid(value, uuid));
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                noOfInvalidRecords += 1;
                logger.log(Logger.Level.ERROR, "Faulty record in [" + filename+ "]: " + line);
            }
        }
        data.close();
        return new FileContent(sensors, noOfInvalidRecords, filename);
    }
    private static void addReadoutToSensor(ArrayList<Sensor> sensorsList, String sensorName, Readout readout){
        boolean isInSensorList = false;
        for(Sensor sns : sensorsList){
            if(Objects.equals(sns.getName(), sensorName)) {
                sns.addReadout(readout);
                isInSensorList = true;
                break;
            }
        }
        if(!isInSensorList){
            sensorsList.add(new Sensor(sensorName));
            Sensor newSensor = sensorsList.get(sensorsList.size()-1);
            newSensor.addReadout(readout);
        }
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
}
