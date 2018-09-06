package testingHelpers.navigation;

import testingHelpers.util.Common;

/**
 * 锚点辅助类
 * @author wangchao
 *
 */
public class AnchorHelper {
	private ViewAnchor currentAnchor; // 当前锚点
	
	/**
	 * 获取当前锚点
	 * @return 当前锚点
	 */
	public ViewAnchor getCurrentAnchor() {
		return currentAnchor;
	}


	ViewAnchor parentAnchor; // 父锚点
	Filter inFilter; //从父锚点进入当前锚点的操作元素
	ViewAnchor backAnchor;	// 返回上级锚点
	Filter backFilter; // 返回上级锚点的操作元素
	
	
	/**
	 * 加载视图锚点信息
	 * @param elements 视图锚点信息数组  
	 * elements[0] ：锚点路径
	 * elements[1] ：从父视图进入该视图的元素的过滤方式
	 * elements[2] ：过滤元素的条件值
	 * elements[3] ：返回上级视图的名字
	 * elements[4] ：返回上级视图的操作元素过滤方式
	 * elements[5] ：过滤元素的条件值
	 * 
	 * @return 视图锚点信息
	 */
	public static AnchorHelper loadElements(String[] elements){
		
		if (elements.length == 0)
			return null;
		
		AnchorHelper helper = new AnchorHelper(elements);
		return helper;
		
	}
	
	/**
	 * 构造方法
	 * 
	 * @param elements 视图锚点信息数组   
	 * elements[0] ：锚点路径
	 * elements[1] ：从父视图进入该视图的元素的过滤方式
	 * elements[2] ：过滤元素的条件值
	 * elements[3] ：返回上级视图的名字
	 * elements[4] ：返回上级视图的操作元素过滤方式
	 * elements[5] ：过滤元素的条件值
	 */
	private AnchorHelper(String[] elements){

		this.currentAnchor = ViewAnchor.createInstance(elements[0]);
		
				
		this.initInFilter(elements);
		this.initParentAnchor();
		
		
		this.initBackAnchor(elements);
		this.initBackFilter(elements);
		
		if (this.backAnchor != null){
			this.currentAnchor.add(this.backAnchor, this.backFilter);
		}
		
	}
	
	/**
	 * 初始化父锚点
	 */
	private void initParentAnchor() {
		String parentPath = this.currentAnchor.parentPath();
		
		this.parentAnchor = ViewAnchor.createInstance(parentPath);
		
		if (this.parentAnchor != null)
			this.parentAnchor.add(this.currentAnchor, inFilter);
	}

	/**
	 * 初始化从父锚点进入当前锚点的过滤器
	 * @param elements 导航信息数组
	 */
	private void initInFilter(String[] elements){

		if (elements.length > 2){
						
			FilterType ft = FilterType.parse(elements[1]);
			
			this.inFilter = new Filter(ft, elements[2]);
		}
	}
	
	/**
	 * 初始化当前锚点能返回的上级锚点
	 * @param elements 导航信息数组
	 */
	private void initBackAnchor(String[] elements){
		if (elements.length > 3){
			String backPath = Common.EMPTY;
			for (int i = 0; i < this.currentAnchor.getPathNameList().length; i++){
				backPath += this.currentAnchor.getPathNameList()[i] + ViewAnchor.SPLITER;
				if (this.currentAnchor.getPathNameList()[i].equals(elements[3]))
					break;
			}
				
			this.backAnchor = ViewAnchor.createInstance(backPath);
		}
	}
	
	/**
	 * 初始化当前锚点返回上级锚点的元素过滤器
	 * @param elements 导航信息数组
	 */
	private void initBackFilter(String[] elements){
		if (elements.length > 5){
			
			FilterType ft = FilterType.parse(elements[4]);
			
			this.backFilter = new Filter(ft, elements[5]);
		}
	}
	
}
