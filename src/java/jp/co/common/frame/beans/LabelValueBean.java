/*
 * File Name： LabelValueBean.java
 * -----------------------------------------------------------
 *　Version      When            Who            Why
 *-----------------------------------------------------------
 *　   1.0		  2015/09/25	   NTS			       新規作成
　*/
package jp.co.common.frame.beans;

/**
 *  区分マスタ Beanオブジェクトのクラス
 * <pre>
 *  共通フィールドのセット・取得メソッドを提供する。
 * </pre>
 * <ul>
 *   <li>区分内容と区分値プロパティを取得</li>
 *   <li>区分内容と区分値プロパティを設定</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class LabelValueBean extends BaseBean {

	private static final long serialVersionUID = -2078576973282468598L;
	
	/** 区分内容 */
	private String label;
	/** 区分値 */
	private String value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}		
}
