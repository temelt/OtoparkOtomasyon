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

import com.vektorel.oot.entity.Arac;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 * 
 */
@ManagedBean(name = "aracService")
@ApplicationScoped
public class AracService {

	@ManagedProperty(value = "#{baseDao}")
	private transient BaseDao baseDao;

	public boolean save(Arac entity) throws Exception {
		
		if (entity.getPlaka() == null || entity.getPlaka().trim().equals("")) {
			throw new HRException("Plaka Boþ Olmamalýdýr");
		}
		baseDao.save(entity);
		return true;
	}

	public boolean update(Arac entity) throws Exception {
		if (entity.getPlaka() == null || entity.getPlaka().trim().equals("")) {
			throw new HRException("Plaka Boþ Olmamalýdýr");
		}
		
		baseDao.update(entity);
		return true;
	}

	public boolean delete(Arac entity) {
		baseDao.delete(entity);
		return true;
	}

	public boolean delete(Long entityId) {
		baseDao.delete(entityId, Arac.class);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Arac> getAll(String query) {
		return baseDao.getAll(Arac.class);
	}

	public Arac getById(Long id) {
		return (Arac) baseDao.getById(id, Arac.class);
	}

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(Arac.class, first, pageSize, filters);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Arac> acomp(String query) {
		Session session = baseDao.getOpenSession();
		Criteria criteria = session.createCriteria(Arac.class);
		criteria.add(
				Restrictions.ilike("plaka", query,MatchMode.ANYWHERE)
				);
		
		criteria.setMaxResults(15);
		List lst = criteria.list();
		session.close();
		return lst;
	}
}
