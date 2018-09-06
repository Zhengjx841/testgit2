package testingHelpers.appiumHelpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * 对应于Android测试环境的MyCapabilities
 * @author wangchao
 *
 */
public class AndroidCapabilities extends MyCapabilities {

	@Override
	public AppiumDriver<? extends WebElement> CreateDriver() {
		// TODO Auto-generated method stub
		AppiumDriver<AndroidElement> result = null;
		try {
			result = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), this.getDesiredCapabilities());
			result.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public AndroidCapabilities(){
		devideName("Android Emulator");
	    unicodeKeyboard(true); 
	    resetKeyboard(true); 
	}
	
	
	public void appActivity(String value){
		this.setCapability("appActivity", value);
	}
	
	public void appPackage(String value){
		this.setCapability("appPackage", value);
	}
	
	
	public void appWaitActivity(String value){
		this.setCapability("appWaitActivity", value);
	}
	
	
	public void appWaitPackage(String value){
		this.setCapability("appWaitPackage", value);
	}
	
	
	public void appWaitDuration(int value){
		this.setCapability("appWaitDuration", value);
	}
	
	
	public void deviceReadyTimeout(int value){
		this.setCapability("deviceReadyTimeout", value);
	}
	
	
	public void androidCoverage(String value){
		this.setCapability("androidCoverage", value);
	}
	
	
	public void enablePerformanceLogging(boolean value){
		this.setCapability("enablePerformanceLogging", value);
	}
	
	public void androidDeviceReadyTimeout(int value){
		this.setCapability("androidDeviceReadyTimeout", value);
	}
	
	
	public void androidInstallTimeout(int value){
		this.setCapability("androidInstallTimeout", value);
	}
	
	
	public void adbPort(int value){
		this.setCapability("adbPort", value);
	}
	
	
	public void androidDeviceSocket(String value){
		this.setCapability("androidDeviceSocket", value);
	}
	
	
	public void avd(String value){
		this.setCapability("avd", value);
	}

	public void avdReadyTimeout(int value){
		this.setCapability("avdReadyTimeout", value);
	}

	
	public void avdLaunchTimeout(int value){
		this.setCapability("avdLaunchTimeout", value);
	}
	
	
	public void avdArgs(String value){
		this.setCapability("avdArgs", value);
	}
	
	
	
	public void useKeystore(boolean value){
		this.setCapability("useKeystore", value);
	}
	
	
	public void keystorePath(String value){
		this.setCapability("keystorePath", value);
	}
	
	
	
	public void keystorePassword(String value){
		this.setCapability("keystorePassword", value);
	}
	
	
	public void keyAlias(String value){
		this.setCapability("keyAlias", value);
	}
	
	
	public void keyPassword(String value){
		this.setCapability("keyPassword", value);
	}
	
	
	
	public void chromedriverExecutable(String value){
		this.setCapability("chromedriverExecutable", value);
	}
	
	
	
	public void autoWebviewTimeout(int value){
		this.setCapability("autoWebviewTimeout", value);
	}
	
	
	
	public void intentAction(String value){
		this.setCapability("intentAction", value);
	}
	
	
	
	public void intentCategory(String value){
		this.setCapability("intentCategory", value);
	}
	
	public void intentFlags(int value){
		this.setCapability("intentFlags", value);
	}
	
	
	
	public void optionalIntentArguments(String value){
		this.setCapability("optionalIntentArguments", value);
	}
	
	
	public void dontStopAppOnReset(boolean value){
		this.setCapability("dontStopAppOnReset", value);
	}
	
	
	
	public void unicodeKeyboard(boolean value){
		this.setCapability("unicodeKeyboard", value);
	}
	
	
	
	public void resetKeyboard(boolean value){
		this.setCapability("resetKeyboard", value);
	}
	
	
	
	public void noSign(boolean value){
		this.setCapability("noSign", value);
	}
	
	
	
	public void ignoreUnimportantViews(boolean value){
		this.setCapability("ignoreUnimportantViews", value);
	}
	
	
	public void disableAndroidWatchers(boolean value){
		this.setCapability("disableAndroidWatchers", value);
	}
	
	
	
	public void chromeOptions(String value){
		this.setCapability("chromeOptions", value);
	}
	
	
	public void recreateChromeDriverSessions(boolean value){
		this.setCapability("recreateChromeDriverSessions", value);
	}
	
	
	
	public void nativeWebScreenshot(boolean value){
		this.setCapability("nativeWebScreenshot", value);
	}
	
	
	public void androidScreenshotPath(String value){
		this.setCapability("androidScreenshotPath", value);
	}
	
	
	
	
	
	
	
	
	
	
}
