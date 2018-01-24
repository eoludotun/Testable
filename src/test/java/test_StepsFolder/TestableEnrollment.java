package test_StepsFolder;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjectHelper.URLUtility;
import testHooks_Utility.TestConfigUtility;
import test_PageObjectsModel.TestableEnrollmentPage;

public class TestableEnrollment extends TestConfigUtility  {

	private static URLUtility appURL = new URLUtility();
	private static TestableEnrollmentPage testEnrollOBJ = new TestableEnrollmentPage();


	@Given("^User is on the Enrollment page$")
	public void user_is_on_the_Enrollment_page() throws Throwable {
		appURL.goToTestURL();
	}

	@Given("^Verify the \"([^\"]*)\" display on the page$")
	public void verify_the_display_on_the_page(String expectedTitle) throws Throwable {
		String actualTitle = testEnrollOBJ.verifyWelcome();
		checkMatchingValues(actualTitle, expectedTitle);
	
	}

	@When("^User clicks \"([^\"]*)\" button$")
	public void user_clicks_button(String buttonName) throws Throwable {
		testEnrollOBJ.clickEnrollButton(buttonName);

	}

	@When("^User is re-directed to \"([^\"]*)\" page$")
	public void user_is_re_directed_to_page(String expectedMSG) throws Throwable {
    	String actualMsg = testEnrollOBJ.signUpPage();
		checkMatchingValues(actualMsg, expectedMSG);
	}

	@When("^User filled the enrollment form$")
	public void user_filled_the_enrollment_form() throws Throwable {		
		String firstName = faker.firstName();
		String lastName = faker.lastName();
		String email = generateEmail(20);
		String passWord = generateRandomString(12);
		
		testEnrollOBJ.enrollmentForm(firstName+" "+lastName, email, passWord, passWord);
	}
	
	@When("^User filled the enrollment form with a mis-match password$")
	public void user_filled_the_enrollment_form_with_a_mis_match_password() throws Throwable {
		String firstName = faker.firstName();
		String lastName = faker.lastName();
		String email = generateEmail(20);
		String passWord = generateRandomString(12);
		String confirmPassWord = generateRandomString(12);
		
		testEnrollOBJ.enrollmentForm(firstName+" "+lastName, email, passWord, confirmPassWord);

	}

	@When("^User checks the i agree checkbox$")
	public void user_checks_the_i_agree_checkbox() throws Throwable {
		testEnrollOBJ.agreeCheckBox();		
	}

	@When("^User clicks Sign Up button$")
	public void user_clicks_Sign_Up_button() throws Throwable {
		testEnrollOBJ.clickSignUP();		
	}
	
	@Then("^Validate course author name's = \"([^\"]*)\"$")
	public void validate_course_author_name_s(String expectedAuthor) throws Throwable {
		String actualAuthor = testEnrollOBJ.validateCourseAuthor();
		checkMatchingValues(actualAuthor, expectedAuthor);		
	}
	
	@Then("^Validate password mis-match error message = \"([^\"]*)\" display$")
	public void validate_password_mis_match_error_message_display(String expectedPassWordERR) throws Throwable {
		String actualPassWordERR = testEnrollOBJ.passWordMisMatchErrorMSG();
		checkMatchingValues(actualPassWordERR, expectedPassWordERR);
	}
	
	@When("^User clicks the FREE button$")
	public void user_clicks_the_FREE_button() throws Throwable {
		testEnrollOBJ.clickFreeButton();		
	}
	
	@When("^User clicks \"([^\"]*)\" cart button$")
	public void user_clicks_cart_button(String cartmessage) throws Throwable {
		testEnrollOBJ.enrollCartButton();
		log().info("user is on : " + cartmessage);
		
	}
	
	@Then("^Validate enrollement message = \"([^\"]*)\"$")
	public void validate_enrollement_message(String expectedEnrollmentMSG) throws Throwable {
		String actualEnrollmentMSG = testEnrollOBJ.validateEnrollmentMSG();
		checkMatchingValues(actualEnrollmentMSG, expectedEnrollmentMSG);
		
		//click the contiune to course button
		testEnrollOBJ.clickContiuneToCourse();		
	}

    @When("^User scrolled through all courses and completed all required video$")
    public void user_scrolled_through_all_courses_and_completed_all_required_video() throws Throwable {
    	testEnrollOBJ.userCompletedAllCources();
    }
	
	@Then("^Validate the couurse completed is \"([^\"]*)\"$")
	public void validate_the_couurse_completed_is(String expectedCourseProgress) throws Throwable {
		String actualCourseProgress = testEnrollOBJ.validateCourseCompletedMSG();
		checkMatchingValues(actualCourseProgress, expectedCourseProgress);		
	}
	
	@Then("^Validate \"([^\"]*)\" display on the page$")
	public void validate_display_on_the_page(String expectedDisplay) throws Throwable {
		String actualDisplay = testEnrollOBJ.validateCategoryDisplay();
		checkMatchingValues(actualDisplay, expectedDisplay);		
	}
}
