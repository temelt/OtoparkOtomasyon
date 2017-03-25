package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.OrderUtil;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 * 
 */
@Service
public class KisiService {

	@Autowired
	private transient BaseDao baseDao;

	public boolean save(Kisi entity) throws Exception {
		if (entity.getAd() == null || entity.getAd().trim().equals("")) {
			throw new HRException("Kullanýcý Adý Boþ Olmamalýdýr");
		}
		if (entity.getSoyad() == null || entity.getSoyad().trim().equals("")) {
			throw new HRException("Kullanýcý Þifre Boþ Olmamalýdýr");
		}
		baseDao.save(entity);
		return true;
	}

	@Async
	public void asyncMetod() {
		for (int i = 0; i < 1000000; i++) {
			System.out.println("Mesaj" + i);
		}

	}

	public boolean update(Kisi entity) throws Exception {
		if (entity.getAd() == null || entity.getAd().trim().equals("")) {
			throw new HRException("Kullanýcý Adý Boþ Olmamalýdýr");
		}
		if (entity.getSoyad() == null || entity.getSoyad().trim().equals("")) {
			throw new HRException("Kullanýcý Þifre Boþ Olmamalýdýr");
		}
		baseDao.update(entity);
		return true;
	}

	public boolean delete(Kisi entity) {
		baseDao.delete(entity);
		return true;
	}

	public boolean delete(Long entityId) {
		baseDao.delete(entityId, Kisi.class);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Kisi> getAll(String query) {
		return baseDao.getAll(Kisi.class);
	}

	public Kisi getById(Long id) {
		return (Kisi) baseDao.getById(id, Kisi.class);
	}
	
	@Transactional
	public Kisi getByTC(Long tc) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Kisi.class);
		criteria.add(Restrictions.eq("tc",tc));
		return (Kisi) criteria.uniqueResult();
	}

	@Transactional
	public PagingResult getByPaging(int first, int pageSize,
			Map<String, Object> filters, OrderUtil order) {
		PagingResult result = new PagingResult();
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Kisi.class);

		if (filters.containsKey("ad")) {
			criteria.add(Restrictions.ilike("ad", filters.get("ad").toString(),
					MatchMode.ANYWHERE));
		}

		if (filters.containsKey("anaAdi")) {
			criteria.add(Restrictions.ilike("anaAdi", filters.get("anaAdi")
					.toString(), MatchMode.ANYWHERE));
		}

		if (filters.containsKey("soyad")) {
			criteria.add(Restrictions.ilike("soyad", filters.get("soyad")
					.toString(), MatchMode.ANYWHERE));
		}

		if (filters.containsKey("il.ad")) {
			Criteria crt = criteria.createAlias("il", "ilAls");
			crt.add(Restrictions.ilike("ilAls.ad", filters.get("il.ad")
					.toString(), MatchMode.ANYWHERE));
		}

		// Ýlçesinin ilinin adýný sorgulamak için
		// if(filters.containsKey("il.ad")){
		// Criteria crt = criteria.createAlias("ilce", "alias");
		// Criteria crt2 = criteria.createAlias("alias.il", "ilt");
		// crt2.add(Restrictions.ilike("ilt.ad",
		// filters.get("il.ad").toString(),MatchMode.ANYWHERE));
		// }

		criteria.setProjection(Projections.rowCount());
		result.setRowCount((Long) criteria.uniqueResult());

		criteria.setProjection(null);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		if (order.getField() != null) {
			if (order.getOrderType() == OrderUtil.OrderType.ASC)
				criteria.addOrder(Order.asc(order.getField()));
			else
				criteria.addOrder(Order.desc(order.getField()));
		}

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		result.setList(criteria.list());
		return result;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Kisi> acomp(String query) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Kisi.class);
		criteria.add(Restrictions.or(
				Restrictions.ilike("ad", query, MatchMode.ANYWHERE),
				Restrictions.ilike("soyad", query, MatchMode.ANYWHERE)));

		criteria.setMaxResults(15);
		List lst = criteria.list();
		return lst;
	}
}
