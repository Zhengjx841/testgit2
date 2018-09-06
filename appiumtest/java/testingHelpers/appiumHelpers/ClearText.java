package testingHelpers.appiumHelpers;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * 清除文本框的内容
 * 用于android测试环境下清除密码框的值
 * @author wangchao
 *
 */
public class ClearText {
	
	/**
	 * 清空密码框文本
	 * @param element 密码框元素
	 * @param pwdLength 密码长度
	 * @param driver 测试环境
	 */
	public static void clearPassword(AndroidElement element, int pwdLength, AndroidDriver<?> driver){
		String text = element.getAttribute("text");
		System.out.println(text);
		element.click();
		
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_MOVE_END);
		
		for (int i = 0; i < pwdLength; i++){
			driver.pressKeyCode(AndroidKeyCode.DEL);
		}
	}
	
	/**
	 * 清空密码长度小于等于20的密码框文本
	 * @param element 密码框元素
	 * @param driver 测试环境
	 */
	public static void clearPassword(AndroidElement element, AndroidDriver<?> driver){
		clearPassword(element, 20, driver);
	}
}
