package testingHelpers.util;

/**
 * 字符串连接类
 * StringBuffer类的扩展
 * @author wangchao
 *
 */
public class StringBuilder {
	StringBuffer sb = new StringBuffer();
	
	/**
	 * 在当前字符串后面添加新的字符串
	 * @param value 要添加的字符串
	 */
	public <T> void append(T value){
		if (value == null)
			sb.append(Common.EMPTY);
		else			
			sb.append(value);
	}
	
	/**
	 * 在当前字符串后面添加换行符
	 */
	public void appendLine(){
		this.append(Common.newLine());
	}
	
	/**
	 * 在当前字符串后面添加新的字符串，然后添加换行符
	 * @param value 要添加的字符串
	 */
	public <T> void appendLine(T value){
		this.append(value);
		this.appendLine();
	}
	
	/**
	 * 添加空格
	 * @param count 添加空格个数
	 */
	public void appendSpace(int count){
		for (int i = 0; i < count; i++){
			this.append(Common.SPACE);
		}
	}
	
	
	@Override
	public String toString(){
		return sb.toString();
	}
	
}
