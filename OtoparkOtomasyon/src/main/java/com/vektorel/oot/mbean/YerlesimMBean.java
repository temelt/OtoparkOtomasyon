package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Il;
import com.vektorel.oot.entity.Ilce;
import com.vektorel.oot.service.YerlesimService;
import com.vektorel.oot.util.YerlesimTip;

@Controller("yerlesimMBean")
@Scope("session")
public class YerlesimMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5719769023659204319L;

	@Autowired
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
	
	public void ilChange(Long id) {
		ilceListesi=yerlesimService.getAllByIlId(id);
	}
}
