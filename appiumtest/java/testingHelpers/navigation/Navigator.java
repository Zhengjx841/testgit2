package testingHelpers.navigation;



import org.openqa.selenium.WebDriver;

/**
 * 界面导航器
 * 
 * @author wangchao
 *
 */
public abstract class Navigator {

	protected Object sharedObj; //临时对象

	// 当前界面锚点
	protected ViewAnchor currentAnchor;

	public ViewAnchor getCurrentAnchor() {
		return currentAnchor;
	}

	// 起始界面锚点
	protected ViewAnchor startAnchor;

	/**
	 * 初始化测试项目包含的视图锚点
	 */
	protected abstract void initAnchors();

	WebDriver driver;

	protected Navigator(Object sharedObj, WebDriver driver) {
		this.sharedObj = sharedObj;
		
		initAnchors();

		this.currentAnchor = startAnchor;
		this.driver = driver;
	}

	public boolean moveTo(String anchorName) {

		ViewAnchor newAnchor = ViewAnchor.getAnchorByName(anchorName);

		if (newAnchor != null) {

			int index = moveToSameParentAnchor(newAnchor);

			for (int i = index + 1; i < newAnchor.getPathNameList().length; i++) {
				ViewAnchor anchor = this.currentAnchor.moveTo(newAnchor.getPathNameList()[i], driver);
				if (anchor != null)
					this.currentAnchor = anchor;
			}

			return true;

		}

		return false;
	}

	/**
	 * 从当前锚点移动到与指定锚点最近的锚点上
	 * @param anchor 指定锚点
	 * @return 返回新的锚点在指定锚点的路径上的索引值
	 */
	protected int moveToSameParentAnchor(ViewAnchor anchor) {
		
		int index = getMaxIndexOfSameElement(this.currentAnchor.getPathNameList(), 
				anchor.getPathNameList());

		if (index < this.currentAnchor.getPathNameList().length - 1) {
			for (int i = this.currentAnchor.getPathNameList().length - 2; i >= 0; i--) {
				ViewAnchor va = this.currentAnchor.moveTo(this.currentAnchor.getPathNameList()[i], driver);

				if (va != null) {
					this.currentAnchor = va;
					if (i <= index) {
						index = i;
						break;
					}
				}
			}
		}

		return index;

	}

	private static int getMaxIndexOfSameElement(String[] array1, String[] array2) {

		int length = (array1.length < array2.length) ? array1.length : array2.length;

		for (int i = 0; i < length; i++) {
			if (!array1[i].equals(array2[i]))
				return i - 1;
		}

		return length - 1;
	}

}
