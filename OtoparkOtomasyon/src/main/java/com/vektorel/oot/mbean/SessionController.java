package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Kullanici;
import com.vektorel.oot.service.KullaniciService;

@Controller("sessionController")
@Scope("session")
public class SessionController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 605316128519185756L;
	@Autowired
	private transient KullaniciService kullaniciService;
	
	Kullanici kullanici;
	List<String> aktifKullaniciYetkileri;
	
	
	public Kullanici getKullanici() {
		if(kullanici==null){
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			kullanici= kullaniciService.getUserByUname(username);
			aktifKullaniciYetkileri = new ArrayList<>();
			aktifKullaniciYetkileri.add("YT0045");
			aktifKullaniciYetkileri.add("YT0046");
		}
		return kullanici;
	}
	
	public boolean hasAuth(String authCode) {
		if(aktifKullaniciYetkileri.contains(authCode))
			return true;
		return false;
	}
	
}
