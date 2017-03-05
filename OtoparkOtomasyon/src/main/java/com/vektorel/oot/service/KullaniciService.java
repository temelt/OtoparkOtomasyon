package com.vektorel.oot.service;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.vektorel.oot.entity.Kullanici;
import com.vektorel.oot.util.BaseDao;


@ManagedBean(name = "kullaniciService", eager=true)
@ApplicationScoped
public class KullaniciService {


	@ManagedProperty(value = "#{baseDao}")
	private transient BaseDao baseDao;
	
	public KullaniciService() {
		System.out.println("KullaniciService created..");
	}
	
	public Kullanici getUserByUnameAndPass(String uname,String pwd) {
		Session session = baseDao.getOpenSession();
		Criteria criteria = session.createCriteria(Kullanici.class);
		criteria.add(Restrictions.eq("uname", uname));
		criteria.add(Restrictions.eq("pwd", pwd));
		Kullanici kullanici = (Kullanici) criteria.uniqueResult();
		session.close();
		return kullanici;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
