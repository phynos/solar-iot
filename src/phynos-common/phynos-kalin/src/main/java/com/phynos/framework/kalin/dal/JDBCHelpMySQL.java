package com.phynos.framework.kalin.dal;


import com.phynos.framework.kalin.util.DateTimeUtility;
import com.phynos.framework.kalin.util.DisposeUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

public class JDBCHelpMySQL extends JDBCHelpBase {
	
	@Override
	public void setParameterForInsert(PreparedStatement cmd, Object entity, List<java.io.InputStream> lstIs)
			throws SQLException {
		
		PropertyDefine[] properties = super.getJDBCHelpData(entity.getClass()).getProperties();
		
		int p = 1;
		for(PropertyDefine property : properties){
			PropertyType type = property.getProperty().type();
			Field field = property.getField();
			
			if(type == PropertyType.Boolean){
				Boolean value = super.readBoolean(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.BOOLEAN);
				else
					cmd.setBoolean(p, value);
			}else if(type == PropertyType.Integer){
				Integer value = super.readInteger(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.INTEGER);
				else
					cmd.setInt(p, value);
			}else if(type == PropertyType.Long){
				Long value = super.readLong(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.BIGINT);
				else
					cmd.setLong(p, value);
			}else if(type == PropertyType.Float){
				Float value = super.readFloat(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.FLOAT);
				else
					cmd.setFloat(p, value);
			}else if(type == PropertyType.Double){
				Double value = super.readDouble(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.DOUBLE);
				else
					cmd.setDouble(p, value);
			}else if(type == PropertyType.DateTime){
				java.util.Date value = super.readDateTime(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.TIMESTAMP);
				else
					cmd.setTimestamp(p, new java.sql.Timestamp(value.getTime()));
			}else if(type == PropertyType.String){
				String value = super.readString(entity, field);
				if(value == null)
					cmd.setNull(p, java.sql.Types.VARCHAR);
				else
					cmd.setString(p, value);
			}else if(type == PropertyType.Binary){
				byte[] value = super.readBytes(entity, field);
				if(value == null){
					cmd.setNull(p, java.sql.Types.BINARY);
				}else{
					java.io.InputStream is = new java.io.ByteArrayInputStream(value);
					cmd.setBinaryStream(p, is);
					lstIs.add(is);
				}
			}
			p++;
		}
	}
	
	@Override
	public void setParameterForUpdate(PreparedStatement cmd, Object entity, List<java.io.InputStream> lstIs)
			throws SQLException {
		
		this.setParameterForInsert(cmd, entity, lstIs);
		int p = super.getJDBCHelpData(entity.getClass()).getProperties().length + 1;
		
		PrimaryKeyDefine primaryKey = super.getJDBCHelpData(entity.getClass()).getPrimaryKey();
		if(primaryKey.getPrimaryKey().type() == PropertyType.Integer){
			Integer value = super.readInteger(entity, primaryKey.getField());
			cmd.setInt(p, value);
		}else if(primaryKey.getPrimaryKey().type() == PropertyType.Long){
			Long value = super.readLong(entity, primaryKey.getField());
			cmd.setLong(p, value);
		}
	}

	@Override
	public void setParameterForDeleteEntity(PreparedStatement cmd, Object entity)
			throws SQLException {
		
		PrimaryKeyDefine primaryKey = super.getJDBCHelpData(entity.getClass()).getPrimaryKey();
		Object id = this.readPrimaryKey(entity);
		if(primaryKey.getPrimaryKey().type() == PropertyType.Integer){
			Integer value = (Integer)id;
			cmd.setInt(1, value);
		}else if(primaryKey.getPrimaryKey().type() == PropertyType.Long){
			Long value = (Long)id;
			cmd.setLong(1, value);
		}
	}
	
	public void setParameterForDeleteData(PreparedStatement cmd, Class<? extends Object> cls, Object id)
			throws SQLException {
		
		PrimaryKeyDefine primaryKey = super.getJDBCHelpData(cls).getPrimaryKey();
		if(primaryKey.getPrimaryKey().type() == PropertyType.Integer){
			Integer value = (Integer)id;
			cmd.setInt(1, value);
		}else if(primaryKey.getPrimaryKey().type() == PropertyType.Long){
			Long value = (Long)id;
			cmd.setLong(1, value);
		}
	}
	
