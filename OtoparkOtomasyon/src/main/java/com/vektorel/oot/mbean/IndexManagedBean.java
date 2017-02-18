package com.vektorel.oot.mbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="indexBean")
@ViewScoped
public class IndexManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330075364711917067L;

	private String mesaj="JSF Uygulamasý";
	
	
	public void butonaTiklandi() {
		System.out.println(mesaj+"    Butona Týklandý...");
	}
	
	public String getMesaj() {
		return mesaj;
	}
	
	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}
	
}


