package testingHelpers.util;

import java.text.MessageFormat;
import java.util.HashMap;



public class TestData {
	private final static String EXPECTED = "[期待值]"; //期待值标题
	
	private final static String ACTUAL = "[实际值]"; //实际值标题
	
	
	private HashMap<String , Object> data = new HashMap<>();
	
	private Object expected; //期待值
	private Object actual; //实际值
	
	/**
	 * 设置期待值
	 * @param value 期待值
	 */
	public void expected(Object value){
		this.expected = value;
	}
	
	/**
	 * 设置实际值
	 * @param value
	 */
	public void actual(Object value){
		this.actual = value;
	}
	
	/**
	 * 添加输入参数
	 * @param name 输入参数名称
	 * @param value 输入参数值
	 */
	public void add(String name, Object value){
		this.data.put(MessageFormat.format("[{0}]", name), value);
	}
	
	public TestData() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 构造方法
	 * @param names 测试输入参数名称数组
	 * @param values 测试输入参数值数组
	 * @param values 输入本组测试数据后，测试程序期待值
	 */
	public TestData(String[] names, Object[] values, Object expected){
		for (int i = 0; i < names.length; i++){
			this.add(names[i], values[i]);
		}
		
		this.expected = expected;
	}
	
	@Override
	public String toString(){
		StringBuilder strBuilder = new StringBuilder();
		
		StringBuilder keyBuilder = new StringBuilder();
		StringBuilder valueBuilder = new StringBuilder();
		
				
		for (String key : this.data.keySet()){
			String value = (this.data.get(key) == null) ? Common.EMPTY : this.data.get(key).toString();
//			int length = (value.length() >= key.length()) ? value.length() : key.length();
			
			keyBuilder.append(Common.padRight(key, value.length() + key.length()));
			keyBuilder.append(Common.TAB);
			
			valueBuilder.append(Common.padRight(value, value.length() + key.length()));
			valueBuilder.append(Common.TAB);
			
		}
		
		strBuilder.append(keyBuilder);
		strBuilder.append(EXPECTED);
		strBuilder.append(Common.TAB);
		strBuilder.appendLine(ACTUAL);
		
		strBuilder.append(valueBuilder);
		strBuilder.append(this.expected);
		strBuilder.append(Common.TAB);
		strBuilder.appendLine(this.actual);
		
		return strBuilder.toString();
	}
	
	
	/**
	 * 测试数据描述，不包含测试数据变量名的内容
	 * @return 测试数据描述
	 */
	public String toString2(){

		StringBuilder strBuilder = new StringBuilder();
		
		String values = Common.EMPTY;
		
		for (String key : data.keySet()){
			values += data.get(key).toString() + Common.TAB;
		}
		
		strBuilder.appendLine(values);
		
		return strBuilder.toString();
	}
}
