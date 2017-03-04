package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.Il;
import com.vektorel.oot.entity.Ilce;
import com.vektorel.oot.service.YerlesimService;
import com.vektorel.oot.util.YerlesimTip;

@ManagedBean(name="yerlesimMBean")
@ApplicationScoped
public class YerlesimMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5719769023659204319L;

	@ManagedProperty(value="#{yerlesimService}")
	private transient YerlesimService yerlesimService;
	
	private List<Il> ilListesi;
	private List<Ilce> ilceListesi;
		
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	private void init() {
		ilListesi=yerlesimService.getAll(YerlesimTip.IL);
	}
	
	public List<Il> getIlListesi() {
		return ilListesi;
	}
	
	public List<Ilce> getIlceListesi() {
		return ilceListesi;
	}
	
	public void setYerlesimService(YerlesimService yerlesimService) {
		this.yerlesimService = yerlesimService;
	}

	public void ilChange(Long id) {
		ilceListesi=yerlesimService.getAllByIlId(id);
	}
}
