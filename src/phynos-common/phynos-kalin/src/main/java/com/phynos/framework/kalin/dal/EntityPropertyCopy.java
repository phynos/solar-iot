package com.phynos.framework.kalin.dal;

import java.lang.reflect.Field;

public class EntityPropertyCopy {

	public static void copyPropertiesValueTo(Object sourceEntity, Object targetEntity, boolean copyNullValue){
		Entity entity1 = sourceEntity.getClass().getAnnotation(Entity.class);
		Entity entity2 = targetEntity.getClass().getAnnotation(Entity.class);
		
		if(entity1 == null)
			throw new IllegalArgumentException("Argument sourceEntity is not an entity");
		if(entity2 == null)
			throw new IllegalArgumentException("Argument targetEntity is not an entity");
		
		if(sourceEntity.getClass() != targetEntity.getClass())
			throw new IllegalArgumentException("Class of sourceEntity is not equals to the Class of targetEntity.");
		
		Field[] fields = sourceEntity.getClass().getDeclaredFields();
		for(Field field : fields){
			Property property = field.getAnnotation(Property.class);
			if(property == null)
				continue;
			
			field.setAccessible(true);
			
			try {
				Object value = field.get(sourceEntity);
				
				if(value == null && copyNullValue == false)
					continue;
				
				field.set(targetEntity, value);
			} catch (Exception e) {
			} 
		}
	}
}
