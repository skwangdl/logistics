/*
 * プラスロジスティクス株式会社
 * 新配送管理システム
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * <DT><B>[クラス名]</B><UL>
 *    Commons BeanUtils ラッパークラス
 * </UL><P>
 * <DT><B>[説明]</B>
 * <UL>
 *    Commons BeanUtils ラッパークラス。
 *    とりあえず必要そうなもののみ実装。
 *    オブジェクト間のプロパティのコピーには必ず問うクラスを使用する。
 *    プロパティのコピー中に発生した例外は全てキャッチするかどうかは検討
 * </UL><P>
 * <DT><B>[更新履歴]</B><UL>
 * <LI>2006/08/21 1.0 Cocom H.Yatake 新規作成
 * </UL><P>
 * @since JDK1.5.0
 */
public class CCMWrappedBeanUtils {

	
	/**
	 * インスタンス化を抑止したりします。
	 */
	private CCMWrappedBeanUtils() {

	}

	/**
	 * オブジェクトプロパティコピー
	 * 第2引数から第1引数にコピーする。
	 * @param arg0 出力オブジェクト
	 * @param arg1 入力オブジェクト
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object arg0, Object arg1) throws IllegalAccessException,
			InvocationTargetException {

		// プロパティリスト取得
		if(arg1 == null || arg0 == null) {
			return;
		}
		PropertyDescriptor descriptor[] = PropertyUtils.getPropertyDescriptors(arg1);

		// プロパティ分ループして値を転送する。
		// java.lang.String -> java.sql.Timestamp  と
		// java.lang.String -> java.math.BigDecimal　の転送の場合

		// 転送元がNullの場合に変換エラーが出てしまうので
		// その例外はスキップするようにする。
		// 【注意】
		// java.sql.Timestamp -> java.lang.String と
		// java.math.BigDecimal-> java.laong.String　の場合はNullになる。
		//Object objValue = null;
		for (int i = 0; i < descriptor.length; i++) {

			try {
				//objValue = BeanUtils.getProperty(arg1, descriptor[i].getName());

				String key = descriptor[i].getName();

				if ("class".equals(key)) {
					continue;
				}

				Object value = BeanUtilsBean.getInstance().getPropertyUtils().getSimpleProperty(arg1, key);

				if (value instanceof Map) {

					Iterator names = ((Map) value).keySet().iterator();
					while (names.hasNext()) {
						String name = (String) names.next();

						if(PropertyUtils.isWriteable(arg0, name)) {
							Object mapValue = ((Map) value).get(name);
							BeanUtils.copyProperty(arg0, key, mapValue);
						}
					}

				} else {

					if(PropertyUtils.isWriteable(arg0, key)) {
						BeanUtils.copyProperty(arg0, key, value);
					}
				}

			} catch (ConversionException e) {
				// 全プロパティ分処理したいので処理続行
				continue;

			} catch (NoSuchMethodException e) {
				// 全プロパティ分処理したいので処理続行
				continue;
			}

		}

	}

	/**
	 * 名称を指定してオブジェクトのプロパティコピー
	 * @param arg0 出力オブジェクト
	 * @param arg1 コピー対象のプロパティ名
	 * @param arg2 入力オブジェクト
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperty(Object arg0, String arg1, Object arg2) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtils.copyProperty(arg0, arg1, arg2);
	}

	/**
	 * オブジェクトプロパティコピー
	 * 第2引数のMapを第1引数のBeanに変換する。
	 * @param arg0 出力オブジェクト
	 * @param arg1 入力オブジェクト
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object arg0, Map arg1) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.populate(arg0, arg1);

	}

	/**
	 * Beanのクローン作成
	 * @param arg0
	 * @return Object arg0のクローンとして作成した別オブジェクト
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object cloneBean(Object arg0) throws IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException {
		return BeanUtils.cloneBean(arg0);

	}

	/**
	 * 第２引数のListオブジェクトの各要素を第１引数のVOにコピーし、
	 * 新しいListを返す。
	 * @param className
	 * @param copyFromList
	 * @return List
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	public static List copyList(Class clazz, List copyFromList) throws IllegalAccessException, InvocationTargetException, InstantiationException {

		List<Object> rtnList = new ArrayList<Object>();

		int rowCnt = 0;

		if (copyFromList != null) {

			rowCnt = copyFromList.size();

			for (int i = 0; i < rowCnt; i++) {

				Object rtnClass = clazz.newInstance();

				copyProperties(rtnClass, copyFromList.get(i));

				rtnList.add(rtnClass);

			}

		}
		
		

		return rtnList;

	}
	
    /**
     * プロパティ設定
     * @param bean 設定対象のBean名
     * @param name 設定対象のプロパティ名
     * @param value 設定値
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see BeanUtils#setProperty
     */
    public static void setProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

    	BeanUtils.setProperty(bean, name, value);
    }
	
    /**
     * プロパティ取得
     * @param bean 設定対象のBean名
     * @param name 設定対象のプロパティ名
     * @return String 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @see BeanUtilsBean#getProperty
     */
    public static String getProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtils.getProperty(bean, name);

    }
	
	

}
