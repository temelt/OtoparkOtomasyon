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

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.util.OrderUtil;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 *
 */
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
	@ManagedProperty(value="#{kisiService}")
	private transient KisiService kisiService;
	
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	@ManagedProperty(value="#{yerlesimMBean}")
	private YerlesimMBean yerlesimMBean;
	
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
		return kisi;
	}
	
	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}
	
	public LazyDataModel<Kisi> getLazy() {
		return lazy;
	}
	
	public void setKisiService(KisiService kisiService) {
		this.kisiService = kisiService;
	}
	
	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}
	public void setYerlesimMBean(YerlesimMBean yerlesimMBean) {
		this.yerlesimMBean = yerlesimMBean;
	}
}
