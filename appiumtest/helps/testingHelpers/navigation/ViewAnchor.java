package testingHelpers.navigation;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testingHelpers.util.Common;
import testingHelpers.util.StringBuilder;


/**
 * 界面视图锚点定位
 * 进入直接导航界面，默认采用点击操作方式
 * 如果想采用其它方式，可继承本类，然后重载moveTo方法
 * @author wangchao
 *
 */
public class ViewAnchor {

	// 路径各部分之间的分隔符
	public final static char SPLITER = '/';
	
	// 存储所有视图锚点
	protected static List<ViewAnchor> allAnchors = new ArrayList<>();

	/**
	 * 根据视图锚点名称查找锚点
	 * @param anchorName 视图锚点名称
	 * @return 视图锚点
	 */
	public static ViewAnchor getAnchorByName(String anchorName){
		
		for(ViewAnchor anchor : allAnchors){
			if (anchor.name.equals(anchorName))
				return anchor;
		}
		
		return null;
	}
	

	
	/**
	 * 根据视图锚点路径查找锚点
	 * @param path 视图锚点路径
	 * @return 视图锚点
	 */
	public static ViewAnchor getAnchorByPath(String path){

		for(ViewAnchor anchor : allAnchors){
			if (foramtPath(path).equals(anchor.path))
				return anchor;
		}
		
		return null;
	}
	
	/**
	 * 格式化路径，使其更规范
	 * @param path
	 * @return
	 */
	private static String foramtPath(String path){
		String result = path;
		
		if (result.charAt(0) == SPLITER)
			result = result.substring(1);
		
		if (result.charAt(result.length() - 1) == SPLITER)
			result = result.substring(0, result.length() - 1);
		
		return result;
	}
	
	
	private String name;

	/**
	 * 获取界面视图名称
	 * @return 界面视图名称
	 */
	public String getName() {
		return name;
	}
	
	//锚点对应的路径,起始路径从起始锚点开始,锚点之间用/分割
	private String path;
	
	
	/**
	 * 获取锚点对应的路径
	 * @return 当前锚点对应的路径
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * 获取父锚点的路径
	 * @return 父锚点的路径
	 */
	public String parentPath(){
		int index = this.path.lastIndexOf(String.valueOf(SPLITER));
		
		if (index > 0)
			return this.path.substring(0, index);
		else
			return null;
	}

	
	String[] pathNameList;
	
	/**
	 * 获取锚点路径对应的锚点名称数组
	 * @return 锚点路径对应的锚点名称数组
	 */
	public String[] getPathNameList(){
		return pathNameList;
	}

	/**
	 * 构造方法
	 * @param path	锚点对应的路径,起始路径从起始锚点开始,锚点之间用/分割
	 */
	private ViewAnchor(String path){

		this.path = foramtPath(path);		
			
		pathNameList = this.path.split(String.valueOf(SPLITER));
		
		this.name = pathNameList[pathNameList.length - 1];
		
		allAnchors.add(this);
	}
	

	/**
	 * 构造方法
	 * @param path	锚点对应的路径,起始路径从起始锚点开始,锚点之间用/分割
	 */
	public static ViewAnchor createInstance(String path){

		if (Common.isNullOrEmpty(path) || path.trim().equals(String.valueOf(SPLITER)))
			return null;
		
		ViewAnchor anchor = ViewAnchor.getAnchorByPath(path);
		
		if (anchor != null)
			return anchor;
		
		return new ViewAnchor(path);
		
	}
	

	// 导航锚点集合，用来存储能够从当前锚点直接导航进入的视图锚点
	protected Map<ViewAnchor, Filter> navAnchorMap = new ConcurrentHashMap<>(); 

	/**
	 * 添加导航锚点
	 * @param navViewAnchor 直接导航到的锚点
	 * @param navAnchorFilter 出发导航的元素信息
	 */
	public void add(ViewAnchor navViewAnchor, Filter navAnchorFilter){
		this.navAnchorMap.put(navViewAnchor, navAnchorFilter);
	}
	
	/**
	 * 获取当前视图锚点的导航结构树文本视图
	 * @return 当前视图锚点的导航结构树
	 */
	public String navAnchorMapStructure(){
		List<ViewAnchor> readedList = new ArrayList<>();		
		return navAnchorMapStructure(this, 0, readedList);
	}
	
	/**
	 * 构建给定锚点的导航结构树文本视图
	 * @param anchor 给定锚点
	 * @param x	下一行文本偏移量
	 * @param readedList 已经访问过的锚点列表
	 * @return 给定锚点的导航结构树文本视图
	 */
	private static String navAnchorMapStructure(ViewAnchor anchor, int x, List<ViewAnchor> readedList){

		StringBuilder sb = new StringBuilder();
				
		sb.appendLine(anchor.path);
		readedList.add(anchor);
			
		
		for (ViewAnchor temp : anchor.navAnchorMap.keySet()){
			
			sb.appendSpace(x + 2);
			sb.appendLine("|");
			sb.appendSpace(x + 3);
			sb.append("--");				

			if (readedList.contains(temp))
				sb.appendLine(temp.path);
			else
				sb.append(navAnchorMapStructure(temp, x + 6, readedList));
		}
		
		return sb.toString();
	}
	
	/**
	 * 根据锚点名查找可直接导航的锚点
	 * @param anchorName 锚点名
	 * @return 锚点名对应的锚点
	 */
	private ViewAnchor getNavAnchor(String anchorName){

		for(ViewAnchor va : navAnchorMap.keySet()){
			if (va.name.equals(anchorName))
				return va;
			
		}
		
		return null;
	}
	
	/**
	 * 转到视图描点为给定名称的视图界面上
	 * 注意此方法只能用于从当前视图界面能够直接跳转到的新界面上，
	 * 而不能用于跨视图界面的跳转
	 * @param name	给定的视图锚点名称
	 * @param driver	测试的webdriver环境
	 * @return	转到的新界面视图锚点
	 */
	protected ViewAnchor moveTo(String name, WebDriver driver){
		
		ViewAnchor va = getNavAnchor(name);

		Filter myFilter = navAnchorMap.get(va);
		
		if (myFilter == null)
			return null;
		
		WebElement element = myFilter.filterElement(driver);

		if (element != null) {
			element.click();
			return va;
		}

		return null;
	}

	/**
	 * 检查当前视图锚点是否为起始界面锚点
	 * @return 当前视图锚点为起始界面锚点，返回true，否则返回false
	 */
	public boolean isHeadAnchor(){
		if (this.name == this.path)
			return true;
		
		return false;
	}
}
