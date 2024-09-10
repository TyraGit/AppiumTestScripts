package athirahrahmat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver ad;
	public AppiumDriverLocalService service;
	
	@BeforeClass
	public void configurations() throws MalformedURLException, URISyntaxException {
		
		//start appium server programmatically instead using Terminal
		AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("/Users/athirah/.npm-global/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("192.168.1.109")
                .usingPort(4723)
                .usingDriverExecutable(new File("/opt/homebrew/bin/node"));
        
        service = AppiumDriverLocalService.buildService(builder);
        
		service.start();
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("Pixel 8 API 35");
		options.setApp("/Users/athirah/eclipse-workspace/AppiumTestScripts/src/test/java/resources/ApiDemos-debug.apk");
		
		ad = new AndroidDriver((new URI("http://192.168.1.109:4723/")).toURL(), options);
		
		ad.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	@AfterClass
	public void tearDown() {
		ad.quit();
		service.stop();
	}

}
