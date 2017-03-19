package com.vektorel.oot.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Kullanici;
import com.vektorel.oot.util.BaseDao;

@Service
public class KullaniciService {


	@Autowired
	private transient BaseDao baseDao;
	
	Md5PasswordEncoder encoder=new Md5PasswordEncoder();
	
	public KullaniciService() {
		System.out.println("KullaniciService created..");
	}
	
	
	public boolean save(Kullanici entity) {	
		try {
			entity.setPwd(encoder.encodePassword(entity.getPwd(), null));
			baseDao.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
	
	@Transactional
	public Kullanici getUserByUname(String uname) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Kullanici.class);
		criteria.add(Restrictions.eq("uname", uname));
		Kullanici kullanici = (Kullanici) criteria.uniqueResult();
		return kullanici;
	}
	

}
