package android;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest {
	
	public AndroidDriver androidDriver;
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
		
		androidDriver = new AndroidDriver((new URI("http://192.168.1.109:4723/")).toURL(), options);
		
		androidDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
    public void scrollPage(String text) {
       //using UiScrollable to scroll into view
    	androidDriver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }
    
    public void dragAndDrop(WebElement source, WebElement target) {
    	//using W3C to drag and drop
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDropSequence = new Sequence(finger, 1);

        // Move to the source element and press down (starting point of drag)
        dragAndDropSequence.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), 
                source.getLocation().getX(), source.getLocation().getY()));
        dragAndDropSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        
        // Wait to simulate the hold for drag
        dragAndDropSequence.addAction(new Pause(finger, Duration.ofMillis(1000)));

        // Move to the target element (ending point of drop)
        dragAndDropSequence.addAction(finger.createPointerMove(Duration.ofMillis(1000), Origin.viewport(), 
                target.getLocation().getX(), target.getLocation().getY()));
        
        // Release the press to drop the element
        dragAndDropSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the drag and drop sequence
        androidDriver.perform(Collections.singletonList(dragAndDropSequence));
    }
    
    // Method to perform long press
    public void longPress(WebElement element) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPressSequence = new Sequence(finger, 1);

        // Move to the element and press down
        longPressSequence.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), element.getLocation().getX(), element.getLocation().getY()));
        // Long press for 2 seconds (2000 milliseconds)
        longPressSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPressSequence.addAction(new Pause(finger, Duration.ofSeconds(2)));
        // Release the press
        longPressSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the long press action
        androidDriver.perform(Collections.singletonList(longPressSequence));
    }
    
	
	@AfterClass
	public void tearDown() {
		androidDriver.quit();
		service.stop();
	}

}
