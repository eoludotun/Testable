package pageObjectHelper;

import cucumber.api.Scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;



public class Driver {

    public static WebDriver aDriver;
    private static String currentDir;
    private static Logger _logger;
  

    public Driver(WebDriver aDriver) {
        Driver.aDriver = aDriver;
    }
    
   
    //Singleton Logger
    public static Logger log()
    {
    	if(_logger == null)
    		_logger = LogManager.getLogger();
    	return _logger;
    }

    public static void setSystemsProperties() {
        currentDir = System.getProperty("user.dir");
        log().info("Current Platform is: " + System.getProperty("os.name"));
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", currentDir
                    + "/drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", currentDir
                    + "/drivers/chromedriver");
        }
    }

    public static WebDriver initialiseDriver() {
        setSystemsProperties();
        aDriver = new ChromeDriver();
        log().info("Current Browser is: " + aDriver);
        System.out.println(aDriver);
        aDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        return aDriver;
    }
    
    public static void quiteDriver() {
        try {
            aDriver.close();
            if (aDriver != null) {

                aDriver.quit();
                aDriver = null;
            }
        } catch (UnreachableBrowserException e) {

        	log().info("Browser has already Shutdown");
        }
    }
    
    public Driver sendTextToWeBElement(By locator, String textValue) throws Exception{
    	
        try {
        	
        	WebElement clickElement = aDriver.findElement(locator);
            new WebDriverWait(aDriver,5L).
           until(ExpectedConditions.visibilityOf(clickElement));  	
            	clickElement.sendKeys(textValue);
            	log().info("Text Value sent is : " + textValue);
            return this;
            }

        	   catch (Exception e) 
            {
        		   
        		   log().error("Error in  Value sent " + e.getMessage());
        		   throw(e);
            }
        }
    
    public Driver selectListByTextOption(By locator, String option){
    	WebElement dropDown = aDriver.findElement(locator);
    	dropDown = new WebDriverWait(aDriver,5L).
                until(ExpectedConditions.visibilityOf(dropDown));
        Select options = new Select(dropDown);
        options.selectByVisibleText(option);
		return this;
    }
    
    public String selectFirstOption(By locator, String option){
    	WebElement select = aDriver.findElement(By.name("myselect"));
    	Select dropDown = new Select(select);           
    	String selected = dropDown.getFirstSelectedOption().getText();
    	if(selected.equals(option)){
    		log().info("First Text in the dropdown is selected : =  " + selected);
    		
    	}
		return selected; 
    	
    }

    public WebElement pageWeBElement(By locator) throws Exception{
    	
        try {
        	
        	WebElement clickElement = aDriver.findElement(locator);
            new WebDriverWait(aDriver,30).
             until(ExpectedConditions.visibilityOf(clickElement));          
            return clickElement;
            }

        	   catch (Exception e) 
            {
        		   
        		   log().error("Error in  Value sent " + e.getMessage());
        		   throw(e);
            }
        } 
      
    public List<WebElement> pageWeBElements(By locator) throws Exception{
    	
        try {
        	
           List	<WebElement> clickElement = aDriver.findElements(locator);
            new WebDriverWait(aDriver,30).
             until(ExpectedConditions.visibilityOfAllElements(clickElement));          
            return clickElement;
            }

        	   catch (Exception e) 
            {
        		   
        		   log().error("Error in  Value sent " + e.getMessage());
        		   throw(e);
            }
        }   
       
    public Driver clickWeBElement(By locator) throws Exception{   	
    try {
    	
    	WebElement clickElement = aDriver.findElement(locator);
        new WebDriverWait(aDriver,5L).
       until(ExpectedConditions.visibilityOf(clickElement));
          
        
        if(clickElement.isDisplayed() && clickElement.isEnabled())
        {
        	log().info("This current element seems enabled, no need javascript Click");
        	clickElement.click();
        }
        else
        {
        	log().info("This current element needs javascript Click");
        	javaScriptClick(clickElement);
        }
        return this;
        }

    	   catch (Exception e) 
        {
    		   
    		   log().error("clickWeBElement error " + e.getMessage());
    		   throw(e);
        }
    }
    
    public Driver clickWeBElementWithJavaScript(By locator) throws Exception{
    	
        try {
        	
        	WebElement clickElement = aDriver.findElement(locator);
            new WebDriverWait(aDriver,30).
            until(ExpectedConditions.visibilityOf(clickElement));
              
            
            if(clickElement.isDisplayed() && clickElement.isEnabled())
            {
            	
            	javaScriptClick(clickElement);
            }
            else
            {
            	log().info("This current element is either not enabled or not clickable");
            }
            return this;
            }

        	   catch (Exception e) 
            {
        		   
        		   log().error("clickWeBElementWithJavaScript error " + e.getMessage());
        		   throw(e);
            }
        }
    
	public void javaScriptClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)aDriver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void actionClick(WebElement element) {
		Actions actions = new Actions(aDriver);
		actions.moveToElement(element);
		actions.click();
		actions.build().perform();
	}
	
	public void actionMoveToClick(WebElement startElement, By locator) {
		Actions actions = new Actions(aDriver);
		actions.moveToElement(startElement).moveToElement(aDriver.findElement(locator))
		.click().build().perform();
	}
	
	public void waitUntilClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
    public static void screenShotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) try {
            scenario.write(scenario.getName() + " ---- " + scenario.getName()
                    + " ------- " + scenario.getStatus() + "\n"
                    + aDriver.getCurrentUrl());
            byte[] screenshot = ((TakesScreenshot) aDriver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException screenShotException) {
        	 log().error(screenShotException.getMessage());
        }
    }

	protected boolean windowExists(String windowName) {
		String initialWindow = aDriver.getWindowHandle();
		Set<String> windowSet = aDriver.getWindowHandles();
		for (String window : windowSet) {
			aDriver.switchTo().window(window);
			// This is specific to Directory tasks, because they only have a sub
			// title and not a real title like everything else.
			 log().info(aDriver.getTitle());
			if (aDriver.getTitle().equals(windowName)) {
				// driver.switchTo().window(initialWindow);
				return true;
			}
		}
		aDriver.switchTo().window(initialWindow);
		return false;
	}

	protected boolean isObjectActive(String objectName) {
		if (aDriver.findElement(By.xpath("//a[@class='active' and text()='"
				+ objectName + "']")) != null) {
			return true;
		}
		return false;
	}
	
	public static WebDriverWait waitTillElementFound(WebElement ElementTobeFound, int seconds) {
	WebDriverWait wait = new WebDriverWait(aDriver, seconds);
	wait.until(ExpectedConditions.visibilityOf(ElementTobeFound));
	return wait;
	}
	
	public void clickCheckboxFromList(String xpathOfElement, String valueToSelect) {

	List<WebElement> lst = aDriver.findElements(By.xpath(xpathOfElement));
	for (int i = 0; i < lst.size(); i++) {
	List<WebElement> dr = lst.get(i).findElements(By.tagName("label"));
	for (WebElement f : dr) {
    log().info("Value in the list : " + f.getText() );
	if (valueToSelect.equals(f.getText())) {
	f.click();
	break;
	}
	}
	}
	}
	public void selectElementByIndexMethod(WebElement element, int index) {
	Select selectitem = new Select(element);
	selectitem.selectByIndex(index);
	}
	public void selectElementByValueMethod(WebElement element, String value) {
		
	Select selectitem = new Select(element);
	selectitem.selectByValue(value);
	}
	
	public void selectElementByNameMethod(WebElement element, String Name) {
	Select selectitem = new Select(element);
	selectitem.selectByVisibleText(Name);
	}
	
	public Boolean isCheckBoxChecked(By locator, String elemName) {
		Boolean checkBox = false;
		
        log().info("Checking if the checkbox related to '"+elemName+"' is checked or not.");
        try {

            if (pageWeBElement(locator).isSelected() || pageWeBElement(locator).isEnabled()) {
                log().info("Checkbox related to: '"+elemName+"' is checked.");
                checkBox = true;
                Assert.assertTrue(checkBox);

            }else{
                log().info("Checkbox related to: '"+elemName+"' is not checked!!");
                checkBox = false;
                Assert.assertTrue(checkBox);
            }
        }
        catch (Throwable t) {

            log().error("Error while Checking if the checkbox related to '"+elemName+"' is checked or not. -" + t.getMessage());

        }
		return checkBox;

    }
}