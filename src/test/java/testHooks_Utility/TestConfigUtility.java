package testHooks_Utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fluttercode.datafactory.impl.DataFactory;
import org.json.JSONObject;
import org.junit.Assert;
import org.yaml.snakeyaml.Yaml;


import com.esotericsoftware.yamlbeans.YamlReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import gherkin.formatter.model.Scenario;

public class TestConfigUtility {
	public static Scenario scenario;
	public String sessionId;
	public String exeBrowserName = System.getProperty("browserName");
	public String exePlatfom = System.getProperty("platform");
	private static Logger _logger;
	private Date date = new Date();  
	private SimpleDateFormat sdf = new SimpleDateFormat("dd, MM, yyyy"); 
	private static Random myRandom;
	protected Faker faker = new Faker();


	
	public static Scenario getScenario() {
		return scenario;
	}

	public TestConfigUtility() {
		// Empty constructor
	}

	public void pause(Integer milliseconds){
	    try {
	        TimeUnit.MILLISECONDS.sleep(milliseconds);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
		
	 //Singleton Logger
    public static Logger log()
    {
    	if(_logger == null)
    		_logger = LogManager.getLogger();
    	return _logger;
    }

	public boolean checkMatchingValues(Object actualValue, Object expectedValue) {
        String successMessage = "\t* The Expected and Actual Values match. (PASS)\n";
        String failureMessage = "\t* The Expected and Actual Values do not match! (FAIL)\n";

        boolean doValuesMatch = false;

        log().info("\t* Expected Value: " + expectedValue);
        log().info("\t* Actual Value: " + actualValue);

        if (actualValue.equals(expectedValue)) {
        	log().info(successMessage);
        	Assert.assertEquals(actualValue, expectedValue);
            doValuesMatch = true;
            Assert.assertTrue(doValuesMatch);
        } else {
        	log().info(failureMessage);
            doValuesMatch = false;
            Assert.assertTrue(doValuesMatch);
        }
        return doValuesMatch;
    }

    public String RandomPhoneNumber()
    {
        String tempString = "";
        for (int i = 0; i < 3; i++)
        {
            tempString = tempString + myRandom.nextInt(10);
        }
        tempString = tempString + '-';
        for (int i = 0; i < 3; i++)
        {
            tempString = tempString + myRandom.nextInt(10);
        }
        tempString = tempString + '-';
        for (int i = 0; i < 4; i++)
        {
            tempString = tempString + myRandom.nextInt(10);
        }
        return tempString;
    }

    public String generateRandomString(int length)
    {
    	  return RandomStringUtils.randomAlphabetic(length);
    }
    	 
    public String generateRandomNumber(int length)
    {
    	  return RandomStringUtils.randomNumeric(length);
    }
    	 
    public String generateRandomAlphaNumeric(int length)
    {
    	  return RandomStringUtils.randomAlphanumeric(length);
    }
    	 
    public String generateStringWithAllobedSplChars(int length,String allowdSplChrs)
    {
    	  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    	    "1234567890" +   //numbers
    	    allowdSplChrs;
    	  return RandomStringUtils.random(length, allowedChars);
    }
    	 
    public String generateEmail(int length)
    {
    	  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    	    "1234567890" +   //numbers
    	    "_-.";   //special characters
    	  String email="";
    	  String temp=RandomStringUtils.random(length,allowedChars);
    	  email=temp.substring(0,temp.length()-9)+"@automationtest.com";
    	  return email;
     }
    	 
    public String generateUrl(int length) 
    {
    	  String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
    	    "1234567890" +   //numbers
    	    "_-.";   //special characters
    	  String url="";
    	  String temp=RandomStringUtils.random(length,allowedChars);
    	  url=temp.substring(0,3)+"."+temp.substring(4,temp.length()-4)+"."+temp.substring(temp.length()-3);
    	  return url;
    }  
}
