package com.phynos.framework.kalin.util;

public class DisposeUtil {

	public static void safeClose(java.io.Closeable obj){
		if(obj == null)
			return;
		
		try{
			obj.close();
		}catch(Throwable ex){}
	}
	
	public static void safeClose(java.sql.Statement cmd){
		if(cmd == null)
			return;
		
		try{
			cmd.close();
		}catch(Throwable ex){}
	}
	
	public static void safeClose(java.sql.ResultSet rs){
		if(rs == null)
			return;
		
		try{
			rs.close();
		}catch(Throwable ex){}
	}
	
	public static void safeClose(java.sql.Connection conn){
		if(conn == null)
			return;
		
		try{
			conn.close();
		}catch(Throwable ex){}
	}
}
