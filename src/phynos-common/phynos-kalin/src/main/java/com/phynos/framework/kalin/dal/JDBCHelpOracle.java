package com.phynos.framework.kalin.dal;


import com.phynos.framework.kalin.util.DisposeUtil;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCHelpOracle extends JDBCHelpBase {

	@Override
	public String toGetCountSql(String sql) {
		return " Select Count(*) As Cnt From (" + sql + ") tbData";
	}

	@Override
	public String toPageSql(String sql, int pageIndex, int pageSize) {
		
		int idx1 = (pageIndex - 1) * pageSize + 1;
		int idx2 = idx1 + pageSize - 1;
		
		StringBuilder sb = new StringBuilder();
		sb
		.append("Select * From (Select RowNum As TheRowNumber, t.* From (")
		.append(sql)
		.append(") t )t1 Where TheRowNumber Between ")
		.append(Integer.toString(idx1))
		.append(" And ")
		.append(Integer.toString(idx2));
		
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public String getDeleteSql(Class<? extends Object> cls, SearchOption[] sos){
		
		StringBuilder sb = new StringBuilder();
		
		JDBCHelpData helpData = super.getJDBCHelpData(cls);
		String tableName = helpData.getEntity().getEntity().table();
		
		sb.append(" Delete From ")
		.append(tableName);
		
		String pre = " Where ";
		if(sos != null && sos.length > 0){
			for(SearchOption so : sos){
				
				ESearchOptionEnum soe = so.getSearchOptionEnum();
				
				if(soe != ESearchOptionEnum.IsNotNull && soe != ESearchOptionEnum.IsNull && so.getValue() == null)
				 	continue;
				
				sb.append(pre)
				.append(so.getColumn());
				
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
	public String getSearchOptionSql(Class<? extends Object> cls,
			SearchOption[] sos, OrderOption[] oos) {
		
		JDBCHelpData helpData = super.getJDBCHelpData(cls);
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		PropertyDefine[] properties = helpData.getProperties();
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select ");
		String pre0 = "";
		if(primaryKey != null){
			sb.append(primaryKey.getPrimaryKey().column());
			pre0 = ", ";
		}
		for(PropertyDefine property : properties){
			sb.append(pre0).append(property.getProperty().column());
			pre0 = ", ";
		}
		
		sb.append(" From ").append(helpData.getEntity().getEntity().table());
		
		boolean lockTable = false;
		
		if(sos != null && sos.length > 0){
			String pre = " Where ";
			for(SearchOption so : sos){
				
				ESearchOptionEnum soe = so.getSearchOptionEnum();
				
				if(so.isLockTable())
					lockTable = true;
				
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
			SearchOption[] sos) throws SQLException {
		
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
	
	@Override
	public void setParameterForDeleteData(PreparedStatement cmd,
			Class<? extends Object> cls, Object id) throws SQLException {
		
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
	public void setParameterForGetById(PreparedStatement cmd,
			Class<? extends Object> cls, Object id) throws SQLException {
		
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
			PrimaryKeyDefine primaryKey = super.getJDBCHelpData(entity.getClass()).getPrimaryKey();
			String sequence = primaryKey.getPrimaryKey().sequence();
			
			cmd = DBSession.getStatement();
			rs = cmd.executeQuery("SELECT " + sequence + ".CurrVal From Dual");
			if(rs.next()){
				
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
	public boolean buildTable(UserDefineTable table,
			UserDefinePrimaryKey primaryKey, UserDefineColumn[] columns, EDateRangePartitionType partitionType, String partitionColumn, String dataDir)
			throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Create Table ").append(table.getTableName()).append("");
		
		String pre = " (";
		
		if(primaryKey != null){
			sb.append(pre);
			sb.append(primaryKey.getColumnName());
			
			if(primaryKey.getDataType() == PropertyType.Integer)
				sb.append(" Number(10) Primary Key");
			else if(primaryKey.getDataType() == PropertyType.Long)
				sb.append(" Number(18) Primary Key");
			
			pre = ", ";
		}
		
		for(UserDefineColumn column : columns){
			sb.append(pre);
			sb.append(column.getColumnName());
			
			if(column.getDataType() == PropertyType.Boolean){
				sb.append(" Number(1)");
			}else if(column.getDataType() == PropertyType.Integer){
				sb.append(" Integer");
			}else if(column.getDataType() == PropertyType.Long){
				sb.append(" Number(18)");
			}else if(column.getDataType() == PropertyType.Float){
				sb.append(" Number(10, " + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.Double){
				sb.append(" Number(18, " + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.String){
				sb.append(" Varchar2(" + column.getSize() + ")");
			}else if(column.getDataType() == PropertyType.DateTime){
				sb.append(" Date");
			}else if(column.getDataType() == PropertyType.Binary){
				
			}
			pre = ", ";
		}
		
		sb.append(")");
		
		try{
			super.buildTable(sb.toString());
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	@Override
	public void saveBatch(UserDefineTable table,
			UserDefinePrimaryKey primaryKey,
			List<List<UserDefineColumnData>> rows, int batchSize)
			throws SQLException {
		
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
			UserDefinePrimaryKey primaryKey, UserDefineColumnData[] columns)
			throws SQLException {
		
		String sql = super.getUserDefineDataInsertSql(table, primaryKey, columns);
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = DBSession.getPreparedStatement(sql);
			
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
	protected String buildInsertSql(JDBCHelpData helpData,
			Class<? extends Object> cls) {
		
		String tableName = helpData.getEntity().getEntity().table();
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		PropertyDefine[] properties = helpData.getProperties();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Insert Into ").append(tableName);
		
		sb.append("(").append(primaryKey.getPrimaryKey().column());
		
		String pre = ", ";
		
		for(PropertyDefine property : properties){
			sb.append(pre);
			sb.append(property.getProperty().column());
		}
		sb.append(") Values(").append(primaryKey.getPrimaryKey().sequence()).append(".NextVal");
		
		for(int i=0; i<properties.length; i++){
			sb.append(pre);
			sb.append("?");
		}
		sb.append(")");
		
		return sb.toString();
	}
	
	@Override
	protected String buildUpdateSql(JDBCHelpData helpData,
			Class<? extends Object> cls) {
		
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
			sb.append(property.getProperty().column());
			sb.append(" = ?");
			pre = ", ";
		}
		
		sb.append(" Where ").append(primaryKey.getPrimaryKey().column()).append(" = ?");
		
		return sb.toString();
	}

	@Override
	protected String buildDeleteSql(JDBCHelpData helpData,
			Class<? extends Object> cls) {
		
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		if(primaryKey == null)
			return null;
		
		String tableName = helpData.getEntity().getEntity().table();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Delete From ").append(tableName);
		sb.append(" Where ").append(primaryKey.getPrimaryKey().column()).append(" = ?");
		
		return sb.toString();
	}

	@Override
	protected String buildGetByIdSql(JDBCHelpData helpData,
			Class<? extends Object> cls) {
		
		String tableName = helpData.getEntity().getEntity().table();
		PropertyDefine[] properties = helpData.getProperties();
		PrimaryKeyDefine primaryKey = helpData.getPrimaryKey();
		
		StringBuilder sb = new StringBuilder();
		
		String pre = " Select ";
		
		if(primaryKey != null && primaryKey.getPrimaryKey() != null){
			sb.append(pre);
			sb.append(primaryKey.getPrimaryKey().column());
			pre = ", ";
		}
		
		for(PropertyDefine property : properties){
			sb.append(pre);
			sb.append(property.getProperty().column());
			pre = ", ";
		}
		
		sb.append(" From ");
		sb.append(tableName);
		if(primaryKey != null)
			sb.append(" Where ").append(primaryKey.getPrimaryKey().column()).append(" = ?");
		
		return sb.toString();
	}

	@Override
	protected String buildUserDefineDataInsertSql(UserDefineTable table,
			UserDefinePrimaryKey primaryKey, UserDefineColumnData[] columns) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" Insert Into ").append(table.getTableName());
		
		String pre = " (";
		for(UserDefineColumnData column : columns){
			sb.append(pre);
			sb.append(column.getColumnDefine().getColumnName());
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

	@Override
	public boolean tableExists(String tableName) throws SQLException {
		
		return false;
	}

	@Override
	public boolean appendColumn(String tableName, UserDefineColumn column)
			throws SQLException {
		
		return false;
	}

	@Override
	public boolean columnExists(String tableName, String column)
			throws SQLException {
		
		return false;
	}

	@Override
	public boolean createIndex(String tableName, String column, String idxName)
			throws SQLException {
		
		return false;
	}
}

