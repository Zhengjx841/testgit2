package testingHelpers.appiumHelpers;

import io.appium.java_client.AppiumDriver;

/**
 * 滑屏操作
 * @author tester
 *
 */
public class Swiper {

	/**
	 * 向上滑屏
	 * 滑动距离为1/5屏幕高度
	 * @param driver 测试环境
	 */
	public static void toUp(AppiumDriver<?> driver){
		   int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();
	        
	        driver.swipe(width / 2, height * 2 / 5, width / 2, height / 5, 2000);
	}
	
	/**
	 * 向下滑屏
	 * 活动距离为1/5屏幕高度
	 * @param driver 测试环境
	 */
	public static void toDown(AppiumDriver<?> driver){

		   int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();
	        
	        driver.swipe(width / 2, height  / 5, width / 2, height * 2 / 5, 2000);
	}
	
	/**
	 * 向左边滑屏
	 * 滑动距离为1/4屏幕宽度
	 * @param driver 测试环境
	 */
	public static void toLeft(AppiumDriver<?> driver){

		   int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();
	        
	        driver.swipe(width / 2, height  * 2  / 5, width / 4, height * 2 / 5, 2000);
	}
	
	/**
	 * 向右滑屏
	 * 滑动距离为1/4屏幕宽度
	 * @param driver 测试环境
	 */
	public static void toRight(AppiumDriver<?> driver){

		   int width = driver.manage().window().getSize().getWidth();
	        int height = driver.manage().window().getSize().getHeight();
	        
	        driver.swipe(width / 2, height  * 2  / 5, width * 3 / 4, height * 2 / 5, 2000);
	}
	
}
