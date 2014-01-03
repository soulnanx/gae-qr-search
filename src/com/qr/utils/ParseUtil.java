package com.qr.utils;
import java.lang.reflect.Field;

import com.google.appengine.api.datastore.Entity;


public class ParseUtil<T> {
	
	public T getObjectFromEntity(Entity entity, T pojo) {

		if (entity.getClass().getSimpleName().equals(pojo.getClass().getSimpleName())) {
			for (Field fieldPojo : pojo.getClass().getDeclaredFields()) {
				try {
					fieldPojo.setAccessible(true);

					fieldPojo.set(fieldPojo.getType(), entity.getProperty(fieldPojo.getName()));

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return pojo;
		}
		return null;
	}
}
