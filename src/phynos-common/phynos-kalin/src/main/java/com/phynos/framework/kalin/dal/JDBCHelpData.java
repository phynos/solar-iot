package com.phynos.framework.kalin.dal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class EntityDefine {
	
	private Entity entity;
	private Class<? extends Object> cls;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Class<? extends Object> getCls() {
		return cls;
	}

	public void setCls(Class<? extends Object> cls) {
		this.cls = cls;
	}
	
	public EntityDefine(Entity entity, Class<? extends Object> cls){
		this.entity = entity;
		this.cls = cls;
	}
}

class PrimaryKeyDefine {
	
	private PrimaryKey primaryKey;
	private Field field;

	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PrimaryKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public PrimaryKeyDefine(PrimaryKey primaryKey, Field field){
		this.primaryKey = primaryKey;
		this.field = field;
	}
}

class PropertyDefine {
	
	private Property property;
	private Field field;

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}
	
	public PropertyDefine(Property property, Field field){
		this.property = property;
		this.field = field;
	}
}

public class JDBCHelpData {
	
	private EntityDefine entity;
	private PrimaryKeyDefine primaryKey;
	private PropertyDefine[] properties;
	
	private String insertSql;
	private String updateSql;
	private String deleteSql;
	private String getByIdSql;
	
	public EntityDefine getEntity(){
		return entity;
	}
	
	public PrimaryKeyDefine getPrimaryKey(){
		return primaryKey;
	}
	
	public PropertyDefine[] getProperties(){
		return properties;
	}
	
	public String getInsertSql() {
		return insertSql;
	}
	public void setInsertSql(String value){
		this.insertSql = value;
	}
	public String getUpdateSql() {
		return updateSql;
	}
	public void setUpdateSql(String value){
		updateSql = value;
	}
	public String getDeleteSql() {
		return deleteSql;
	}
	public void setDeleteSql(String value){
		deleteSql = value;
	}
	public String getGetByIdSql() {
		return getByIdSql;
	}
	public void setGetByIdSql(String value){
		getByIdSql = value;
	}
	
	public JDBCHelpData(Class<? extends Object> entityClass){
		
		this.entity = new EntityDefine(
				entityClass.getAnnotation(Entity.class), 
				entityClass);
		
		try{
			Field[] fields = entityClass.getDeclaredFields();
			List<PropertyDefine> list = new ArrayList<PropertyDefine>();
			
			for(Field field : fields){
				PrimaryKey pk = field.getAnnotation(PrimaryKey.class);
				if(pk != null){
					field.setAccessible(true);
					this.primaryKey = new PrimaryKeyDefine(pk, field);
					continue;
				}
				
				Property property = field.getAnnotation(Property.class);
				if(property != null){
					field.setAccessible(true);
					list.add(new PropertyDefine(property, field));
				}
			}
			
			PropertyDefine[] properties = list.toArray(new PropertyDefine[0]);
			Arrays.sort(properties, new Comparator<PropertyDefine>(){

				@Override
				public int compare(PropertyDefine o1, PropertyDefine o2) {
					if(o1.getProperty().sn() < o2.getProperty().sn())
						return -1;
					else if(o1.getProperty().sn() == o2.getProperty().sn())
						return 0;
					else
						return 1;
				}
			});
			
			this.properties = properties;
			
		}catch(Exception ex){
			
		}
	}
}
