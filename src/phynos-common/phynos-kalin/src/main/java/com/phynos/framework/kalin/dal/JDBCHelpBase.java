package com.phynos.framework.kalin.dal;


import com.phynos.framework.kalin.util.DisposeUtil;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class JDBCHelpBase implements JDBCHelp {

	private static final Map<String, JDBCHelpData> mapSQL = new TreeMap<String, JDBCHelpData>();
	
	protected static final Map<String, String> mapUserDefineDataInsertSqls = new TreeMap<String, String>();
	
	protected abstract String buildInsertSql(JDBCHelpData jdbcHelpData, Class<? extends Object> cls);
	
	protected abstract String buildUpdateSql(JDBCHelpData jdbcHelpData, Class<? extends Object> cls);
	
	protected abstract String buildDeleteSql(JDBCHelpData jdbcHelpData, Class<? extends Object> cls);
	
	protected abstract String buildGetByIdSql(JDBCHelpData jdbcHelpData, Class<? extends Object> cls);
	
	protected abstract String buildUserDefineDataInsertSql(UserDefineTable table, UserDefinePrimaryKey primaryKey, UserDefineColumnData[] columns);
	
	protected void appendListSql(StringBuilder sb, List<Object> list){
		String pre = "(";
		for(Object item : list){
			sb.append(pre);
			
			if(item instanceof String){
				sb.append("'");
				sb.append(((String)item).replaceAll("'", "''"));
				sb.append("'");
			}else if(item instanceof Integer){
				sb.append(((Integer)item).toString());
			}else if(item instanceof Long){
				sb.append(((Long)item).toString());
			}else if(item instanceof Float){
				sb.append(((Float)item).toString());
			}else if(item instanceof Double){
				sb.append(((Double)item).toString());
			}else{
				throw new IllegalArgumentException("不支持的数据类型：" + item.getClass());
			}
			
			pre = ", ";
		}
		sb.append(")");
	}
	
	protected JDBCHelpData getJDBCHelpData(Class<? extends Object> cls){
		JDBCHelpData result = mapSQL.get(cls.getName());
		
		if(result == null){
			JDBCHelpData item = new JDBCHelpData(cls);
			
			item.setInsertSql(this.buildInsertSql(item, cls));
			item.setUpdateSql(this.buildUpdateSql(item, cls));
			item.setDeleteSql(this.buildDeleteSql(item, cls));
			item.setGetByIdSql(this.buildGetByIdSql(item, cls));
			
			mapSQL.put(cls.getName(), item);
			result = item;
		}
		
		return result;
	}
	
	protected Boolean readBoolean(Object obj, Field field){
		try {
			return (Boolean)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected Integer readInteger(Object obj, Field field){
		try {
			return (Integer)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected Long readLong(Object obj, Field field){
		try{
			return (Long)field.get(obj);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	protected Float readFloat(Object obj, Field field){
		try {
			return (Float)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected Double readDouble(Object obj, Field field){
		try {
			return (Double)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected java.util.Date readDateTime(Object obj, Field field){
		try {
			return (java.util.Date)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected String readString(Object obj, Field field){
		try {
			return (String)field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected byte[] readBytes(Object obj, Field field){
		try {
			return (byte[])field.get(obj);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected Object readPrimaryKey(Object entity){
		try{
			JDBCHelpData helpData = this.getJDBCHelpData(entity.getClass());
			return helpData.getPrimaryKey().getField().get(entity);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	protected void setValue(Object obj, Field field, Object value){
		
		try{
			field.set(obj, value);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	protected void buildTable(String sql) throws SQLException {
		Statement stmt = null;
		
		try{
			System.out.println("Build table, sql = \r\n" + sql + "\r\n");
			stmt = DBSession.getStatement();
			stmt.execute(sql);
		}finally{
			DisposeUtil.safeClose(stmt);
		}
	}
	
	protected String getUserDefineDataInsertSql(
			UserDefineTable table, UserDefinePrimaryKey primaryKey, UserDefineColumnData[] columns){
		
		String result = mapUserDefineDataInsertSqls.get(table.getTableName());
		if(result == null){
			result = this.buildUserDefineDataInsertSql(table, primaryKey, columns);
			mapUserDefineDataInsertSqls.put(table.getTableName(), result);
		}
		return result;
	}
	
	@Override
	public String getInsertSql(Class<? extends Object> cls) {
		
		return this.getJDBCHelpData(cls).getInsertSql();
	}

	@Override
	public String getUpdateByIdSql(Class<? extends Object> cls) {
		
		return this.getJDBCHelpData(cls).getUpdateSql();
	}

	@Override
	public String getDeleteByIdSql(Class<? extends Object> cls) {
		
		return this.getJDBCHelpData(cls).getDeleteSql();
	}

	@Override
	public String getGetByIdSql(Class<? extends Object> cls) {
		
		return this.getJDBCHelpData(cls).getGetByIdSql();
	}

	@Override
	public void setIdValueEmpty(Object entity) {
		try{
			PrimaryKeyDefine primaryKey = this.getJDBCHelpData(entity.getClass()).getPrimaryKey();
			primaryKey.getField().set(entity, null);
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
}
