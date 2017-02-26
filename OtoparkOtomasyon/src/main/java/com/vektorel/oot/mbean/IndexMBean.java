package com.vektorel.oot.mbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * 
 * @author temelt
 *
 */
@ManagedBean(name="indexBean")
@ViewScoped
public class IndexMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330075364711917067L;

	/**
	 * Properties
	 */
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	private String aktifKullanici;
	
	@PostConstruct
	private void init() {		
		aktifKullanici="Admin Kullanýcý";
		messageMBean.mesajUyariGoster("", "HOÞGELDÝNÝZ");
	}
	
	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}
	
	public String getAktifKullanici() {
		return aktifKullanici;
	}
	
}


