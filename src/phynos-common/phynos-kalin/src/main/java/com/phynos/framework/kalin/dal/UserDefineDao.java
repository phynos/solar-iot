package com.phynos.framework.kalin.dal;


import java.sql.SQLException;
import java.util.List;

public class UserDefineDao {
	
	public boolean tableExists(String tableName) throws SQLException {
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		return jdbcHelp.tableExists(tableName);
	}
	
	public boolean columnExists(String tableName, String column) throws SQLException {
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		return jdbcHelp.columnExists(tableName, column);
	}
	
	public boolean appendColumn(String tableName, UserDefineColumn column) throws SQLException {
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		return jdbcHelp.appendColumn(tableName, column);
	}
	
	public boolean buildTable(
			UserDefineTable table, 
			UserDefinePrimaryKey primaryKey, 
			UserDefineColumn[] columns,
			JDBCHelp.EDateRangePartitionType partitionType,
			String partitionColumn, 
			String dataDir) throws SQLException{
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		return jdbcHelp.buildTable(table, primaryKey, columns, partitionType, partitionColumn, dataDir);
	}
	
	public void createIndex(
			String tableName, 
			String columnName, 
			String indexName) throws SQLException {
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		jdbcHelp.createIndex(tableName, columnName, indexName);
	}
	
	public Object saveUserDefineData(
			UserDefineTable table, 
			UserDefinePrimaryKey primaryKey, 
			UserDefineColumnData[] columns) throws SQLException{
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		return jdbcHelp.saveUserDefineData(table, primaryKey, columns);
	}
	
	public void saveBatch(
			UserDefineTable table, 
			UserDefinePrimaryKey primaryKey,
			List<List<UserDefineColumnData>> rows, 
			int batchSize) throws SQLException {
		
		JDBCHelp jdbcHelp = JDBCHelpFactory.getJDBCHelp();
		jdbcHelp.saveBatch(table, primaryKey, rows, batchSize);
	}
	
}

