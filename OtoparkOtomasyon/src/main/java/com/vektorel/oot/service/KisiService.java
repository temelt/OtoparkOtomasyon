package com.vektorel.oot.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.HibernateUtil;


public class KisiService {
	
	    public boolean save(Kisi entity) throws Exception {
	        if(entity.getAd()==null || entity.getAd().trim().equals("")){
	            throw  new HRException("Kullanýcý Adý Boþ Olmamalýdýr");
	        }
	        if(entity.getSoyad()==null || entity.getSoyad().trim().equals("")){
	            throw  new HRException("Kullanýcý Þifre Boþ Olmamalýdýr");
	        }
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        session.save(entity);
	        trans.commit();
	        session.close();
	        return true;
	    }

	    public boolean update(Kisi entity) {
	        
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        session.update(entity);
	        trans.commit();
	        session.close();
	        return true;
	    }

	    public boolean delete(Kisi entity) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        session.delete(entity);
	        trans.commit();
	        session.close();
	        return true;
	    }
	    
	    public boolean delete(Long entityId) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction trans = session.beginTransaction();
	        Kisi kisi = getById(entityId);
	        session.delete(kisi);
	        trans.commit();
	        session.close();
	        return true;
	    }

	    @SuppressWarnings("unchecked")
		public List<Kisi> getAll(String query) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Criteria criteria = session.createCriteria(Kisi.class);
	        if(query!=null){
	            criteria.add(Restrictions.or(Restrictions.ilike("username", query,MatchMode.ANYWHERE),
	                    Restrictions.ilike("adSoyad", query,MatchMode.ANYWHERE)));
	        }
	        criteria.addOrder(Order.asc("id"));
	        return criteria.list();
	    }

	    public Kisi getById(Long id) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Criteria criteria = session.createCriteria(Kisi.class);
	        criteria.add(Restrictions.eq("id", id));
	        return (Kisi) criteria.uniqueResult();
	    }
	}