import java.io.IOException;


public class Main {
    static void processOneFile(String filename, String logFilename, String title) throws IOException{
        Logger logger = new LoggerFile(logFilename);
        FileContent fContent = IOHelper.readFile(filename, logger);
        System.out.println(IOHelper.getOutputInfo(fContent, title));
        logger.flush();
    }

    public static void main(String[] args) throws IOException {

        //odczytywanie pliku a
        processOneFile("data_a1.txt", "data_a.log", "Task I.1");

        //odczytywanie pliku b
        processOneFile("data_b1.txt", "data_b.log", "Task I.2");

        //odczytywanie pliku c
        processOneFile("data_c1.txt", "data_c.log", "Task II.1");

        //odczytywanie pliku d
        processOneFile("data_d1.txt", "data_d.log", "Task III");

        //odcyztywanie pliku e
        processOneFile("data_e1.txt", "data_e.log", "Task III.4");
    }
}

