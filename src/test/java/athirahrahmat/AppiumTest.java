package athirahrahmat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumTest {

	@Test
	public void Test1() throws URISyntaxException, MalformedURLException {
		
		//start appium server programmatically instead using Terminal
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/athirah/.npm-global/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("192.168.1.121")
                .usingPort(4723)
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"));
        
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        
		service.start();
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 API 35");
		options.setApp("/Users/athirah/eclipse-workspace/Appium/src/test/java/resources/ApiDemos-debug.apk");
		
		AndroidDriver ad = new AndroidDriver((new URI("http://192.168.1.121:4723/")).toURL(), options);
		
		ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		ad.findElement(AppiumBy.accessibilityId("Preference")).click();
		ad.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		ad.findElement(By.id("android:id/checkbox")).click();
		ad.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String wifiPopup = ad.findElement(By.id("android:id/alertTitle")).getText(); //to validate the text in wifi settings popup
		Assert.assertEquals(wifiPopup, "WiFi settings"); //if WiFi settings exists, the test is passed
		
		ad.findElement(By.id("android:id/edit")).sendKeys("Athirah WiFi");
		
		ad.findElements(AppiumBy.className("android.widget.Button")).get(1).click(); //used findElements because classname is not unique
		
		ad.quit();
		service.stop();
		
	}
}
