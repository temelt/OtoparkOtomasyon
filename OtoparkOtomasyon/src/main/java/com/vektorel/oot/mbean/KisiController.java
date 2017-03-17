package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.util.OrderUtil;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 *
 */
@Controller("kisiBean")
@Scope("session")
public class KisiController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 461195612342326214L;

	/**
	 * Properties
	 */
	@Autowired
	private transient KisiService kisiService;
	
	@Autowired
	private transient MessageMBean messageMBean;
	
	@Autowired
	private transient YerlesimMBean yerlesimMBean;
	
	
	private Kisi kisi;
	private LazyDataModel<Kisi> lazy;

	
	/**
	 * Methods
	 */
	@PostConstruct
	public void init() {
		yeni();
		listele();
	}
	
	public void kaydet() {
		try {
			if(kisi.getId()==null){
				kisiService.save(kisi);
				messageMBean.mesajKayitBasarili();
			}else{
				kisiService.update(kisi);
				messageMBean.mesajGuncellemeBasarili();
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
		messageMBean.mesajSilmeBasarili();
		listele();
	}
	
	public void yeni() {
		kisi=new Kisi();
	}

	public void ilChange() {
		yerlesimMBean.ilChange(this.kisi.getIl().getId());
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
				
				PagingResult pagingResult = kisiService.getByPaging(first, pageSize, filters,new OrderUtil(sortField, sortOrder));
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	

	
	/**
	 * Getter/Setter 
	 */
	public Kisi getKisi() {
		if(kisi==null)
			kisi=new Kisi();
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
	public LazyDataModel<Kisi> getLazy() {
		return lazy;
	}
	
}