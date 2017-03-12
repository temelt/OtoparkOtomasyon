package com.vektorel.oot.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Kullanici;
import com.vektorel.oot.service.KullaniciService;

@Controller("loginBean")
@Scope("session")
public class LoginMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 867897543045420875L;
	
	public LoginMBean() {
		System.out.println("LoginMBean created..");
	}
	
	@Autowired
	private transient KullaniciService kullaniciService ;

	private String uname;
	private String pass;

	@PostConstruct
	private void init() {
		System.out.println("Login bean initialized...");
	}
	
	public void login() {
		try {
			Kullanici kullanici = kullaniciService.getUserByUnameAndPass(uname, pass);
			if(kullanici!=null){
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
				session.setAttribute("username", kullanici.getUname());
				FacesContext.getCurrentInstance().getExternalContext().redirect("/OtoparkOtomasyon/secure/dashboard.jsf?faces-redirect=true");
			}else{
				FacesContext.getCurrentInstance().getExternalContext().redirect("/OtoparkOtomasyon/login.jsf?faces-redirect=true&error=true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String logOut() {
		try {			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			session.invalidate();			
			FacesContext.getCurrentInstance().getExternalContext().redirect("/OtoparkOtomasyon/login.jsf?faces-redirect=true&logout=true");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "";
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
