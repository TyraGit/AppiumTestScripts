package android;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Preference extends AndroidBaseTest {

	@Test
	public void WifiSettings() {
		//open Preference page
		androidDriver.findElement(AppiumBy.accessibilityId("Preference")).click();
		
		//open Preference Dependencies page
		androidDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		androidDriver.findElement(By.id("android:id/checkbox")).click(); //click a checkbox
		androidDriver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String wifiPopup = androidDriver.findElement(By.id("android:id/alertTitle")).getText(); //to validate the text in wifi settings popup
		Assert.assertEquals(wifiPopup, "WiFi settings"); //if WiFi settings exists, the test is passed
		
		androidDriver.findElement(By.id("android:id/edit")).sendKeys("Athirah WiFi");
		
		androidDriver.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); //used findElements because classname is not unique
	}
	
	@Test
	public void fragmentPage() {

		androidDriver.findElement(AppiumBy.accessibilityId("Preference")).click();

		androidDriver.findElement(AppiumBy.accessibilityId("7. Fragment")).click();
		
        //click on List Preference item
    	androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(2)")).click();

        //click "Alpha Option 01" radio button using UIAutomator
    	androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Alpha Option 01\")")).click();
 	
    	//click on Intent Preference item
    	androidDriver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[5]/android.widget.RelativeLayout")).click();
    	
    	//back to previous screen
    	androidDriver.navigate().back();
    }
}
