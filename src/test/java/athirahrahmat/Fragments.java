package athirahrahmat;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;

public class Fragments extends BaseTest {
	
	@Test
	public void fragmentPage() {

    	ad.findElement(AppiumBy.accessibilityId("Preference")).click();

        ad.findElement(AppiumBy.accessibilityId("7. Fragment")).click();
		
	}
	
    @Test(dependsOnMethods="fragmentPage")
    public void listPreference() {
    	    
        //click on List Preference item
        ad.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.RelativeLayout\").instance(2)")).click();

        //click "Alpha Option 01" radio button using UIAutomator
        ad.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Alpha Option 01\")")).click();
        
    }
    
    @Test(dependsOnMethods="fragmentPage")
    public void launchInternetWindow() {
    	
    	//click on Intent Preference item
    	ad.findElement(By.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[5]/android.widget.RelativeLayout")).click();
    	
    	//back to previous screen
    	ad.navigate().back();
    }
    
}
