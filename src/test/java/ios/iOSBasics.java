package ios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class iOSBasics extends XCUIBaseTest {
	
	@Test
	public void AlertView() {
		//opening Alert Views item
		iOSDriver.findElement(AppiumBy.accessibilityId("Alert Views")).click();
		
		//click Simple item
		iOSDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Simple\"]")).click();		
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"OK\"")).click(); //click OK button
		
		//click Text Entry item
		iOSDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Text Entry\"]")).click();
		iOSDriver.findElement(By.className("XCUIElementTypeTextField")).sendKeys("This is from automation testing");
		iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"Cancel\"`]")).click(); //click Cancel button
		
		//click Other item
		iOSDriver.findElement(AppiumBy.accessibilityId("Other")).click();
		String description = iOSDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"A message should be a short, complete sentence.\"]")).getText();
		System.out.println("This is the subtext from the alert: " + description); //print the subtext of an alert
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Choice One\"")).click();
		
		goToMainScreenUsingBackButton(); //to go back to Main screen
	}
	
	@Test
	public void SearchItem() {
		//opening Search item
		iOSDriver.findElement(AppiumBy.accessibilityId("Search")).click();
		iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"chevron\"`][1]")).click();
		WebElement searchBox = iOSDriver.findElement(By.className("XCUIElementTypeSearchField"));
		WebElement cancelButton = iOSDriver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Cancel\"]"));
		
		if (searchBox != null) {
			
			System.out.println("Is the cancel button not active? " + cancelButton.isEnabled());
			searchBox.sendKeys("Hello world");
			//searchBox.submit();
			System.out.println("Is the cancel button active now? " + cancelButton.isEnabled());
			
		}
		
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Search\"")).click(); //click the Search button on top left corner to go back to previous screen
		goToMainScreenUsingBackButton(); //to go back to Main screen
		
	}
	
	@Test
	public void Slider() {
		//opening Slider item
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"Sliders\"")).click();
		
		WebElement defaultSlider = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[1]"));
		// Set the slider to 75%
		defaultSlider.sendKeys("0.75"); // Values are usually between "0" (0%) and "1" (100%)
		
		WebElement minMaxSlider = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeSlider[4]"));
		// Set the slider to 75%
		minMaxSlider.sendKeys("0.20"); // Values are usually between "0" (0%) and "1" (100%)
		
		goToMainScreenUsingBackButton(); //to go back to Main screen
	}
	
	@Test
	public void WebViewItem() {
		//opening Web View item
		scrollPage("Web View"); //scrolling the Main page to find Web View
		
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("label == \"Web View\"")).click();
		
		goToMainScreenUsingBackButton(); //to go back to Main screen
	}
	
	@Test
	public void DatePickerItem() {
		//opening Date Picker item
		iOSDriver.findElement(AppiumBy.accessibilityId("Date Picker")).click();

		WebElement DatePicker = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeButton/XCUIElementTypeButton[1]"));
		
		DatePicker.click(); //to expand date picker
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("value == \"15\"")).click();
		
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"PopoverDismissRegion\"")).click(); //to collapse the picker
		
		WebElement TimePicker = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeButton/XCUIElementTypeButton[2]"));
		
		TimePicker.click(); //to expand time picker
		WebElement hourTimePicker = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[1]")); //hour
		WebElement minuteTimePicker = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[2]")); //minute
		
		hourTimePicker.sendKeys("13");
		minuteTimePicker.sendKeys("30");
		
		iOSDriver.findElement(AppiumBy.iOSNsPredicateString("name == \"PopoverDismissRegion\"")).click(); //to collapse the picker
		
		String datetime = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeWindow/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther"
				+ "/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")).getText();
		System.out.println("The selected date and time are: " + datetime);
		
		goToMainScreenUsingBackButton(); //to go back to Main screen
		
	}

}
