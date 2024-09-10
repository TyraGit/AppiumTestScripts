package athirahrahmat;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class PreferenceDependencies extends BaseTest {

	@Test
	public void WifiSettings() {
		
		ad.findElement(AppiumBy.accessibilityId("Preference")).click();
		ad.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		ad.findElement(By.id("android:id/checkbox")).click();
		ad.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String wifiPopup = ad.findElement(By.id("android:id/alertTitle")).getText(); //to validate the text in wifi settings popup
		Assert.assertEquals(wifiPopup, "WiFi settings"); //if WiFi settings exists, the test is passed
		
		ad.findElement(By.id("android:id/edit")).sendKeys("Athirah WiFi");
		
		ad.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); //used findElements because classname is not unique
		
		
	}
}
