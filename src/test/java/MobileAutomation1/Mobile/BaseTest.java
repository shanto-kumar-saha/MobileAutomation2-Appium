package MobileAutomation1.Mobile;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


//I write all the re-usable code in this class
public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws MalformedURLException{
		
		
		        //start the appium server
				service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//shant//.appium//node_modules//appium-uiautomator2-driver//build//index.js"))
						.withIPAddress("127.0.0.1").usingPort(4723).build();
					 service.start(); 
					
				UiAutomator2Options options = new UiAutomator2Options();
				//options.setDeviceName("RahulPhone"); //emulator
				options.setDeviceName("Pixel 5 API 30");// real device
				//choredriver path for chrome execution in Hybrid app
				options.setChromedriverExecutable("C:\\Users\\shant\\eclipse-workspace\\chromedriver_win32\\chromedriver.exe");
				//options.setApp("C:\\Users\\shant\\eclipse-workspace\\Project 1\\Mobile Autimation\\Mobile\\src\\test\\java\\resources\\ApiDemos-debug.apk");
				options.setApp("C:\\Users\\shant\\eclipse-workspace\\Project 1\\GeneralStoreAPK\\src\\test\\java\\resources\\General-Store.apk");
				
				driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options); 
				//global timeouts
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId",((RemoteWebElement)ele).getId(),
						"duration",2000));
	}
	
	public void swipeAction(WebElement ele,String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
			 
			    "direction", direction,
			    "percent", 0.75
			));	
	}
	
	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}
	
	
	
	
	
	//tearDown method use For Driver quite & stop
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
        service.stop();
	}

}
