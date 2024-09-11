package athirahrahmat;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class XCUIBaseTest {
	
	public IOSDriver iOSDriver;
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
		
		
		XCUITestOptions iOSoptions = new XCUITestOptions();
		iOSoptions.setDeviceName("iPhone 12");
		iOSoptions.setApp("/Users/athirah/Library/Developer/Xcode/DerivedData/UIKitCatalog-aozglzfythmkyoemcbnhpacpxjpm/Build/Products/Debug-iphonesimulator/UIKitCatalog.app");
		iOSoptions.setPlatformVersion("16.4");
		iOSoptions.setWdaLaunchTimeout(Duration.ofSeconds(20));
		
		iOSDriver = new IOSDriver((new URI("http://192.168.1.109:4723/")).toURL(), iOSoptions);
		
		iOSDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	public void backToMainScreen() {
		
		//clicking the back button to go back to the Main screen
		WebElement backButton = iOSDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"UIKitCatalog\"`]"));
		backButton.click();		
	}
	
	//Method to perform scroll down action using W3C Actions
    public void scrollPage() {
        // Get screen dimensions for swipe calculations
        Dimension size = iOSDriver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.3); // Starting point near the top of the screen
        int endY = (int) (size.height * 0.7); // Ending point near the bottom of the screen

        // Define the pointer input for touch interactions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Create a sequence of actions to scroll down
        Sequence scrollDown = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the scrolling action
        iOSDriver.perform(Collections.singletonList(scrollDown));
    }
	
	@AfterClass
	public void tearDown() {
		iOSDriver.quit();
		service.stop();
	}

}
