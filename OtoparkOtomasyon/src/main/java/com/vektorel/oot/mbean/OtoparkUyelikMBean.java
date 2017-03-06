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
import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.entity.OtoparkUyelik;
import com.vektorel.oot.service.AracService;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.service.OtoparkUyelikService;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author ugur
 *
 */
@ManagedBean(name="uyeBean")
@ViewScoped
public class OtoparkUyelikMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 461195612342326214L;

	/**
	 * Properties
	 */
	@ManagedProperty(value="#{uyeService}")
	private transient OtoparkUyelikService uyelikService;
	
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	@ManagedProperty(value="#{yerlesimMBean}")
	private YerlesimMBean yerlesimMBean;
	
	@ManagedProperty(value="#{aracService}")
	private transient AracService aracService;
	
	@ManagedProperty(value="#{kisiService}")
	private transient KisiService kisiService;
	
	private OtoparkUyelik uye;
	private LazyDataModel<OtoparkUyelik> lazy;

	


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
			if(uye.getId()==null){
				uyelikService.save(uye);
				messageMBean.mesajKayitBasarili();
			}else{
				uyelikService.update(uye);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seciliKayit(Long id) {
		this.uye = uyelikService.getById(id);
	}
	
	public void seciliyiSil(Long id) {
		uyelikService.delete(id);
		messageMBean.mesajSilmeBasarili();
		listele();
	}
	
	public void yeni() {
		uye=new OtoparkUyelik();
	}

	public List<Arac> aracAcomp(String query){
		return aracService.acomp(query);
	}
	
	
	public List<Kisi> kisiAcomp(String query){
		return kisiService.acomp(query);
	}
	
	
	public void ilChange() {
		yerlesimMBean.ilChange(this.uye.getIl().getId());
	}
	
	private void listele() {
		lazy =new LazyDataModel<OtoparkUyelik>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<OtoparkUyelik> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PagingResult pagingResult = uyelikService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	

	
	/**
	 * Getter/Setter 
	 */
	public OtoparkUyelik getUye() {
		return uye;
	}

	public void setUye(OtoparkUyelik uye) {
		this.uye = uye;
	}

	public LazyDataModel<OtoparkUyelik> getLazy() {
		return lazy;
	}
			
	public void setUyelikService(OtoparkUyelikService uyelikService) {
		this.uyelikService = uyelikService;
	}

	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}

	public void setYerlesimMBean(YerlesimMBean yerlesimMBean) {
		this.yerlesimMBean = yerlesimMBean;
	}

	public void setAracService(AracService aracService) {
		this.aracService = aracService;
	}
	
	public void setKisiService(KisiService kisiService) {
		this.kisiService = kisiService;
	}
	
	
}

