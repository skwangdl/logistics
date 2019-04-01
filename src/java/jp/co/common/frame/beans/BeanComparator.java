package jp.co.common.frame.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Comparator;

import jp.co.common.frame.util.StringBaseUtil;


@SuppressWarnings("rawtypes")
public class BeanComparator implements Comparator {
	
	public BeanComparator (String key, String isAsc) {
		this.isAsc = isAsc;
		this.key = key;
	}
	
	private String isAsc;
	
	private String key;
	@Override
	public int compare(Object bean1, Object bean2) {
		// TODO Auto-generated method stub
		
		try {
			Class clazz = bean1.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(key, clazz);
			Method getMethod = pd.getReadMethod();
			String value1 = (String) getMethod.invoke(bean1);
			if (value1 == null) {
				value1 = "";
			}
			int length1 = value1.length();
			
			String value2 = (String) getMethod.invoke(bean2);
			if (value2 == null) {
				value2 = "";
			}
			int length2 = value2.length();
			
			if(length1 > length2){
				value2 = StringBaseUtil.zeroPadding(value2, length1);
			} else {
				value1 = StringBaseUtil.zeroPadding(value1, length2);
			}
			
			return "desc".equals(isAsc) ? value2.compareTo(value1) : value1.compareTo(value2);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

}
