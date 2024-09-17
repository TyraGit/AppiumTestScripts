package android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Views extends AndroidBaseTest {
	
	@Test
	public void fiveStarsRatingBar() {
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Views\")")).click(); //open Views page
		
		scrollPage("Rating Bar");
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Rating Bar\")")).click(); //open Rating Bar page
		
		WebElement ratingBar = androidDriver.findElement(AppiumBy.xpath("//android.widget.RatingBar[@resource-id=\"io.appium.android.apis:id/ratingbar2\"]"));
		
		ratingBar.click();
        
        WebElement ratingValue = androidDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"io.appium.android.apis:id/rating\"]"));
        System.out.println(ratingValue.getText());
        
        androidDriver.navigate().back();
	}
	
	@Test(dependsOnMethods="fiveStarsRatingBar")
	public void dragAndDropRedCircle() {
		
		scrollPage("Drag and Drop");
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Drag and Drop\")")).click(); //open Drag and Drop page
		
		WebElement source = androidDriver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));
		WebElement target = androidDriver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		
		dragAndDrop(source, target);
		
		WebElement dragDropOutput = androidDriver.findElement(By.id("io.appium.android.apis:id/drag_result_text"));
		System.out.println(dragDropOutput.getText());
		
		androidDriver.navigate().back();
	}
	
	/*@Test(dependsOnMethods="dragAndDropRedCircle")
	public void draggingClock() {
		//to drag the hour/minute hands in a time widget
		scrollPage("Date Widgets");
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Date Widgets\")")).click(); //open Date widgets page
		
		androidDriver.findElement(AppiumBy.accessibilityId("2. Inline")).click();
		
		//dragging the hour hand to 10
		WebElement hourHand = androidDriver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"10\"]"));
		// Perform a long press on the hour
        longPress(hourHand);
        
		//dragging the minute hand to 
		WebElement minuteHand = androidDriver.findElement(By.xpath("//android.widget.RadialTimePickerView.RadialPickerTouchHelper[@content-desc=\"35\"]"));
		// Perform a long press on the minute
        longPress(minuteHand);
        
        androidDriver.findElement(By.xpath("//android.widget.RadioButton[@resource-id=\"android:id/am_label\"]")).click();
        
        androidDriver.navigate().back();
	}*/

	
	@Test(dependsOnMethods="dragAndDropRedCircle")
	public void DatePickerAndTimeSpinner() {
		
		scrollPage("Date Widgets");
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Date Widgets\")")).click(); //open Date widgets page
		
		androidDriver.findElement(AppiumBy.accessibilityId("1. Dialog")).click();
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/pickDate\")")).click();
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"26\")")).click(); //select date 26
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")")).click(); //click OK button
		
		androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/pickTime\")")).click();
		
		//select hour
		WebElement hourHand = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"4\")"));
		hourHand.click();
        
		//select minute
		WebElement minuteHand = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().description(\"45\")"));
		minuteHand.click();
        
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/am_label\")")).click();
        
        androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"android:id/button1\")")).click(); //click OK button
        
        WebElement datetimeValue = androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/dateDisplay\")"));
        
        System.out.println(datetimeValue.getText());
        
	}
	
}
