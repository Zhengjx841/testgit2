package testingHelpers.navigation;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.openqa.selenium.WebDriver;

import testingHelpers.util.Common;


/**
 * 以文本文件方式定义的导航器
 * 文本文件为当前项目所在目录下config子文件夹下，文件名默认为navigator.txt的文件
 * @author wangchao
 *
 */
public class TextNavigator extends Navigator {

	private final static String CONFIG_DIRECTORY_NAME = "config";
	
	
	
	
		
	// 配置文件中的元素分隔符
	private static String SPLITER = " |\t";
	
	/**
	 * 设置配置文件描述内容的分隔符
	 * @param spliter
	 */
	public static void setSpliter(String spliter){
		if (!Common.isNullOrEmpty(spliter))
			TextNavigator.SPLITER = spliter;
	}
	
	/**
	 * 构造方法
	 * @param driver 测试环境
	 */
	public TextNavigator(WebDriver driver) {

		this("navigator.txt", driver);
		
		// TODO Auto-generated constructor stub
		
	}
	
	/**
	 * 构造方法
	 * @param configFileName 导航配置文件名称
	 * @param driver 测试环境
	 */
	public TextNavigator(String configFileName, WebDriver driver){
		super(configFileName, driver);
		
	}
	


	/**
	 * 判断当前文本行是否为注释行
	 * 注意：注释行以#字符开头
	 * @param text 文本行
	 * @return 当前文本行是否为注释行
	 */
	private static boolean isComment(String text){
		if (text.charAt(0) == Common.SHARP)
			return true;
		
		return false;
	}
	
	@Override
	protected void initAnchors() {
		
		File classpathRoot = new File(Common.currentPath());
		File testConfigDir = new File(classpathRoot, CONFIG_DIRECTORY_NAME);
		File navigatorInfo = new File(testConfigDir, this.sharedObj.toString());
		
		
		// TODO Auto-generated method stub
		if (! navigatorInfo.exists())
			return;


		FileReader fr;
		try {
			fr = new FileReader(navigatorInfo);
			BufferedReader br = new BufferedReader(fr);
			
			String temp = br.readLine();
			
			while (temp != null){
				if (!isComment(temp)){					
					AnchorHelper ah = AnchorHelper.loadElements(temp.split(TextNavigator.SPLITER));

					if (ah.getCurrentAnchor().isHeadAnchor())
						this.startAnchor = ah.getCurrentAnchor();
				}
				
				temp = br.readLine();
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
