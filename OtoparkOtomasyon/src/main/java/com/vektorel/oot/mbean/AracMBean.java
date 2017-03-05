package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.vektorel.oot.entity.Arac;
import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.service.AracService;
import com.vektorel.oot.service.MarkaModelService;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 *
 */
@ManagedBean(name="aracBean")
@ViewScoped
public class AracMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 461195612342326214L;

	/**
	 * Properties
	 */
	@ManagedProperty(value="#{aracService}")
	private transient AracService aracService;
	
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	@ManagedProperty(value="#{markaModelService}")
	private MarkaModelService markaModelService;
	
	
	
	private Arac arac;
	private LazyDataModel<Arac> lazy;

	
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
			if(arac.getId()==null){
				aracService.save(arac);
				messageMBean.mesajKayitBasarili();
			}else{
				aracService.update(arac);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seciliKayit(Long id) {
		this.arac = aracService.getById(id);
	}
	
	public void seciliyiSil(Long id) {
		aracService.delete(id);
		messageMBean.mesajSilmeBasarili();
		listele();
	}
	
	public void yeni() {
		arac=new Arac();
	}

	public List<MarkaModel> markaModelAcomp(String term) {
		return markaModelService.markaModelAcomp(term);
	}
	
	private void listele() {
		lazy =new LazyDataModel<Arac>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Arac> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PagingResult pagingResult = aracService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	

	
	/**
	 * Getter/Setter 
	 */
	public Arac getArac() {
		return arac;
	}
	
	public void setArac(Arac arac) {
		this.arac = arac;
	}
	
	public LazyDataModel<Arac> getLazy() {
		return lazy;
	}
	
	public void setAracService(AracService aracService) {
		this.aracService = aracService;
	}
	
	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}
	
	public void setMarkaModelService(MarkaModelService markaModelService) {
		this.markaModelService = markaModelService;
	}
}
