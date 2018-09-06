package com.jinglian.user;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * demo 点击医享家医生端的健康管理页面
 * @author jx
 * */
public class Doctorone extends TestDoctor{
	@Override
	public void test() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("cn.kinglian.dc:id/tab_health_manager")));
		driver.findElement(By.id("cn.kinglian.dc:id/tab_health_manager")).click();
	}
}