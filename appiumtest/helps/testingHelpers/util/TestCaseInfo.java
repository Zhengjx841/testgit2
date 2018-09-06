package testingHelpers.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试用例文本信息
 * @author wangchao
 *
 */
public class TestCaseInfo {
	
	private final static String CASE_TITLE = "[标题]: ";
	private final static String CASE_AUTHOR = "[作者]: ";
	private final static String PRE_CONDITION = "[前置条件]: ";
	private final static String TEST_STEP = "[测试步骤]: ";
	private final static String POST_CONDITION = "[后置条件]: ";
	private final static String TEST_DATA = "[测试数据]: ";
//	private final static String TEST_RESULT = "[测试结果]: ";
	
	
	private String title; //测试用例标题
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	
	private String author; //测试用例作者
	
	public String getAuthor(){
		return this.author;
	}
	
	public void setAuthor(String value){
		this.author = value;
	}
	
	private int index; //测试用例序号
	
	public int getIndex(){
		return this.index;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	private List<String> steps = new ArrayList<>(); //测试步骤
	
	public void addStep(String value){
		this.steps.add(value);
	}
 	
	private String precondition; //前置条件
	
	public String getPrecondition(){
		return this.precondition;
	}
	
	public void setPrecondition(String value){
		this.precondition = value;
	}
	
	
	private String postcondition; //后置条件
	
	public String getPostcondition(String value){
		return this.postcondition;
	}
	
	public void setPostcondition(String value){
		this.postcondition = value;
	}
		
	
	private List<TestData> testDatas = new ArrayList<>();
	
	
	public void addTestData(TestData value){
		this.testDatas.add(value);
	}
	
	@Override	
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		
		strBuilder.appendLine(MessageFormat.format("{0}{1}", CASE_TITLE, this.title));
		
		strBuilder.appendLine();
		
		strBuilder.appendLine(MessageFormat.format("{0}{1}", CASE_AUTHOR, this.author));
		
		strBuilder.appendLine();
		
		strBuilder.appendLine(PRE_CONDITION);
		strBuilder.appendLine(this.precondition);
		
		strBuilder.appendLine();
		
		strBuilder.appendLine(TEST_STEP);
		for (int i = 0; i < this.steps.size(); i++){
			strBuilder.appendLine(MessageFormat.format("{0}. {1}", i + 1, this.steps.get(i)));
		}
		
		strBuilder.appendLine();
		
		strBuilder.appendLine(POST_CONDITION);
		strBuilder.appendLine(this.postcondition);
		
		strBuilder.appendLine();

		strBuilder.appendLine(TEST_DATA);
		for (TestData tData : this.testDatas){
			strBuilder.appendLine(tData);
			strBuilder.appendLine();
		}
		
		strBuilder.appendLine();
		
//		strBuilder.appendLine(TEST_RESULT);
		
		return strBuilder.toString();
	}
}
