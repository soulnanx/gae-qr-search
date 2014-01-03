package com.qr.entity;

import java.lang.reflect.Field;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class BaseModel<T> {

	public Key save(T t) {

		Entity entity = new Entity(t.getClass().getSimpleName());

		for (Field field : t.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				
				if ((String)field.get(t) != null){
					entity.setProperty(field.getName(), (String)field.get(t));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return DatastoreServiceFactory.getDatastoreService().put(entity);

	}
	
	public Entity findByCode(String code){
		Query q = new Query("Item");
		Query.Filter query_filter = new Query.FilterPredicate("code", Query.FilterOperator.EQUAL, code);
		q.setFilter(query_filter);
		PreparedQuery pq = DatastoreServiceFactory.getDatastoreService().prepare(q);
		List<Entity> lista = pq.asList(FetchOptions.Builder.withDefaults());
		
		Entity entity = lista.iterator().next();
		
		
		return entity;
	}

}
