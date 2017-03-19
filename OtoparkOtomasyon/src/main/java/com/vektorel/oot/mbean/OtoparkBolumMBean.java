package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.OtoparkBolum;
import com.vektorel.oot.service.OtoparkBolumService;
import com.vektorel.oot.util.PagingResult;

@Controller("otoparkBolumBean")
@Scope("view")
public class OtoparkBolumMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 131393325795319163L;

	@Autowired
	private transient OtoparkBolumService otoparkBolumService;

	@Autowired
	private MessageMBean messageMBean;

	private OtoparkBolum otoparkBolum;
	private List<String> liste = new ArrayList<>();
	private List<OtoparkBolum> otoparkBolumListe;
	private String deger;
	private LazyDataModel<OtoparkBolum> lazy;

	@PostConstruct
	private void init() {
		yeni();
		 listele();
		otoparkBolumComboDoldur();
//		otoparkBolumListe = otoparkBolumService.getMarkaList(otoparkBolum);
		System.out.println("---");
	}

	public void kaydet() {

		// OtoparkBolum otoparkBolum = otoparkBolumService.getMarkaId(deger);

		try {
			if (otoparkBolum.getId() == null) {
				otoparkBolum.setOtoparkBolum(otoparkBolum);
				otoparkBolumService.save(otoparkBolum);
				messageMBean.mesajKayitBasarili();
			} else {
				otoparkBolum.setOtoparkBolum(otoparkBolum);
				otoparkBolumService.update(otoparkBolum);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listele() {
		lazy = new LazyDataModel<OtoparkBolum>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<OtoparkBolum> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				PagingResult pagingResult = otoparkBolumService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}

	public void yeni() {
		otoparkBolum = new OtoparkBolum();
	}

	private void otoparkBolumComboDoldur() {

//		List<OtoparkBolum> otoparkBolumListe = otoparkBolumService.getMarkaList(otoparkBolum);
//		for (OtoparkBolum bolum : otoparkBolumListe) {
//			liste.add(bolum.getTanim());
//
//		}
	}

	public OtoparkBolumService getOtoparkBolumService() {
		return otoparkBolumService;
	}

	public void setOtoparkBolumService(OtoparkBolumService otoparkBolumService) {
		this.otoparkBolumService = otoparkBolumService;
	}

	public MessageMBean getMessageMBean() {
		return messageMBean;
	}

	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}

	public OtoparkBolum getOtoparkBolum() {
		return otoparkBolum;
	}

	public void setOtoparkBolum(OtoparkBolum otoparkBolum) {
		this.otoparkBolum = otoparkBolum;
	}

	public List<String> getListe() {
		return liste;
	}

	public void setListe(List<String> liste) {
		this.liste = liste;
	}

	public List<OtoparkBolum> getOtoparkBolumListe() {
		return otoparkBolumListe;
	}

	public void setOtoparkBolumListe(List<OtoparkBolum> otoparkBolumListe) {
		this.otoparkBolumListe = otoparkBolumListe;
	}

	public String getDeger() {
		return deger;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}

}
