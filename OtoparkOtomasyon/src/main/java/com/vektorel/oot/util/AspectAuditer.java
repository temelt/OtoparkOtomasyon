package com.vektorel.oot.util;

import java.io.Serializable;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vektorel.oot.entity.EBase;
import com.vektorel.oot.mbean.SessionController;

@Component
@Scope("session")
@Aspect
public class AspectAuditer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2922129101596326455L;
	
	@Autowired
	private SessionController sessionController;

	@Before("execution(* com.vektorel.oot.util.BaseDao.save(..))")
	public void beforeSaving(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args[0] instanceof EBase){
			EBase b = (EBase) args[0];
			b.setEkleyen(sessionController.getKullanici().getUname());
			b.setEklemeTarihi(new Date());
		}
	}
	
//	@After("execution(* com.vektorel.oot.*.*.*(..))")
//	public void afterSaving(JoinPoint joinPoint) {
//		System.out.println("afterSaving Çaðrýldý");
//	}
	
	@Before("execution(* com.vektorel.oot.util.BaseDao.update(..))")
	public void beforeUpdating(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if(args[0] instanceof EBase){
			EBase b = (EBase) args[0];
			b.setGuncellemeTarihi(new Date());
			b.setGuncelleyen(sessionController.getKullanici().getUname());
		}
	}
	
	public void afterUpdating() {

	}
}
