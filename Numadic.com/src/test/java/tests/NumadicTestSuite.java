package tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CareersQAEnginner;
import pages.NumadicCareersHomapage;

public class NumadicTestSuite {

	WebDriver driver; //Globally declared
	
	@BeforeTest
	public void beforetest() {
	WebDriverManager.chromedriver().setup();
	driver =new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://numadic.com/careers/");
	}

	// Testcase to validate 'Login our crew' button on Numadic careers page
	@Test(priority=1)
	public void validateLofinToCrewBtn() {
		boolean status;
		NumadicCareersHomapage numadicHomepage = new NumadicCareersHomapage(driver);
		status=numadicHomepage.loginOurCrew.isDisplayed();
        Assert.assertTrue(status, "Login our Crew is not desplayed");
	}
	
	// Testcase to click on 'Apply' button and redirection to careers page
	@SuppressWarnings("deprecation")
	@Test(priority=2)
	public void aaplyingForQARole() throws InterruptedException {
		boolean status;
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		NumadicCareersHomapage numadicHomepage = new NumadicCareersHomapage(driver);
		
		// Selecting 'Internship' value in 'Job Type' dropdown
		numadicHomepage.selectAJobType("IN");
		status=numadicHomepage.noAvailableJobs.isDisplayed();
		Assert.assertTrue(status, "No jobs available message is not displayed for Job Type Internship");
	
		// Selecting 'Full time' value in 'Job Type' dropdown
		numadicHomepage.selectAJobType("FT");
		numadicHomepage.selectJobAsQAEngineer();
		String currentUrl = driver.getCurrentUrl();;
		status=currentUrl.contains("/qa-engineer");
		Assert.assertTrue(status, "URL as qa-engineer is not verified");
		CareersQAEnginner careersQAEnginner = new CareersQAEnginner(driver);
		status = careersQAEnginner.applyHereNowBtn.isDisplayed();
		Assert.assertTrue(status, "Apply here now button is not displayed");
		careersQAEnginner.clickOnApplyForQARole();
		String currentUrl1 = driver.getCurrentUrl();;
		status=currentUrl1.contains("/#careersFormContainer");
		Assert.assertTrue(status, "Redirection back to careers page is not done");		
		numadicHomepage.applyForQAEngineer();
	}
	
//	@DataProvider (name = "data_provider")
//	public Object[][] dpMethod() {
//	    return new Object [][] {{"","","","",""}, {"Snehal", "Patil", "snehal@gmail.com", "9999999898", "02/02/2002"}};
//	}
	
//	@Test(priority=3, dataProvider = "data_provider")
	@Test(priority=3)
//	public void testcase03(String fisrtNameText1, String lastNameText1, String emailText1, String phoneText1, String dobText1) throws InterruptedException {
		
	public void checkingValidationsUnderPersonalDetails() throws InterruptedException {
	boolean status;
		NumadicCareersHomapage numadicHomepage = new NumadicCareersHomapage(driver);
//		numadicHomepage.checkValidationsUnderPersonalDetails(fisrtNameText1, lastNameText1, emailText1, phoneText1, dobText1);
		numadicHomepage.checkValidationsUnderPersonalDetails("","","","","");
		// Thread.sleep(3000);
		status=numadicHomepage.firstNameValidationMessage.isDisplayed() && numadicHomepage.lastNameValidationMessage.isDisplayed() && 
				numadicHomepage.emailValidationMessage.isDisplayed() && numadicHomepage.phoneValidationMessage.isDisplayed() && numadicHomepage.dobValidationMessage.isDisplayed();

		Assert.assertTrue(status,"Validation messages are not getting displayed");
		// Thread.sleep(3000);
		
		try {
		numadicHomepage.checkValidationsUnderPersonalDetails("Snehal", "Patil", "snehal@gmail.com", "9999999898", "02/02/2002");
		status=numadicHomepage.firstNameValidationMessage.isDisplayed() || numadicHomepage.lastNameValidationMessage.isDisplayed() || 
				numadicHomepage.emailValidationMessage.isDisplayed() || numadicHomepage.phoneValidationMessage.isDisplayed() || numadicHomepage.dobValidationMessage.isDisplayed();
		
		Assert.assertFalse(status,"Validation messages are getting displayed for valid inputs");
		}
		catch (Exception NoSuchElementException) {
		}
	}
	
	// Quitting the browser
	@AfterTest
	public void aftertest() {
		driver.quit();
	}
	
}
