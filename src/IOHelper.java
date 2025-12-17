import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class IOHelper {
    //odczytywanie plików Task III.1
    static FileContent readFile(String filename, Logger logger) throws IOException {

        String path = "inputData/";
        BufferedReader data = new BufferedReader(new FileReader(path + filename));
        String line;
        ArrayList<Sensor> sensors = new ArrayList<>();
        logger.log(Logger.Level.INFO, "Started reading file [" + filename+ "] ");

        int noOfInvalidRecords = 0;
        while ((line = data.readLine()) != null) {
            try {
                String[] parts = line.split(" ");
                if (parts.length ==1){
                    addReadoutToSensor(sensors, "<N/A>", new Readout(Double.parseDouble(line)));
                }
                else if (parts.length==2){
                    double value = Double.parseDouble(parts[0]);
                    String uuid = parts[1].replace("id:","");
                    addReadoutToSensor(sensors, "<N/A>", new ReadoutWithUuid(value, uuid));
                }
                else{
                    double value = Double.parseDouble(parts[0]);
                    String uuid = parts[1].replace("id:","");
                    String sensorName = parts[2];
                    addReadoutToSensor(sensors, sensorName,new ReadoutWithUuid(value, uuid));
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                noOfInvalidRecords += 1;
                logger.log(Logger.Level.ERROR, "Faulty record in [" + filename+ "]: " + line);
            }
        }
        data.close();
        logger.log(Logger.Level.INFO, "Finished reading file [" + filename+ "] ");
        sensors.sort(null);
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

    private final static String OUT_PATH="outputData/"; //Default location of out
    static String getOutputInfo(FileContent fContent, String title, Logger logger, String outFilename) {
        String separatorLong = "\n------------------------------\n";
        String separatorShort="\n-------";

        int noOfInvalidRecords = fContent.getNoOfInvalidRecords();

        String filename = fContent.getFileName();
        String str = title + "\nMartyna Pieczka, 297955" + separatorLong + "Data filename: "+filename;

        for(Sensor sensor: fContent.getSensors()){

            str =    str + separatorShort + "\nSensor name: "+ sensor.getName() +
                    "\nLength of the series: " + sensor.getLengthOfData() + "\nMax value: " + sensor.getMax() +
                    "\nMin value: " + sensor.getMin().toString() + String.format("\nMean value: %.3f", sensor.getMean())
                    + "\nMedian: " + sensor.getMedian() + "\nNumber of central elements: " + sensor.noOfCentralElements(logger);
            logger.log(Logger.Level.MAX_ELEM, "Max. element for sensor [" + sensor.getName()+ "]: " + sensor.getMax());
            logger.log(Logger.Level.MIN_ELEM, "Min. element for sensor [" + sensor.getName()+ "]: " + sensor.getMin().toString());
        }

        str = str + "\nNumber of invalid records: " + noOfInvalidRecords + separatorLong;

        try  (PrintWriter out = new PrintWriter(OUT_PATH+outFilename)){
            out.println(str);
        } catch (FileNotFoundException e) {
            System.out.println("Can’t flush the output. Please check the filename: "
                    + filename);
        }
        return str;

    }
}