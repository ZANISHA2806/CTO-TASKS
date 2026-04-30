package config;

import java.io.IOException;
import java.util.logging.*;

public class LoggingConfig {

    private static final Logger log =
            Logger.getLogger(LoggingConfig.class.getName());

    static {

        try {

            log.setUseParentHandlers(false);

            FileHandler fileHandler =
                    new FileHandler(
                            "portfolio.log",
                            true
                    );

            fileHandler.setFormatter(
                    new SimpleFormatter()
            );

            fileHandler.setLevel(
                    Level.INFO
            );

            ConsoleHandler consoleHandler =
                    new ConsoleHandler();

            consoleHandler.setFormatter(
                    new SimpleFormatter()
            );

            consoleHandler.setLevel(
                    Level.INFO
            );

            log.addHandler(fileHandler);
            log.addHandler(consoleHandler);

            log.setLevel(Level.INFO);

        }

        catch(IOException e){
            e.printStackTrace();
        }

    }

   

    public static Logger getLogger(){
        return log;
    }
}