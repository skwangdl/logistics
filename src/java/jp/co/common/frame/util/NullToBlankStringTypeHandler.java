package jp.co.common.frame.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.StringTypeHandler;

public class NullToBlankStringTypeHandler extends StringTypeHandler{
	public String getResult(ResultSet arg0, String arg1) throws SQLException {
		if ("LINK_NAME".equals(arg1)) {
			return StringBaseUtil.isEmpty(arg0.getString(arg1)) ? "" : arg0.getString(arg1);
		}
		return StringBaseUtil.nullToBlank(arg0.getString(arg1));
	}
	
	public String getNullableResult(ResultSet arg0, String arg1) throws SQLException {
		return StringBaseUtil.nullToBlank(arg0.getString(arg1));
	}
}