	@Override
	public void setParameterForGetById(PreparedStatement cmd, Class<? extends Object> cls, Object id)
			throws SQLException {
		
		PrimaryKeyDefine primaryKey = super.getJDBCHelpData(cls).getPrimaryKey();
		if(primaryKey.getPrimaryKey().type() == PropertyType.Integer){
			Integer value = (Integer)id;
			cmd.setInt(1, value);
		}else if(primaryKey.getPrimaryKey().type() == PropertyType.Long){
			Long value = (Long)id;
			cmd.setLong(1, value);
		}
	}

	@Override
	public void setIdValueAfterInsert(Object entity) throws SQLException {
		
		Statement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getStatement();
			rs = cmd.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				PrimaryKeyDefine primaryKey = super.getJDBCHelpData(entity.getClass()).getPrimaryKey();
				if(primaryKey.getPrimaryKey().type() == PropertyType.Integer){
					Integer value = rs.getInt(1);
					super.setValue(entity, primaryKey.getField(), value);
				}else if(primaryKey.getPrimaryKey().type() == PropertyType.Long){
					Long value = rs.getLong(1);
					super.setValue(entity, primaryKey.getField(), value);
				}
			}
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
	}
	
	@Override
	public void readFields(ResultSet rs, Object entity) throws SQLException {
		
		JDBCHelpData jdbcHelpData = super.getJDBCHelpData(entity.getClass());
		
		int idx = 1;
		
		PrimaryKeyDefine primaryKey = jdbcHelpData.getPrimaryKey();
		if(primaryKey != null && primaryKey.getPrimaryKey() != null){
			PropertyType type = primaryKey.getPrimaryKey().type();
			Object idValue = null;
			
			if(type == PropertyType.Integer)
				idValue = rs.getInt(idx++);
			else if(type == PropertyType.Long)
				idValue = rs.getLong(idx++);
			
			super.setValue(entity, primaryKey.getField(), idValue);
		}
		
		PropertyDefine[] properties = jdbcHelpData.getProperties();
		for(PropertyDefine property : properties){
			PropertyType type = property.getProperty().type();
			
			Object value = null;
			if(type == PropertyType.Boolean)
				value = rs.getBoolean(idx++);
			else if(type == PropertyType.Integer)
				value = rs.getInt(idx++);
			else if(type == PropertyType.Long)
				value = rs.getLong(idx++);
			else if(type == PropertyType.Float)
				value = rs.getFloat(idx++);
			else if(type == PropertyType.Double)
				value = rs.getDouble(idx++);
			else if(type == PropertyType.DateTime)
				value = rs.getTimestamp(idx++);
			else if(type == PropertyType.String)
				value = rs.getString(idx++);
			else if(type == PropertyType.Binary)
				value = rs.getBinaryStream(idx++);
			else
				idx++;
			
			if(rs.wasNull())
				value = null;
			if(value != null && value instanceof java.io.InputStream){
				java.io.InputStream is = (java.io.InputStream)value;
				java.io.ByteArrayOutputStream os = new java.io.ByteArrayOutputStream();
				try{
					byte[] buff = new byte[1024];
					int size;
					while((size = is.read(buff)) > 0){
						os.write(buff, 0, size);
					}
					value = os.toByteArray();
				}catch(Exception ex){
					throw new SQLException(ex);
				}finally{
					DisposeUtil.safeClose(os);
					DisposeUtil.safeClose(is);
				}
			}
			
			super.setValue(entity, property.getField(), value);
		}
	}

	@Override
	protected String buildInsertSql(JDBCHelpData helpData, Class<? extends Object> cls) {
		
		try{
		String tableName = helpData.getEntity().getEntity().table();
		PropertyDefine[] properties = helpData.getProperties();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Insert Into ").append(tableName);
		
		String pre = " (";
		
		for(PropertyDefine property : properties){
			sb.append(pre);
			sb.append("`");
			sb.append(property.getProperty().column());
			sb.append("`");
			pre = ", ";
		}
		
		pre = ") Values (";
		for(int i=0; i<properties.length; i++){
			sb.append(pre);
			sb.append("?");
			pre = ", ";
		}
		sb.append(")");
		
		return sb.toString();
		}catch(Exception ex){
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	protected String buildUpdateSql(JDBCHelpData helpData, Class<? extends Object> cls) {
		
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		if(primaryKey == null)
			return null;
		String tableName = helpData.getEntity().getEntity().table();
		PropertyDefine[] properties = helpData.getProperties();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Update ").append(tableName);
		
		String pre = " Set ";
		for(PropertyDefine property : properties){
			sb.append(pre);
			sb.append("`");
			sb.append(property.getProperty().column());
			sb.append("`");
			sb.append(" = ?");
			pre = ", ";
		}
		sb.append(" Where `").append(primaryKey.getPrimaryKey().column()).append("` = ?");
		
		return sb.toString();
	}

	@Override
	protected String buildDeleteSql(JDBCHelpData helpData, Class<? extends Object> cls) {
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		if(primaryKey == null)
			return null;
		String tableName = helpData.getEntity().getEntity().table();
		//PrimaryKey primaryKey = helpData.getPrimaryKey().getPrimaryKey();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Delete From ").append(tableName);
		sb.append(" Where `").append(primaryKey.getPrimaryKey().column()).append("` = ?");
		
		return sb.toString();
	}

	@Override
	protected String buildGetByIdSql(JDBCHelpData helpData, Class<? extends Object> cls) {
		
		String tableName = helpData.getEntity().getEntity().table();
		PropertyDefine[] properties = helpData.getProperties();
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		
		StringBuilder sb = new StringBuilder();
		
		String pre = " Select ";
		
		if(primaryKey != null && primaryKey.getPrimaryKey() != null){
			sb.append(pre);
			sb.append("`");
			sb.append(primaryKey.getPrimaryKey().column());
			sb.append("`");
			pre = ", ";
		}
		
		for(PropertyDefine property : properties){
			sb.append(pre);
			sb.append("`");
			sb.append(property.getProperty().column());
			sb.append("`");
			pre = ", ";
		}
		
		sb.append(" From ");
		sb.append(tableName);
		if(primaryKey != null)
			sb.append(" Where `").append(primaryKey.getPrimaryKey().column()).append("` = ?");
		
		return sb.toString();
	}

	@Override
	public boolean buildTable(UserDefineTable table,
			UserDefinePrimaryKey primaryKey, UserDefineColumn[] columns,
			EDateRangePartitionType partitionType, String partitionColumn, String dataDir) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Create Table `").append(table.getTableName()).append("`");
		
		String pre = " (";
		
		if(primaryKey != null){
			sb.append(pre);
			sb.append("`");
			sb.append(primaryKey.getColumnName());
			sb.append("`");
			
			if(primaryKey.getDataType() == PropertyType.Integer)
				sb.append(" Integer");
			else if(primaryKey.getDataType() == PropertyType.Long)
				sb.append(" BigInt");
			
			sb.append(" Auto_Increment");
			pre = ", ";
		}
		
		for(UserDefineColumn column : columns){
			sb.append(pre);
			sb.append("`");
			sb.append(column.getColumnName());
			sb.append("`");
			
			if(column.getDataType() == PropertyType.Boolean){
				sb.append(" Bit");
			}else if(column.getDataType() == PropertyType.Integer){
				sb.append(" Integer");
			}else if(column.getDataType() == PropertyType.Long){
				sb.append(" BigInt");
			}else if(column.getDataType() == PropertyType.Float){
				sb.append(" Float(10, " + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.Double){
				sb.append(" Double(18, " + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.String){
				sb.append(" Varchar(" + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.DateTime){
				sb.append(" DateTime");
			}else if(column.getDataType() == PropertyType.Binary){
				
			}
			pre = ", ";
		}
		
		if(primaryKey != null){
			sb.append(pre);
			if(partitionType == null){
				sb.append("Primary Key(`").append(primaryKey.getColumnName()).append("`)");
			}else{
				sb.append("Primary Key(`").append(primaryKey.getColumnName()).append("`, `").append(partitionColumn).append("`)");
			}
			pre = ", ";
		}
		
		sb.append(")");
		
		// 分区
		if(partitionType != null){
			
			java.util.Date today = new java.util.Date(System.currentTimeMillis());
			String strDate = null;
			String strByRange = null;
			
			if(partitionType == EDateRangePartitionType.ByMonth){
				java.util.Date date = DateTimeUtility.addMonths(today, 1);
				strDate = new java.text.SimpleDateFormat("yyyyMM").format(date);
				strByRange = "Year(`" + partitionColumn + "`) * 100 + Month(`" + partitionColumn + "`)";
			}else if(partitionType == EDateRangePartitionType.ByQuarter){
				java.util.Date date = DateTimeUtility.addMonths(today, 3);
				strDate = new java.text.SimpleDateFormat("yyyyMM").format(date);
				strByRange = "Year(`" + partitionColumn + "`) * 100 + Month(`" + partitionColumn + "`)";
			}else if(partitionType == EDateRangePartitionType.ByHalfYear){
				java.util.Date date = DateTimeUtility.addMonths(today, 6);
				strDate = new java.text.SimpleDateFormat("yyyyMM").format(date);
				strByRange = "Year(`" + partitionColumn + "`) * 100 + Month(`" + partitionColumn + "`)";
			}else if(partitionType == EDateRangePartitionType.ByYear){
				java.util.Date date = DateTimeUtility.addMonths(today, 12);
				strDate = new java.text.SimpleDateFormat("yyyy").format(date);
				strByRange = "Year(`" + partitionColumn + "`)";
			}
			sb
			.append(" Partition By Range (").append(strByRange).append(")(")
			.append(" Partition `P").append(strDate).append("` Values Less Than (").append(strDate).append(")")
			.append(" Data Directory = '").append(dataDir).append("')");
		}
		
		try{
			super.buildTable(sb.toString());
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	@Override
	public void saveBatch(
			UserDefineTable table,
			UserDefinePrimaryKey primaryKey,
			List<List<UserDefineColumnData>> rows, 
			int batchSize) throws SQLException {
		
		if(rows.size() == 0)
			return;
		
		String sql = 
				super.getUserDefineDataInsertSql(
						table, 
						primaryKey, 
						rows.get(0).toArray(new UserDefineColumnData[0]));
		
		PreparedStatement cmd = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			int prepareCount = 0;
			for(List<UserDefineColumnData> row : rows){
				int index = 1;
				for(UserDefineColumnData columnData : row){
					PropertyType type = columnData.getColumnDefine().getDataType();
					Object value = columnData.getColumnValue();
					
					if(type == PropertyType.Boolean){
						if(value == null)
							cmd.setNull(index, java.sql.Types.BOOLEAN);
						else
							cmd.setBoolean(index, (Boolean)value);
					}else if(type == PropertyType.Integer){
						if(value == null)
							cmd.setNull(index, java.sql.Types.INTEGER);
						else
							cmd.setInt(index, (Integer)value);
					}else if(type == PropertyType.Long){
						if(value == null)
							cmd.setNull(index, java.sql.Types.BIGINT);
						else
							cmd.setLong(index, (Long)value);
					}else if(type == PropertyType.Float){
						if(value == null)
							cmd.setNull(index, java.sql.Types.FLOAT);
						else
							cmd.setFloat(index, (Float)value);
					}else if(type == PropertyType.Double){
						if(value == null)
							cmd.setNull(index, java.sql.Types.DOUBLE);
						else
							cmd.setDouble(index, (Double)value);
					}else if(type == PropertyType.DateTime){
						if(value == null)
							cmd.setNull(index, java.sql.Types.TIMESTAMP);
						else
							cmd.setTimestamp(index, new java.sql.Timestamp(((java.util.Date)value).getTime()));
					}else if(type == PropertyType.String){
						if(value == null)
							cmd.setNull(index, java.sql.Types.VARCHAR);
						else
							cmd.setString(index, (String)value);
					}else if(type == PropertyType.Binary){
						
					}
					index++;
				}
				cmd.execute();
				prepareCount++;
				
				if(prepareCount == batchSize){
					DBSession.commitCurrent();
					prepareCount = 0;
				}
			}
			DBSession.commitCurrent();
		}finally{
			DisposeUtil.safeClose(cmd);
		}
	}
		
	@Override
	public Object saveUserDefineData(UserDefineTable table,
			UserDefinePrimaryKey primaryKey,
			UserDefineColumnData[] columns) throws SQLException {
		
		String sql = super.getUserDefineDataInsertSql(table, primaryKey, columns);
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = DBSession.getPreparedStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			int index = 1;
			for(UserDefineColumnData columnData : columns){
				PropertyType type = columnData.getColumnDefine().getDataType();
				Object value = columnData.getColumnValue();
				
				if(type == PropertyType.Boolean){
					if(value == null)
						stmt.setNull(index, java.sql.Types.BOOLEAN);
					else
						stmt.setBoolean(index, (Boolean)value);
				}else if(type == PropertyType.Integer){
					if(value == null)
						stmt.setNull(index, java.sql.Types.INTEGER);
					else
						stmt.setInt(index, (Integer)value);
				}else if(type == PropertyType.Long){
					if(value == null)
						stmt.setNull(index, java.sql.Types.BIGINT);
					else
						stmt.setLong(index, (Long)value);
				}else if(type == PropertyType.Float){
					if(value == null)
						stmt.setNull(index, java.sql.Types.FLOAT);
					else
						stmt.setFloat(index, (Float)value);
				}else if(type == PropertyType.Double){
					if(value == null)
						stmt.setNull(index, java.sql.Types.DOUBLE);
					else
						stmt.setDouble(index, (Double)value);
				}else if(type == PropertyType.DateTime){
					if(value == null)
						stmt.setNull(index, java.sql.Types.TIMESTAMP);
					else
						stmt.setTimestamp(index, new java.sql.Timestamp(((java.util.Date)value).getTime()));
				}else if(type == PropertyType.String){
					if(value == null)
						stmt.setNull(index, java.sql.Types.VARCHAR);
					else
						stmt.setString(index, (String)value);
				}else if(type == PropertyType.Binary){
					
				}
				index++;
				
			}
			stmt.execute();
			
			rs = stmt.getGeneratedKeys();
			if(rs.next()){
				if(primaryKey.getDataType() == PropertyType.Integer)
					return rs.getInt(1);
				else if(primaryKey.getDataType() == PropertyType.Long)
					return rs.getLong(1);
			}
			
			return null;
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(stmt);
		}
	}

	@Override
	protected String buildUserDefineDataInsertSql(UserDefineTable table,
			UserDefinePrimaryKey primaryKey, UserDefineColumnData[] columns) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Insert Into `").append(table.getTableName()).append("`");
		
		String pre = " (";
		for(UserDefineColumnData column : columns){
			sb.append(pre);
			sb.append("`");
			sb.append(column.getColumnDefine().getColumnName());
			sb.append("`");
			pre = ", ";
		}
		sb.append(")");
		
		pre = " Values (";
		for(int i=0; i<columns.length; i++){
			sb.append(pre);
			sb.append("?");
			pre = ", ";
		}
		sb.append(")");
		
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDeleteSql(Class<? extends Object> cls, SearchOption[] sos){
		StringBuilder sb = new StringBuilder();
		
		JDBCHelpData helpData = super.getJDBCHelpData(cls);
		String tableName = helpData.getEntity().getEntity().table();
		
		sb.append(" Delete From `")
		.append(tableName)
		.append("`");
		
		String pre = " Where ";
		if(sos != null && sos.length > 0){
			for(SearchOption so : sos){
				
				ESearchOptionEnum soe = so.getSearchOptionEnum();
				
				if(soe != ESearchOptionEnum.IsNotNull && soe != ESearchOptionEnum.IsNull && so.getValue() == null)
					continue;
				
				sb.append(pre);
				
				sb.append("`")
				.append(so.getColumn())
				.append("`");
				
				if(soe == ESearchOptionEnum.LessThan){
					sb.append(" < ?");
				}else if(soe == ESearchOptionEnum.LessThanEquals){
					sb.append(" <= ?");
				}else if(soe == ESearchOptionEnum.Equals){
					sb.append(" = ?");
				}else if(soe == ESearchOptionEnum.GreatThan){
					sb.append(" > ?");
				}else if(soe == ESearchOptionEnum.GreatThanEquals){
					sb.append(" >= ?");
				}else if(soe == ESearchOptionEnum.NotEquals){
					sb.append(" <> ?");
				}else if(soe == ESearchOptionEnum.IsNull){
					sb.append(" Is Null");
				}else if(soe == ESearchOptionEnum.IsNotNull){
					sb.append(" Is Not Null");
				}else if(soe == ESearchOptionEnum.StartWith){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.EndWidth){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.Like){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.In){
					sb.append(" In ");
					super.appendListSql(sb, (List<Object>)so.getValue());
				}else if(soe == ESearchOptionEnum.NotIn){
					sb.append(" Not In ");
					super.appendListSql(sb, (List<Object>)so.getValue());
				}
				
				pre = " And ";
			}
		}
		
		return sb.toString();
	}
	
	@Override
	public String getSearchOptionSql(Class<? extends Object> cls,
			SearchOption[] sos) {
		
		return getSearchOptionSql(cls, sos, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getSearchOptionSql(
			Class<? extends Object> cls, SearchOption[] sos, OrderOption[] oos){
		
		JDBCHelpData helpData = super.getJDBCHelpData(cls);
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		PropertyDefine[] properties = helpData.getProperties();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select ");
		String pre0 = "";
		if(primaryKey != null){
			sb.append("`").append(primaryKey.getPrimaryKey().column()).append("`");
			pre0 = ", ";
		}
		for(PropertyDefine property : properties){			
			sb.append(pre0).append("`").append(property.getProperty().column()).append("`");
			pre0 = ", ";
		}
		
		sb.append(" From ").append("`").append(helpData.getEntity().getEntity().table()).append("`");
		
		boolean lockTable = false;
		if(sos != null && sos.length > 0){
			String pre = " Where ";
			for(SearchOption so : sos){
				
				ESearchOptionEnum soe = so.getSearchOptionEnum();
				
				if(so.isLockTable()){
					lockTable = true;
					continue;
				}
				
				if(soe != ESearchOptionEnum.IsNotNull && soe != ESearchOptionEnum.IsNull && so.getValue() == null)
				 	continue;
				
				sb.append(pre);
				sb.append(so.getColumn());
				if(soe == ESearchOptionEnum.LessThan){
					sb.append(" < ?");
				}else if(soe == ESearchOptionEnum.LessThanEquals){
					sb.append(" <= ?");
				}else if(soe == ESearchOptionEnum.Equals){
					sb.append(" = ?");
				}else if(soe == ESearchOptionEnum.GreatThan){
					sb.append(" > ?");
				}else if(soe == ESearchOptionEnum.GreatThanEquals){
					sb.append(" >= ?");
				}else if(soe == ESearchOptionEnum.NotEquals){
					sb.append(" <> ?");
				}else if(soe == ESearchOptionEnum.IsNull){
					sb.append(" Is Null");
				}else if(soe == ESearchOptionEnum.IsNotNull){
					sb.append(" Is Not Null");
				}else if(soe == ESearchOptionEnum.StartWith){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.EndWidth){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.Like){
					sb.append(" Like ?");
				}else if(soe == ESearchOptionEnum.In){
					sb.append(" In ");
					super.appendListSql(sb, (List<Object>)so.getValue());
				}else if(soe == ESearchOptionEnum.NotIn){
					sb.append(" Not In ");
					super.appendListSql(sb, (List<Object>)so.getValue());
				}
				
				pre = " And ";
			}
			
		}
		
		if(oos != null && oos.length > 0){
			String pre = " Order By ";
			for(OrderOption oo : oos){
				sb.append(pre);
				sb.append(oo.getColumn());
				if(oo.getOrderOption() == EOrderOptionEnum.Asc)
					sb.append(" Asc ");
				else if(oo.getOrderOption() == EOrderOptionEnum.Desc)
					sb.append(" Desc ");
				
				pre = ", ";
			}
		}
		
		if(lockTable)
			sb.append(" For Update");
		
		return sb.toString();
	}
	
	@Override
	public void setParameterForSearchOption(PreparedStatement cmd,
			SearchOption[] sos) throws SQLException{
		
		if(sos == null || sos.length == 0)
			return;
		
		int idx = 1;
		for(SearchOption so : sos){
			Object value = so.getValue();
			
			if(so.getSearchOptionEnum() == ESearchOptionEnum.IsNull 
					|| so.getSearchOptionEnum() == ESearchOptionEnum.IsNotNull)
				continue;
			
			if(value == null)
				continue;
			
			if(value instanceof Boolean){
				cmd.setBoolean(idx++, (Boolean)value);
			}else if(value instanceof Integer){
				cmd.setInt(idx++, (Integer)value);
			}else if(value instanceof Long){
				cmd.setLong(idx++, (Long)value);
			}else if(value instanceof Float){
				cmd.setFloat(idx++, (Float)value);
			}else if(value instanceof Double){
				cmd.setDouble(idx++, (Double)value);
			}else if(value instanceof java.util.Date){
				cmd.setTimestamp(idx++, new java.sql.Timestamp(((java.util.Date)value).getTime()));
			}else if(value instanceof String){
				if(so.getSearchOptionEnum() == ESearchOptionEnum.Equals)
					cmd.setString(idx++, (String)value);
				else if(so.getSearchOptionEnum() == ESearchOptionEnum.StartWith)
					cmd.setString(idx++, (String)value + "%");
				else if(so.getSearchOptionEnum() == ESearchOptionEnum.EndWidth)
					cmd.setString(idx++, "%" + (String)value);
				else if(so.getSearchOptionEnum() == ESearchOptionEnum.Like)
					cmd.setString(idx++, "%" + (String)value + "%");
					
			}
		}
	}

	@Override
	public String toGetCountSql(String sql) {
		return " Select Count(*) As Cnt From (" + sql + ") tbData";
	}
	
	@Override
	public String toPageSql(String sql, int pageIndex, int pageSize) {
		int skip = (pageIndex - 1) * pageSize;
		return sql + " Limit " + skip + ", " + pageSize;
	}

	@Override
	public boolean tableExists(String tableName) throws SQLException {
		
		int count = 0;
		String sql = 
				" Select Count(*) As Cnt From information_schema.`TABLES`" +
				" Where TABLE_SCHEMA = database() and Lower(TABLE_NAME) = Lower(?)";
		
		PreparedStatement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			cmd.setString(1, tableName);
			rs = cmd.executeQuery();
			if(rs.next()){
				count = rs.getInt("Cnt");
			}
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return count > 0;
	}

	@Override
	public boolean columnExists(String tableName, String column)
			throws SQLException {
		
		int count = 0;
		String sql = 
				" Select Count(*) As Cnt from information_schema.`COLUMNS`" + 
				" Where TABLE_SCHEMA = DATABASE() and LOWER(TABLE_NAME) = LOWER(?) And LOWER(COLUMN_NAME) = LOWER(?)";
		
		PreparedStatement cmd = null;
		ResultSet rs = null;
		
		try{
			cmd = DBSession.getPreparedStatement(sql);
			cmd.setString(1, tableName);
			cmd.setString(2, column);
			rs = cmd.executeQuery();
			if(rs.next()){
				count = rs.getInt("Cnt");
			}
			
		}finally{
			DisposeUtil.safeClose(rs);
			DisposeUtil.safeClose(cmd);
		}
		
		return count > 0;
	}
	
	@Override
	public boolean appendColumn(String tableName, UserDefineColumn column)
			throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		
		sb
		.append(" Alter Table `").append(tableName).append("`")
		.append(" Add `").append(column.getColumnName()).append("`");
		
		if(column.getDataType() == PropertyType.Boolean){
			sb.append(" Bit");
		}else if(column.getDataType() == PropertyType.Integer){
			sb.append(" Integer");
		}else if(column.getDataType() == PropertyType.Long){
			sb.append(" BigInt");
		}else if(column.getDataType() == PropertyType.Float){
			sb.append(" Float(10, " + column.getSize() + ")");
		}else if(column.getDataType() == PropertyType.Double){
			sb.append(" Double(18, " + column.getSize() + ")");
		}else if(column.getDataType() == PropertyType.String){
			sb.append(" Varchar(" + column.getSize() + ")");
		}else if(column.getDataType() == PropertyType.DateTime){
			sb.append(" DateTime");
		}else if(column.getDataType() == PropertyType.Binary){
			
		}
		
		Statement cmd = null;
		
		try{
			cmd = DBSession.getStatement();
			cmd.execute(sb.toString());
		}finally{
			DisposeUtil.safeClose(cmd);
		}
		
		return true;
	}

	@Override
	public boolean createIndex(String tableName, String column, String idxName)
			throws SQLException {
		
		if(idxName == null || idxName.length() == 0){
			idxName = UUID.randomUUID().toString().replace("-", "");
			idxName = idxName.replace('0', 'g');
			idxName = idxName.replace('1', 'h');
			idxName = idxName.replace('2', 'i');
			idxName = idxName.replace('3', 'j');
			idxName = idxName.replace('4', 'k');
			idxName = idxName.replace('5', 'l');
			idxName = idxName.replace('6', 'm');
			idxName = idxName.replace('7', 'n');
			idxName = idxName.replace('8', 'o');
			idxName = idxName.replace('9', 'p');
		}
		
		String sql = 
				" Create Index `" + idxName + "` On `" + tableName + "`(`" + column + "`)";
		
		Statement cmd = null;
		
		try{
			cmd = DBSession.getStatement();
			cmd.execute(sql);
		}finally{
			DisposeUtil.safeClose(cmd);
		}
		return false;
	}

}
