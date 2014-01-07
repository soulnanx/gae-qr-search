package com.qr.utils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;


public class ParseUtil<T> {
	
	public T getObjectFromEntity(Entity entity, T pojo) {

		if (entity.getKind().equals(pojo.getClass().getSimpleName())) {
			for (Field fieldPojo : pojo.getClass().getDeclaredFields()) {
				try {
					fieldPojo.setAccessible(true);
					
					

					fieldPojo.set(pojo, entity.getProperty(fieldPojo.getName()));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return pojo;
		}
		return null;
	}
	
	public List<T> getListObjectFromListEntity(List<Entity> listEntity, T pojo) {
		List<T> listPojo = new ArrayList<T>();
		
		for (Entity entity : listEntity) {
			
			if (entity.getKind().equals(pojo.getClass().getSimpleName())) {
				for (Field fieldPojo : pojo.getClass().getDeclaredFields()) {
					try {
						fieldPojo.setAccessible(true);
						fieldPojo.set(pojo, entity.getProperty(fieldPojo.getName()));
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				listPojo.add(pojo);
			}
		}

		return listPojo;
	}
}
