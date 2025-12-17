import java.io.IOException;


public class Main {
    static void processOneFile(String filename, String logFilename, String outFilename, String title) throws IOException{
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        System.out.println(IOHelper.getOutputInfo(fContent, title, logger, outFilename));
        logger.flush();
    }

    public static void main(String[] args) throws IOException {

        //odczytywanie pliku a
        processOneFile("data_a1.txt", "data_a.log", "out_a.txt", "Task I.1");

        //odczytywanie pliku b
        processOneFile("data_b1.txt", "data_b.log", "out_b.txt","Task I.2");

        //odczytywanie pliku c
        processOneFile("data_c1.txt", "data_c.log", "out_c.txt","Task II.1");

        //odczytywanie pliku d
        processOneFile("data_d1.txt", "data_d.log", "out_d.txt","Task III");

        //odcyztywanie pliku e
        processOneFile("data_e1.txt", "data_e.log", "out_e.txt","Task III.4");
    }
}

