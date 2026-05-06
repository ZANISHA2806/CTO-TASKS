package util;

import java.util.logging.*;
import java.io.IOException;

public class DividendLogger {

    private static final Logger log = Logger.getLogger("DividendLogger");

    static {
        try {
            log.setUseParentHandlers(false);

            FileHandler fileHandler = new FileHandler("shares.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.ALL);

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter());
            consoleHandler.setLevel(Level.ALL);

            log.addHandler(fileHandler);
            log.addHandler(consoleHandler);

            log.setLevel(Level.ALL);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logInfo(String message) {
        log.info(message);
    }

    public static void logError(String message) {
        log.severe(message);
    }

    public static void logSuccess(String message) {
        log.info(message);
    }

    public static void logFailure(String shareholderId, Exception e) {
        log.severe(shareholderId + ": FAILED — " + e.getMessage());
    }

    public static void logHalt(Exception e) {
        log.severe("DIVIDEND HALT — " + e.getMessage());
    }
}