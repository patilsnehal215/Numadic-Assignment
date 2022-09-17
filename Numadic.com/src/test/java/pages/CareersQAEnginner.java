package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CareersQAEnginner {
	
	WebDriver driver;
	
	// Object Repository
	
	// Locating 'Apply here now' button
	@FindBy (xpath = "//a[text()='Apply here now']")
	public WebElement applyHereNowBtn;
	
	// Constructor
	public CareersQAEnginner (WebDriver driver){
	this.driver=driver;
	PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
	}
	
	// Method to click on 'Apply for QA Role' button
	public void clickOnApplyForQARole() {
		applyHereNowBtn.click();
	}
}
