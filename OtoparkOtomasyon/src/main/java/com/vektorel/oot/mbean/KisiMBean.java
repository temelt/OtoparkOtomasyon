package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;

@SuppressWarnings("restriction")
@ManagedBean(name="kisiBean")
@ViewScoped
public class KisiMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 461195612342326214L;

	/**
	 * Properties
	 */
	private KisiService kisiService;
	
	private List<Kisi> kisis;
	private Kisi kisi;

	
	/**
	 * Methods
	 */
	@PostConstruct
	public void init() {
		kisiService = new KisiService();
		yeni();
		listele();
	}
	
	public void kaydet() {
		try {
			if(kisi.getId()==null){
				kisiService.save(kisi);
				mesajGoster("Kayýt", "Kayýt Eklendi");
			}else{
				kisiService.update(kisi);
				mesajGoster("Kayýt", "Kayýt Güncellendi");
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seciliKayit(Long id) {
		this.kisi = kisiService.getById(id);
	}
	
	public void seciliyiSil(Long id) {
		kisiService.delete(id);
		mesajGoster("Kayýt Silindi", "Kayýt No :"+ id);
		listele();
	}
	
	public void yeni() {
		kisi=new Kisi();
	}
	
	private void listele() {
		kisis = kisiService.getAll(null);
		mesajGoster("Kayýtlar Listelendi","Kayýt Sayýsý :"+kisis.size());
	}
	
	public void mesajGoster(String baslik,String detay) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(baslik,  detay) );
	}
	
	/**
	 * Getter/Setter 
	 */
	
	public List<Kisi> getKisis() {
		return kisis;
	}
	
	public Kisi getKisi() {
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
}
