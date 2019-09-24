package com.phynos.framework.kalin.dal;


import com.phynos.framework.kalin.util.DisposeUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class DaoBase {

	protected void saveEntity(Object entity) throws SQLException {
		
		JDBCHelp help = JDBCHelpFactory.getJDBCHelp();
		
		String sql = help.getInsertSql(entity.getClass());
		
		PreparedStatement cmd = null;
		java.util.List<java.io.InputStream> lstIs = new LinkedList<java.io.InputStream>();
		try{
			cmd = DBSession.getPreparedStatement(sql);
			help.setParameterForInsert(cmd, entity, lstIs);
			cmd.execute();
			help.setIdValueAfterInsert(entity);
		}finally{
			DisposeUtil.safeClose(cmd);
			for(java.io.InputStream is : lstIs)
				DisposeUtil.safeClose(is);
		}
	}

	protected void updateEntity(Object entity) throws SQLException {
		JDBCHelp help = JDBCHelpFactory.getJDBCHelp();

		String sql = help.getUpdateByIdSql(entity.getClass());

		PreparedStatement cmd = null;
		java.util.List<java.io.InputStream> lstIs = new LinkedList<java.io.InputStream>();
		try{
			cmd = DBSession.getPreparedStatement(sql);
			help.setParameterForUpdate(cmd, entity, lstIs);
			cmd.execute();
		}finally{
			DisposeUtil.safeClose(cmd);
			for(java.io.InputStream is : lstIs)
				DisposeUtil.safeClose(is);
		}
	}
	
	protected void deleteEntity(Object entity) throws SQLException {
		JDBCHelp help = JDBCHelpFactory.getJDBCHelp();
		
		String sql = help.getDeleteByIdSql(entity.getClass());
		PreparedStatement cmd = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			help.setParameterForDeleteEntity(cmd, entity);
			cmd.execute();
			
			help.setIdValueEmpty(entity);
		}finally{
			DisposeUtil.safeClose(cmd);
		}
	}
	
	protected void deleteData(Class<? extends Object> cls, Object id) throws SQLException {
		JDBCHelp help = JDBCHelpFactory.getJDBCHelp();
		
		String sql = help.getDeleteByIdSql(cls);
		PreparedStatement cmd = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			help.setParameterForDeleteData(cmd, cls, id);
			cmd.execute();
		}finally{
			DisposeUtil.safeClose(cmd);
		}
	}
	
	protected Object getById(Class<? extends Object> cls, Object id) throws SQLException {
		if(id == null)
			return null;
		
		Object result = null;
		
		JDBCHelp help = JDBCHelpFactory.getJDBCHelp();
		String sql = help.getGetByIdSql(cls);
		
		PreparedStatement cmd = null;
		ResultSet rs = null;
				
		try{
			cmd = DBSession.getPreparedStatement(sql);
			help.setParameterForGetById(cmd, cls, id);
			rs = cmd.executeQuery();
			
			if(rs.next()){
				Object entity = cls.newInstance();
				help.readFields(rs, entity);
				
				result = entity;
			}
		}catch(IllegalAccessException ex1){
			ex1.printStackTrace();
		}catch(InstantiationException ex2){
			ex2.printStackTrace();
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return result;
	}
	
	protected Object searchUnique(Class<? extends Object> cls, SearchOption[] sos)
			throws SQLException {
		
		Object result = null;
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		String sql = jdbcHelp.getSearchOptionSql(cls, sos);
		PreparedStatement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			jdbcHelp.setParameterForSearchOption(cmd, sos);
			rs = cmd.executeQuery();
			if(rs.next()){
				try {
					Object entity = cls.newInstance();
					jdbcHelp.readFields(rs, entity);
					
					result = entity;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return result;
	}
	
	protected Collection<? extends Object> searchCollection(
			Class<? extends Object> cls, SearchOption[] sos, OrderOption[] oos)
					throws SQLException {
		
		Collection<Object> result = new LinkedList<Object>();
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		
		if(sos == null)
			sos = new SearchOption[0];
		if(oos == null)
			oos = new OrderOption[0];
		
		String sql = jdbcHelp.getSearchOptionSql(cls, sos, oos);
		
		PreparedStatement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			jdbcHelp.setParameterForSearchOption(cmd, sos);
			rs = cmd.executeQuery();
			try {
				while(rs.next()){
					Object entity = cls.newInstance();
					jdbcHelp.readFields(rs, entity);
					
					result.add(entity);
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return result;
	}
	
	protected Collection<? extends Object> searchPager(
			Class<? extends Object> cls, SearchOption[] sos, OrderOption[] oos, SearchPager sp)
					throws SQLException {
		
		Collection<Object> result = new LinkedList<Object>();
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		
		if(sos == null)
			sos = new SearchOption[0];
		if(oos == null)
			oos = new OrderOption[0];
		
		String sql = jdbcHelp.getSearchOptionSql(cls, sos, oos);
		
		String sqlCnt = jdbcHelp.toGetCountSql(sql);
		String sqlDat = jdbcHelp.toPageSql(sql, sp.getPageIndex(), sp.getPageSize());
		
		PreparedStatement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sqlCnt);
			jdbcHelp.setParameterForSearchOption(cmd, sos);
			rs = cmd.executeQuery();
			if(rs.next()){
				int count = rs.getInt("Cnt");
				sp.reset(count);
			}
			rs.close();
			cmd.close();
			
			cmd = DBSession.getPreparedStatement(sqlDat);
			jdbcHelp.setParameterForSearchOption(cmd, sos);
			rs = cmd.executeQuery();
			try{
				while(rs.next()){
					Object entity = cls.newInstance();
					jdbcHelp.readFields(rs, entity);
					result.add(entity);
				}
			}catch(Exception ex){
				throw new RuntimeException(ex);
			}
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return result;
	}
}

