package testingHelpers.appiumHelpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * ios测试环境对应的MyCapabilities
 * @author wangchao
 *
 */
public class IOSCapabilities extends MyCapabilities {

	@Override
	public AppiumDriver<? extends WebElement> CreateDriver() {
		AppiumDriver<IOSElement> result = null;
		try {
			result = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), this.getDesiredCapabilities());
			result.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	
	public void calendarFormat(String value){
		this.setCapability("calendarFormat", value);
	}
	
	
	
	public void bundleId(String value){
		this.setCapability("bundleId", value);
	}
	
	
	
	public void launchTimeout(int value){
		this.setCapability("launchTimeout", value);
	}
	
	
	
	public void locationServicesEnabled(boolean value){
		this.setCapability("locationServicesEnabled", value);
	}
	
	
	
	public void locationServicesAuthorized(boolean value){
		this.setCapability("locationServicesAuthorized", value);
	}
	
	
	public void autoAcceptAlerts(boolean value){
		this.setCapability("autoAcceptAlerts", value);
	}
	
	
	public void autoDismissAlerts(boolean value){
		this.setCapability("autoDismissAlerts", value);
	}
	
	
	public void nativeInstrumentsLib(boolean value){
		this.setCapability("nativeInstrumentsLib", value);
	}
	
	
	
	public void nativeWebTap(boolean value){
		this.setCapability("nativeWebTap", value);
	}
	
	
	public void safariInitialUrl(String value){
		this.setCapability("safariInitialUrl", value);
	}
	
	
	public void safariAllowPopups(boolean value){
		this.setCapability("safariAllowPopups", value);
	}
	
	
	public void safariIgnoreFraudWarning(boolean value){
		this.setCapability("safariIgnoreFraudWarning", value);
	}
	
	
	public void safariOpenLinksInBackground(boolean value){
		this.setCapability("safariOpenLinksInBackground", value);
	}
	
	
	public void keepKeyChains(boolean value){
		this.setCapability("keepKeyChains", value);
	}
	
	
	public void localizableStringsDir(String value){
		this.setCapability("localizableStringsDir", value);
	}
	
	
	
	public void processArguments(String value){
		this.setCapability("processArguments", value);
	}
	
	
	public void interKeyDelay(int value){
		this.setCapability("interKeyDelay", value);
	}
	
	
	public void showIOSLog(boolean value){
		this.setCapability("showIOSLog", value);
	}
	
	
	
	public void sendKeyStrategy(String value){
		this.setCapability("sendKeyStrategy", value);;
	}
	
	
	
	public void screenshotWaitTimeout(int value){
		this.setCapability("screenshotWaitTimeout", value);
	}
	
	
	
	public void waitForAppScript(boolean value){
		this.setCapability("waitForAppScript", value);
	}
	
	
	public void webviewConnectRetries(int value){
		this.setCapability("webviewConnectRetries", value);
	}
	
	
	public void appName(String value){
		this.setCapability("appName", value);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
