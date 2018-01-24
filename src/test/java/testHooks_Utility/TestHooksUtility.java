package testHooks_Utility;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import static pageObjectHelper.Driver.*;
import java.time.Duration;
import java.time.Instant; 

public class TestHooksUtility {

	private static final Logger log = LogManager.getLogger(TestHooksUtility.class);
	private static Instant start;
    private static Instant stop;
	
	
   @Before
    public void setUp(Scenario scenario) {
        setSystemsProperties();
        	initialiseDriver();       
        aDriver.manage().window().maximize();
        log.info("=====================================================");
        log.info("Starting Scenario:> " + scenario.getName());
        log.info("=====================================================");
        start = Instant.now();
    }
    
    @After
    public void tearDown(Scenario scenario) {   	
        screenShotOnFailure(scenario);
        stop = Instant.now();
        Duration totalTime = Duration.between(start, stop);       
        log.info("=====================================================");
        log.info("Completed Scenario:" + scenario.getName());
        log.info("Scenario Test time duration is:>  " + totalTime.toMinutes() + " Minutes : " + totalTime.toMillis() / 1000 + " Seconds ");                                                                                                           
        log.info("=====================================================");
        quiteDriver();
    }
}
