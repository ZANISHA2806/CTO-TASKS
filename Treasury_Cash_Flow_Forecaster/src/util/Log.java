package util;
import java.io.IOException;

import java.util.logging.*;
public class Log {
	
	private static final Logger log=Logger.getLogger("log");
	
	static {
		try {
			log.setUseParentHandlers(false);
			
			FileHandler fileHandler=new FileHandler("treasury.log",true);
			
		
		fileHandler.setFormatter(new SimpleFormatter()
				);
		
		log.addHandler(fileHandler);
		ConsoleHandler consolehandler=new ConsoleHandler();
		consolehandler.setFormatter(new SimpleFormatter());
		
		log.addHandler(consolehandler);
		
		log.setLevel(Level.INFO);
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
	}
public static Logger getLogger() {
	return log;
}
}
