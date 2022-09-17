package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class NumadicCareersHomapage {

	WebDriver driver;
	
	// Object Repository
	
	// Locating QA Engineer option
	@FindBy (xpath = "//h1[text()='JOIN OUR CREW']")
	public WebElement loginOurCrew;
		
	// Locating Job Type Dropdown
	@FindBy (id = "job_type")
	WebElement jobTypeDropdown;
	
	// Locating no job available text
	@FindBy (xpath = "//td[contains(text(),'There are no available job positions')]")
	public WebElement noAvailableJobs;
	
	// Locating QA Engineer option
	@FindBy (xpath = "//a[text()='QA Engineer']")
	WebElement qaEngineer;
	
	// Locating 'Apply' button for QA Engineer role
	@FindBy (xpath = "//a[text()='QA Engineer']//parent::td//following-sibling::td[3]//child::button")
	WebElement applyBtnForQAEngineerRole;
	
	// Locating the mandatory fields and validation messages under 'Personal Details'
	
	@FindBy (id = "firstName")
	WebElement firstName;
	
	@FindBy (xpath="//li[text()='Enter your first name']")
	public WebElement firstNameValidationMessage;
	
	@FindBy (id = "lastName")
	WebElement lastName;
	
	@FindBy (xpath="//li[text()='Enter your last name']")
	public WebElement lastNameValidationMessage;
	
	@FindBy (id = "email")
	WebElement emailTextBox;
	
	@FindBy (xpath="//li[text()='Enter an email']")
	public WebElement emailValidationMessage;
	
	@FindBy (id = "phone")
	WebElement phoneTextBox;
	
	@FindBy (xpath="//li[text()='Enter your mobile number']")
	public WebElement phoneValidationMessage;
	
	@FindBy (id = "dob")
	WebElement dob;
	
	@FindBy (xpath="//li[text()='Enter your date of birth']")
	public WebElement dobValidationMessage;
	
	@FindBy (id = "careers_form_btn_step1")
	WebElement nextBtn;
	
	@FindBy (xpath="//h2[text()='Apply for a Job']")
	public WebElement applyForAJobHeader;
	
	// Constructor
	public NumadicCareersHomapage (WebDriver driver){
	this.driver=driver;
	PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
	}
	
	// Method to select a job type from dropdown
	public void selectAJobType (String jobType) {
		Select s=new Select(jobTypeDropdown);
		s.selectByValue(jobType);
	}
	
	// Selecting and clicking on QA Engineer link
	public void selectJobAsQAEngineer() {
		qaEngineer.click();
	}
	
	// Locating 'Apply' button by hovering on 'QA Engineer' role
	public void applyForQAEngineer() throws InterruptedException {
		
		// Scrolling the webpage up
		JavascriptExecutor js=(JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-800)");
		
		//Creating object of an Actions class
		Actions action = new Actions(driver);

		//Performing the mouse hover action on the target element
		action.moveToElement(applyBtnForQAEngineerRole).build().perform();
		
		applyBtnForQAEngineerRole.click();
	}
	
	// Method to check the validations under Personal Details
	public void checkValidationsUnderPersonalDetails(String fisrtNameText, String lastNameText, String emailText, String phoneText, String dobText) throws InterruptedException {
		Thread.sleep(2000);
		firstName.sendKeys(fisrtNameText);
		lastName.sendKeys(lastNameText);
		emailTextBox.sendKeys(emailText);
		phoneTextBox.sendKeys(phoneText);
		dob.sendKeys(dobText);
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,800)");
		nextBtn.click();
	}
		
}
