package testingHelpers.util;

import java.util.HashMap;

import org.openqa.selenium.remote.RemoteWebDriver;



/**
 * 用户操作
 * @author wangchao
 *
 */
public abstract class Action {

	protected static RemoteWebDriver driver;
	
	/**
	 * 设置当前测试环境
	 * @param driver 测试环境
	 */
	public static void SetDriver(RemoteWebDriver driver){
		Action.driver = driver;
	}
	
	// 上下文参数字典，用来存放操作中用到的参数名和对应的值
	protected static HashMap<String, Object> context = new HashMap<String, Object>();
	
	
	/**
	 * 设置参数名和对应的值
	 * @param name 参数名
	 * @param value 参数值
	 */
	public void setParam(String name, Object value){
		context.put(name, value);
	}
	
	/**
	 * 根据参数名获取对应的值
	 * @param name 参数名
	 * @return 参数名对应的参数值
	 */
	public String getParam(String name){
		return context.get(name).toString();
	}
	
	/**
	 * 执行操作
	 */
	public abstract void perform();
	
	
}
