public class LoggerStdOut extends Logger {

    private final static String LOGS_PATH="logs/";


    @Override
    public void flush() {
        for (String log : logs) {
            System.out.println(log);
        }
        clear();
    }
}