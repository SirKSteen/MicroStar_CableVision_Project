package loggers;

/*
 * import org.apache.log4j.Logger; import org.apache.log4j.PropertyConfigurator;
 * import org.apache.logging.log4j.LogManager;
 * 
 * public class Log4jJDBCExample { //static Logger log =
 * Logger.getLogger(Log4jJDBCExample.class); private static final Logger logger
 * = LogManager.getLogger(LoggingExample.class);
 * 
 * public static void main(String[] args) {
 * PropertyConfigurator.configure("log4j.properties");
 * 
 * log.debug("Sample debug message"); log.info("Sample info message");
 * log.error("Sample error message"); log.fatal("Sample fatal message"); } }
 */



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; 
 
public class Log4jJDBCExample 
{
    private static final Logger LOG = LogManager.getLogger(Log4jJDBCExample.class.getName());
     
    public static void main(String[] args) 
    {
    	//PropertyConfigurator.configure("log4j.properties");
        LOG.debug("Debug Message Logged"); 
        LOG.fatal("Sample fatal message");
        LOG.info("Info Message Logged");
        LOG.error("Error Message Logged");
        LOG.warn("Test Warn message");
    }
}