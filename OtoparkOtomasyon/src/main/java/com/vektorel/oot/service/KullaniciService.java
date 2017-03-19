package com.vektorel.oot.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Kullanici;
import com.vektorel.oot.util.BaseDao;

@Service
public class KullaniciService {


	@Autowired
	private transient BaseDao baseDao;
	
	public KullaniciService() {
		System.out.println("KullaniciService created..");
	}
	
	@Transactional
	public Kullanici getUserByUnameAndPass(String uname,String pwd) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Kullanici.class);
		criteria.add(Restrictions.eq("uname", uname));
		criteria.add(Restrictions.eq("pwd", pwd));
		Kullanici kullanici = (Kullanici) criteria.uniqueResult();
		return kullanici;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
