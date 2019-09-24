package com.phynos.framework.kalin.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface JDBCHelp {

	public enum EDateRangePartitionType{
		ByMonth, ByQuarter, ByHalfYear, ByYear
	}
	
	public String toGetCountSql(String sql);
	
	public String toPageSql(String sql, int pageIndex, int pageSize);
	
	public String getInsertSql(Class<? extends Object> cls);
	
	public String getUpdateByIdSql(Class<? extends Object> cls);
	
	public String getDeleteByIdSql(Class<? extends Object> cls);
	
	public String getGetByIdSql(Class<? extends Object> cls);
	
	public String getDeleteSql(Class<? extends Object> cls, SearchOption[] sos);
	
	public String getSearchOptionSql(Class<? extends Object> cls, SearchOption[] sos);
	
	public String getSearchOptionSql(Class<? extends Object> cls, SearchOption[] sos, OrderOption[] oos);
	
	public void setParameterForSearchOption(PreparedStatement cmd, SearchOption[] sos) throws SQLException;
	
	public void setParameterForInsert(PreparedStatement cmd, Object entity, List<java.io.InputStream> lstIs) throws SQLException;
	
	public void setParameterForUpdate(PreparedStatement cmd, Object entity, List<java.io.InputStream> lstIs) throws SQLException;
	
	public void setParameterForDeleteEntity(PreparedStatement cmd, Object entity) throws SQLException;
	
	public void setParameterForDeleteData(PreparedStatement cmd, Class<? extends Object> cls, Object id) throws SQLException;
	
	public void setParameterForGetById(PreparedStatement cmd, Class<? extends Object> cls, Object id) throws SQLException;
	
	public void setIdValueAfterInsert(Object entity) throws SQLException;
	
	public void setIdValueEmpty(Object entity);
	
	public boolean buildTable(UserDefineTable table, UserDefinePrimaryKey primaryKey, UserDefineColumn[] columns, EDateRangePartitionType partitionType, String partitionColumn, String dataDir) throws SQLException;
	
	public boolean tableExists(String tableName) throws SQLException;
	
	public boolean columnExists(String tableName, String column) throws SQLException;
	
	public boolean appendColumn(String tableName, UserDefineColumn column) throws SQLException;
	
	public boolean createIndex(String tableName, String column, String idxName) throws SQLException;
	
	public void saveBatch(
            UserDefineTable table,
            UserDefinePrimaryKey primaryKey,
            List<List<UserDefineColumnData>> rows,
            int batchSize) throws SQLException;

	public Object saveUserDefineData(
            UserDefineTable table,
            UserDefinePrimaryKey primaryKey,
            UserDefineColumnData[] columns) throws SQLException;
	
	public void readFields(ResultSet rs, Object entity) throws SQLException;
}
