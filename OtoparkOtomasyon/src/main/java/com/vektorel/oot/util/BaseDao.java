package com.vektorel.oot.util;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;


@ManagedBean(name="baseDao")
@ApplicationScoped
public class BaseDao {
	
	public boolean save(Object entity) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.save(entity);
		trans.commit();
		session.close();
		return true;
	}

	public boolean update(Object entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.update(entity);
		trans.commit();
		session.close();
		return true;
	}

	public boolean delete(Object entity) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.delete(entity);
		trans.commit();
		session.close();
		return true;
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean delete(Long entityId,Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		session.delete(getById(entityId,cls));
		trans.commit();
		session.close();
		return true;
	}

	@SuppressWarnings({ "rawtypes" })
	public List getAll(Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings({ "rawtypes" })
	public Object getById(Long id,Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq("id", id));
		return criteria.uniqueResult();
	}

	@SuppressWarnings({ "rawtypes" })
	public PagingResult getByPaging(Class cls,int first, int pageSize, Map<String, Object> filters) {
		PagingResult result = new PagingResult();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.setProjection(Projections.rowCount());
		result.setRowCount((Long) criteria.uniqueResult());

		criteria.setProjection(null);

		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);

		
		result.setList(criteria.list());
		session.close();
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public List getMarka(Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.isNull("markaModel"));
		return criteria.list();
	}
	
	@SuppressWarnings("rawtypes")
	public List getModel(Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.isNotNull("markaModel"));
		return criteria.list();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public Object getMarkaAd(String tanim,Class cls) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(cls);
		criteria.add(Restrictions.eq("tanim", tanim));
		return criteria.uniqueResult();
	}

}
