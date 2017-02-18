package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.vektorel.oot.util.KisiListe;
import com.vektorel.oot.util.Ogrenci;

@ManagedBean(name="indexBean")
@ViewScoped
public class IndexManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3330075364711917067L;

	private String mesaj="JSF Uygulamasý";
	private List<Ogrenci> list=null;
	
	@PostConstruct
	private void init() {
		System.out.println("Nesne Oluþtu");
		list=KisiListe.ogrenciler;
	}
	
	public void butonaTiklandi() {
		System.out.println(mesaj+"    Butona Týklandý...");
	}
	
	public String getMesaj() {
		return mesaj;
	}
	
	public void setMesaj(String mesaj) {
		this.mesaj = mesaj;
	}
	
	public List<Ogrenci> getList() {
		return list;
	}
	
}


