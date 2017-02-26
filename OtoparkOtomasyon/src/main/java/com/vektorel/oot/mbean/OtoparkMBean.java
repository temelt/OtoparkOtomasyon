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

import com.vektorel.oot.entity.Otopark;
import com.vektorel.oot.service.OtoparkService;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 *
 */
@ManagedBean(name="otoparkBean")
@ViewScoped
public class OtoparkMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 461195612342326214L;

	/**
	 * Properties
	 */
	@ManagedProperty(value="#{otoparkService}")
	private transient OtoparkService otoparkService;
	
	@ManagedProperty(value="#{messageBean}")
	private MessageMBean messageMBean;
	
	private Otopark otopark;
	private LazyDataModel<Otopark> lazy;

	
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
			if(otopark.getId()==null){
				otoparkService.save(otopark);
				messageMBean.mesajKayitBasarili();
			}else{
				otoparkService.update(otopark);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void seciliKayit(Long id) {
		this.otopark = otoparkService.getById(id);
	}
	
	public void seciliyiSil(Long id) {
		otoparkService.delete(id);
		messageMBean.mesajSilmeBasarili();
		listele();
	}
	
	public void yeni() {
		otopark=new Otopark();
	}

	
	private void listele() {
		lazy =new LazyDataModel<Otopark>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Otopark> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
				
				PagingResult pagingResult = otoparkService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}
	

	
	/**
	 * Getter/Setter 
	 */
	public Otopark getOtopark() {
		return otopark;
	}
	
	public void setOtopark(Otopark otopark) {
		this.otopark = otopark;
	}
	
	public LazyDataModel<Otopark> getLazy() {
		return lazy;
	}
	
	public void setOtoparkService(OtoparkService otoparkService) {
		this.otoparkService = otoparkService;
	}
	
	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}
}
