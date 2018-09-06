package testingHelpers.appiumHelpers;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.appium.java_client.AppiumDriver;
import testingHelpers.util.Action;
import testingHelpers.util.Common;

/**
 * 测试用例基类
 * 测试用例类可直接继承本类，简化测试用例编写
 * 注意安装文件一定要放到当前程序目录的apps文件夹下
 * @author wangchao
 *
 */
public abstract class BaseTestCase {


	/**
	 * 获取app安装文件名
	 * 注意安装文件一定要放到当前程序目录的apps文件夹下
	 * @return app安装文件名
	 */
	protected abstract String getAppFileName();
	
	
	protected AppiumDriver<?> driver;
	
	
	@Before
	public void setUp() throws Exception {
		MyCapabilities capabilities = new AndroidCapabilities();
		capabilities.app(Common.getAppPath(this.getAppFileName()));
		driver = capabilities.CreateDriver();
		Action.SetDriver(driver);
	}

	@After
	public void tearDown() throws Exception {
		
		driver.closeApp();
		driver.quit();
	}

	@Test
	public abstract void test();
	


}
