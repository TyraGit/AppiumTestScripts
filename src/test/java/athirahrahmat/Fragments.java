package athirahrahmat;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

public class Fragments extends AndroidBaseTest {
	
	@Test
	public void fragmentPage() {

		androidDriver.findElement(AppiumBy.accessibilityId("Preference")).click();

		androidDriver.findElement(AppiumBy.accessibilityId("7. Fragment")).click();
		
	}
	
    @Test(dependsOnMethods="fragmentPage")
    public void listPreference() {
    	    
        //click on List Preference item
    	androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(2)")).click();

        //click "Alpha Option 01" radio button using UIAutomator
    	androidDriver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Alpha Option 01\")")).click();
        
    }
    
    @Test(dependsOnMethods="fragmentPage")
    public void launchInternetWindow() {
    	
    	//click on Intent Preference item
    	androidDriver.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[5]/android.widget.RelativeLayout")).click();
    	
    	//back to previous screen
    	androidDriver.navigate().back();
    }
    
}
