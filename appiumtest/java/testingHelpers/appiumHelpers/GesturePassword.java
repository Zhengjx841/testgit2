package testingHelpers.appiumHelpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Rectangle;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

/**
 * 手势密码操作
 * 
 * @author wangchao
 *
 */
public class GesturePassword {

	AppiumDriver<?> driver;
	MobileElement gestureElement;

	int xSize = 3; // 九宫格单行x轴元素个数
	int ySize = 3; // 九宫格单列y轴元素个数

	int x0; // 手势密码中每个单元格的宽度
	int y0; // 手势密码中每个单元格的高度

	/**
	 * 构造方法
	 * 
	 * @param driver
	 *            测试环境
	 * @param gestureElement
	 *            手势密码元素
	 */
	public GesturePassword(AppiumDriver<?> driver, MobileElement gestureElement) {
		this.driver = driver;
		this.gestureElement = gestureElement;

		this.x0 = this.gestureElement.getSize().width / xSize;
		this.y0 = this.gestureElement.getSize().height / ySize;

	}

	/**
	 * 执行手势密码操作
	 * 
	 * @param driver
	 *            测试环境
	 * @param element
	 *            手势密码元素
	 * @param sequence
	 *            手势操作经过的位置序号数组，手势经过的位置序号以1开头
	 */
	public void perform(int... sequence) {

		TouchAction ta = new TouchAction(driver);

		List<Integer> mySequence = new ArrayList<Integer>();
		for (int i : sequence) {
			// 过滤掉错误的手势密码操作位置
			if (isCorrectIndex(i))
				mySequence.add(i);
		}

		// 点击位置序列中的第一个位置
		ta = ta.press(this.gestureElement.getLocation().x + getXOffset(mySequence.get(0)),
				this.gestureElement.getLocation().y + getYOffset(mySequence.get(0)));

		// 存储上一个操作的位置
		int prevIndex = mySequence.get(0);

		for (int i = 1; i < mySequence.size(); i++) {
			// 移动到下一个位置，并更新上一个操作的位置prevIndex的值为当前位置
			if (moveTo(ta, prevIndex, sequence[i])) {
				prevIndex = mySequence.get(i);
			}
		}

		// 在当前位置做一个微小的正向偏移，
		// 避免因为moveTo操作中偏移量为负值而抛出异常的情况发生
		ta.moveTo(1, 1).release().perform();

	}

	/**
	 * 判断位置索引是否正确
	 * @param index 位置索引
	 * @return 位置索引值是否处于合适的范围
	 */
	private boolean isCorrectIndex(int index) {
		if (index > 0 && index <= xSize * ySize)
			return true;

		return false;
	}

	/**
	 * 获取元素的位置及大小信息
	 * 
	 * @param element 元素
	 * @return 元素的位置及大小信息
	 */
	@Deprecated
	public static Rectangle getRectangle(MobileElement element) {

		String[] bounds = element.getCssValue("bounds").split("\\[|\\]|,");
		int x = Integer.parseInt(bounds[0]);
		int y = Integer.parseInt(bounds[1]);
		int width = Integer.parseInt(bounds[2]) - x;
		int height = Integer.parseInt(bounds[3]) - y;

		return new Rectangle(x, y, height, width);
	}

	/**
	 * 获取位置索引对应的x轴偏移量
	 * @param index 位置索引
	 * @return 位置索引对应的x轴偏移量
	 */
	private int getXOffset(int index) {
		return ((index % xSize == 0) ? xSize : index % xSize) * x0 - x0 / 2;
	}

	/**
	 * 获取位置索引对应的y轴偏移量
	 * @param index 位置索引
	 * @return 位置索引对应的y轴偏移量
	 */
	private int getYOffset(int index) {
		return (index / xSize + ((index % xSize == 0) ? 0 : 1)) * y0 - y0 / 2;
	}

	/**
	 * 从一个位置索引移动到另一个位置索引
	 * @param ta 触屏操作
	 * @param startIndex 起始位置索引
	 * @param endIndex 结束位置索引
	 * @return 触屏操作是否成功
	 */
	private boolean moveTo(TouchAction ta, int startIndex, int endIndex) {

		ta.waitAction(500);
		int xoffset = getXOffset(endIndex) - getXOffset(startIndex);
		int yoffset = getYOffset(endIndex) - getYOffset(startIndex);

		ta = ta.moveTo(xoffset, yoffset);

		return true;
	}

}
