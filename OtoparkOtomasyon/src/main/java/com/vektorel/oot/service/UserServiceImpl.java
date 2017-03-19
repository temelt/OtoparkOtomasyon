package com.vektorel.oot.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Kullanici;

public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private transient KullaniciService kullaniciService;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Kullanici kullanici = kullaniciService.getUserByUname(username);
		
		//userCheck();
		
		List<GrantedAuthority> list=new ArrayList<>();
		list.add(new GrantedAuthority() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1345634564356L;

			@Override
			public String getAuthority() {
				return "ADMIN";
			}
		});
		
		if(kullanici!=null){
			return new User(kullanici.getUname(), kullanici.getPwd(), true, true, true, true, list);
		}
		throw new UsernameNotFoundException(username);
	}
	
	
	@Transactional
	public void userCheck() {
		Criteria criteria =sessionFactory.openSession().createCriteria(Kullanici.class);
		criteria.setProjection(Projections.rowCount());
		Long kullaniciSayisi = (Long) criteria.uniqueResult();
		if(kullaniciSayisi<1){
			Kullanici kullanici =new Kullanici();
			kullanici.setUname("ttemel");
			kullanici.setPwd("123");
			kullaniciService.save(kullanici);
		}
	}

}
