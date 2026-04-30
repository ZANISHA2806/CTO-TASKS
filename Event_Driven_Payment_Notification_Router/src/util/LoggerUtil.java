package util;

import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {

    private static final Logger logger =
            Logger.getLogger(
               LoggerUtil.class.getName()
            );

    static {

        try {

            logger.setUseParentHandlers(false);

            FileHandler fileHandler =
                new FileHandler(
                    "Notification.log",
                    true
                );

            fileHandler.setFormatter(
                new SimpleFormatter()
            );

            fileHandler.setLevel(
                Level.INFO
            );

            logger.addHandler(
                fileHandler
            );


            ConsoleHandler consoleHandler =
                new ConsoleHandler();

            consoleHandler.setFormatter(
                new SimpleFormatter()
            );

            consoleHandler.setLevel(
                Level.INFO
            );

            logger.addHandler(
                consoleHandler
            );

            logger.setLevel(
                Level.INFO
            );

        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    private LoggerUtil(){}


    public static Logger getLogger() {
        return logger;
    }
}