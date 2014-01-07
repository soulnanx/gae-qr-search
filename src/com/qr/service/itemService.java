package com.qr.service;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.Key;
import com.qr.dao.ItemDao;
import com.qr.entity.Item;

@Api(name = "item")
public class itemService {
	
	@ApiMethod(path = "find", httpMethod = ApiMethod.HttpMethod.GET)
	public Item find(@Named("id") Long id){
		return new ItemDao().findById(id);
	}
	
	@ApiMethod(path = "findByCode", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Item> findByCode(@Named("code") String code){
		return new Item().findByCode(code);
	}
	
	@ApiMethod(path = "save", httpMethod = ApiMethod.HttpMethod.POST)
	public Key save(Item item){
		return item.save(item);
	}
	
	@ApiMethod(path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
	public void update(Item item){
		new ItemDao().update(item);
	}
}
