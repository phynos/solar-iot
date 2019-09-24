package com.phynos.framework.kalin.dal;


import com.phynos.framework.kalin.util.DisposeUtil;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBFactory {

	public enum DatabaseTypeEnum {
		MySQL, Oracle
	}
	
	private static DataSource ds = null;
	private static DatabaseTypeEnum databaseType;
	
	private static String fixMethodName(String methodName){
		String result = methodName.replace("set", "");
		
		String str1 = "" + result.charAt(0);
		String str2 = result.substring(1);
		
		return str1.toLowerCase() + str2;
	}
	
	private static DataSource getDataSource(Properties ps) throws Exception {
		
		String dsClass = "org.apache.commons.dbcp.BasicDataSource";
		if(ps.getProperty("dataSourceClass") != null)
			dsClass = ps.getProperty("dataSourceClass");
		Class<? extends Object> cls = Class.forName(dsClass);		
		
		DataSource ds = (DataSource)cls.newInstance();
		Method[] methods = cls.getDeclaredMethods();
		for(Method method : methods){
			
			if(Modifier.isPublic(method.getModifiers()) == false)
				continue;
			
			String name = method.getName();
			if(name.startsWith("set") == false)
				continue;
			
			name = fixMethodName(name);
			String strValue = ps.getProperty(name);
			if(strValue == null)
				continue;
			
			strValue = strValue.trim();
			
			Class<? extends Object>[] paramClses = method.getParameterTypes();
			if(paramClses.length != 1)
				continue;
			
			Class<? extends Object> paramCls = paramClses[0];
			if(paramCls.getName().equals("int")){
				method.invoke(ds, Integer.parseInt(strValue));
			}else if(paramCls.getName().equals("java.lang.String")){
				method.invoke(ds, strValue);
			}
		}
		
		return ds;
	}
	
	public static boolean init(){
		InputStream in = null;
		try {
			in = (DBFactory.class.getClassLoader()
					.getResourceAsStream("jdbc.properties"));
			
			if(in == null){
				in = (DBFactory.class.getClassLoader()
						.getResourceAsStream("config.properties"));
			}
						
			if (in == null) {
				throw new FileNotFoundException("配置文件jdbc.properties未找到");
			}
			
			Properties props = new Properties();
			props.load(in);
			in.close();
			
			ds = getDataSource(props);
			
			String database = props.getProperty("database");
			if("mysql".equals(database))
				databaseType = DatabaseTypeEnum.MySQL;
			else if("oracle".equals(database))
				databaseType = DatabaseTypeEnum.Oracle;
			else
				throw new Exception("Property 'database' invalid or not defined.");
			
			return true;
		} catch (Exception e) {			
			return false;
		}finally{
			DisposeUtil.safeClose(in);
		}
	}
	
	static {
		init();
	}
	
	public static void setDataSource(DataSource value){
		ds = value;
	}
	
	public static DatabaseTypeEnum getDatabaseType(){
		return databaseType;
	}
	
	public static Connection newConnection() throws SQLException {
		return ds.getConnection();
	}
}
