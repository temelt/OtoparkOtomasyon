package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 * 
 */
@ManagedBean(name = "kisiService")
@ApplicationScoped
public class KisiService {

	@ManagedProperty(value = "#{baseDao}")
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

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(Kisi.class, first, pageSize, filters);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Kisi> acomp(String query) {
		Session session = baseDao.getOpenSession();
		Criteria criteria = session.createCriteria(Kisi.class);
		criteria.add(Restrictions.or(
				Restrictions.ilike("ad", query,MatchMode.ANYWHERE), 
				Restrictions.ilike("soyad", query ,MatchMode.ANYWHERE)
				));
		
		criteria.setMaxResults(15);
		List lst = criteria.list();
		session.close();
		return lst;
	}
}
