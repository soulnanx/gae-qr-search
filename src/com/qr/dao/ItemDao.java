package com.qr.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.qr.entity.Item;
import com.qr.entityManager.EMFService;

public class ItemDao {

	public void save(Item item) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			item.setCreateDate(new Date());
			em.persist(item);
			item = (Item) em.createQuery("select i from Item i ORDER BY i.createDate DESC LIMIT 1").getResultList().get(0);
			
			em.close();
		}
	}

	public Item findById(Long id) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select i from Item i where i.id = :id");
		q.setParameter("id", id);
		return (Item) q.getSingleResult();
	}

	public void update(Item item) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			item.setUpdateDate(new Date());
			em.refresh(item);
			em.close();
		}
	}

	public Item findByCode(String code) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select i from Item i where i.code = :code");
		q.setParameter("code", code);
		return (Item) q.getSingleResult();
	}

}
