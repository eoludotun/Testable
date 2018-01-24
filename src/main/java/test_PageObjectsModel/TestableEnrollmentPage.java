package test_PageObjectsModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjectHelper.Driver;

public class TestableEnrollmentPage extends Driver{

    public TestableEnrollmentPage(){
        super(aDriver);
    }

    public String searchPageTitle(){

    new WebDriverWait(aDriver, 2L)
                .until(ExpectedConditions.titleContains(""));
        return aDriver.getTitle();
    }
    
    public String verifyWelcome() throws Exception
    {
    	return pageWeBElement(By.xpath("//h2[contains(@class, 'headline')]")).getText();
    }
    
    public void clickEnrollButton(String buttonName) throws Exception
    {
    	pageWeBElement(By.xpath("//a[contains(text(), '"+buttonName+"')]")).click();
    }
    
    public String signUpPage() throws Exception
    {
		return pageWeBElement(By.xpath("//div[contains(@class, 'content-box')]/h1")).getText();

    }
    
    public void enrollmentForm(String fullName, String emailAddress, String passWord, String confirmPassWord) throws Exception
    { 
    	pageWeBElement(By.name("user[name]")).sendKeys(fullName);
        pageWeBElement(By.name("user[email]")).sendKeys(emailAddress);
        pageWeBElement(By.name("user[password]")).sendKeys(passWord);
        pageWeBElement(By.name("user[password_confirmation]")).sendKeys(confirmPassWord);
    	
    }
    
    public void agreeCheckBox() throws Exception
    {
    	WebElement agreeCHK = pageWeBElement(By.id("user_agreed_to_terms"));
    	
    	if (!agreeCHK.isSelected())
    	{
    		agreeCHK.click();
    	}
    	log().info(agreeCHK.getText() + " is already checked");   	
    }
    
    public void clickSignUP() throws Exception
    {
    	pageWeBElement(By.name("commit")).click();
    }
    
    public String validateCourseAuthor() throws Exception
    {
    	return pageWeBElement(By.xpath("//div[contains(@class, 'course-author-name')]")).getText();
		 
    }
    
    public String validateCategoryDisplay() throws Exception
    {
    	return pageWeBElement(By.xpath("//div[contains(@class, 'filter-label')]")).getText();
    }
    
    public String passWordMisMatchErrorMSG() throws Exception
    {
    	return pageWeBElement(By.xpath("//div[contains(@class, 'alert-registration-page')]/ul")).getText();
    }
    
    public String validateEnrollmentMSG() throws Exception
    {
    	return pageWeBElement(By.xpath("//div[contains(@class, 'text-cente')]/h1")).getText();
    }
    
    public String validateCourseCompletedMSG() throws Exception
    {
    	return pageWeBElement(By.xpath("//div[contains(@class, 'course-progress')]/span")).getText();
    }
    
    public void userCompletedAllCources() throws Exception
    {
    	pageWeBElement(By.xpath("//div[contains(@class, 'next-lecture-wrapper')]/a")).click();
    	pageWeBElement(By.id("lecture_complete_button")).click();
    	pageWeBElement(By.xpath("//i[contains(@class, 'fa-arrow-left')]")).click();
      	pageWeBElement(By.xpath("//a[contains(@class, 'complete')]/i")).click();
      	
    	for (int i = 1; i < 3; i++){
			 //click the button
    		pageWeBElement(By.xpath("//i[contains(@class, 'fa-arrow-left')]")).click();		
			}
    }
    
    public void clickContiuneToCourse() throws Exception
    {
    	pageWeBElement(By.xpath("//a[contains(@class, 'goto-course')]")).click();
    }
    
    public void clickFreeButton() throws Exception
    {
    	pageWeBElement(By.xpath("//div[contains(@class, 'course-price')]")).click();
    }
    
    public void enrollCartButton() throws Exception
    {
    	pageWeBElement(By.id("enroll-button-top")).click();
    }
//    public TestableEnrollmentPage  searchKeyword(String keyword) throws Exception{
//    	
//    	WebElement searchBox = pageWeBElement(By.name("q"));
//        if (searchBox.isEnabled())
//        {
//        	searchBox.clear();
//            searchBox.sendKeys(keyword, Keys.ENTER);         	
//        }    	
//        return this;
//    }

}
