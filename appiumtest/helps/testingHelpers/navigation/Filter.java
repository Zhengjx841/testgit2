package testingHelpers.navigation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * 元素过滤器
 * @author wangchao
 *
 */
public class Filter {

	// 过滤类型
	private FilterType filterType;
	
	// 过滤条件
	private String value;
	
	/**
	 * 构造方法
	 * @param ft 过滤类型
	 * @param value 过滤条件
	 */
	public Filter(FilterType ft, String value){
		this.filterType = ft;
		this.value = value;
	}
	
	/**
	 * 过滤元素
	 * @param driver 测试环境
	 * @return 过滤出的元素
	 */
	public WebElement filterElement(WebDriver driver){
		try {
			Method filterMethod = RemoteWebDriver.class.getDeclaredMethod(
					"findElement", String.class, String.class
					);
			filterMethod.setAccessible(true);
			return (WebElement) filterMethod.invoke((RemoteWebDriver)driver, filterType.getDescription(), value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 过滤元素列表
	 * @param driver 测试环境
	 * @return 过滤出的元素列表
	 */
	public List<WebElement> filterElements(WebDriver driver){		
		try {
			Method filterMethod = RemoteWebDriver.class.getDeclaredMethod(
					"findElements", String.class, String.class
					);
			filterMethod.setAccessible(true);
			return (List<WebElement>) filterMethod.invoke((RemoteWebDriver)driver, filterType.getDescription(), value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
//	private By getBy(){
//		switch (filterType) {
//		case ById:
//			return By.id(value);
//		case ByLinkText:
//			return By.linkText(value);
//		case ByPartialLinkText:
//			return By.partialLinkText(value);
//		case ByTagName:
//			return By.tagName(value);
//		case ByName:
//			return By.name(value);
//		case ByClassName:
//			return By.className(value);
//		case ByCssSelector:
//			return By.cssSelector(value);
//		case ByXPath:
//			return By.xpath(value);
//		case ByAccessibilityId:
//			
//		default:
//			return null;
//		}
//	}
	
}
