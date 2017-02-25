package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.util.PagingResult;

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
	private transient KisiService kisiService;
	
	private Kisi kisi;
	private LazyDataModel<Kisi> lazy;

	
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
		lazy =new LazyDataModel<Kisi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Kisi> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PagingResult pagingResult = kisiService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	
	public void mesajGoster(String baslik,String detay) {
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(baslik,  detay) );
	}
	
	/**
	 * Getter/Setter 
	 */
	public Kisi getKisi() {
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
	public LazyDataModel<Kisi> getLazy() {
		return lazy;
	}
}
