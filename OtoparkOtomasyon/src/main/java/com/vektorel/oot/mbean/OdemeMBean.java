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
import com.vektorel.oot.entity.Odeme;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.service.OdemeService;
import com.vektorel.oot.util.PagingResult;

@ManagedBean(name="odemeBean")
@ViewScoped
public class OdemeMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4801225975212400121L;

	@ManagedProperty(value="#{odemeService}")
	private transient OdemeService odemeService;
	
	@ManagedProperty(value="#{kisiService}")
	private transient KisiService kisiService;
	
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	private Odeme odeme;
	private LazyDataModel<Odeme> lazy;

	
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
			if(odeme.getId()==null){
				odemeService.save(odeme);
				messageMBean.mesajKayitBasarili();
			}else{
				odemeService.update(odeme);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seciliKayit(Long id) {
		this.odeme = odemeService.getById(id);
	}
	
	public void seciliyiSil(Long id) {
		odemeService.delete(id);
		messageMBean.mesajSilmeBasarili();
		listele();
	}
	
	public void yeni() {
		odeme=new Odeme();
	}

	public List<Kisi> islemYpnPersAcomp(String query) {
		return kisiService.acomp(query);
	}
	
	private void listele() {
		lazy =new LazyDataModel<Odeme>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Odeme> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PagingResult pagingResult = odemeService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	

	
	/**
	 * Getter/Setter 
	 */
	public Odeme getOdeme() {
		return odeme;
	}
	
	public void setOdeme(Odeme odeme) {
		this.odeme = odeme;
	}
	
	public LazyDataModel<Odeme> getLazy() {
		return lazy;
	}
	
	public void setOdemeService(OdemeService odemeService) {
		this.odemeService = odemeService;
	}
	
	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}
	
	public void setKisiService(KisiService kisiService) {
		this.kisiService = kisiService;
	}
}
