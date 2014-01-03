package com.qr.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.qr.dao.ItemDao;
import com.qr.entity.Item;
import com.qr.utils.ParseUtil;

@Api(name = "item")
public class itemService {
	
	@ApiMethod(path = "find", httpMethod = ApiMethod.HttpMethod.GET)
	public Item find(@Named("id") Long id){
		return new ItemDao().findById(id);
	}
	
	@ApiMethod(path = "findByCode", httpMethod = ApiMethod.HttpMethod.GET)
	public List<Item> findByCode(@Named("code") String code){
		Entity entity = new Item().findByCode(code);
		List<Item> lista = new ArrayList<Item>();
		lista.add(new ParseUtil<Item>().getObjectFromEntity(entity, new Item()));
		return lista;
	}
	
	@ApiMethod(path = "save", httpMethod = ApiMethod.HttpMethod.POST)
	public Text save(Item item){
		Key id = item.save(item);
		return new Text(id.getId() + "");
	}
	
	@ApiMethod(path = "update", httpMethod = ApiMethod.HttpMethod.PUT)
	public void update(Item item){
		new ItemDao().update(item);
	}
}
