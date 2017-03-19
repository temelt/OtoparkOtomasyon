package com.vektorel.oot.util;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("baseDao")
public class BaseDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional(readOnly=false)
	public boolean save(Object entity) throws Exception {
		getCurrentSession().save(entity);
		return true;
	}

	@Transactional(readOnly=false)
	public boolean update(Object entity) {
		getCurrentSession().update(entity);
		return true;
	}

	@Transactional(readOnly=false)
	public boolean delete(Object entity) {
		getCurrentSession().delete(entity);
		return true;
	}

	@Transactional(readOnly=false)
	@SuppressWarnings({ "rawtypes" })
	public boolean delete(Long entityId,Class cls) {
		getCurrentSession().delete(getById(entityId,cls));;
		return true;
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	public List getAll(Class cls) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	public Object getById(Long id,Class cls) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq("id", id));
		return criteria.uniqueResult();
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	public PagingResult getByPaging(Class cls,int first, int pageSize, Map<String, Object> filters) {
		PagingResult result = new PagingResult();
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.setProjection(Projections.rowCount());
		result.setRowCount((Long) criteria.uniqueResult());

		criteria.setProjection(null);

		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		
		result.setList(criteria.list());
		return result;
	}
	
	@Transactional
	@SuppressWarnings({ "rawtypes" })
	public Object getMarkaAd(String tanim,Class cls) {
		Session session = getCurrentSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq("tanim", tanim));
		return criteria.uniqueResult();
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
