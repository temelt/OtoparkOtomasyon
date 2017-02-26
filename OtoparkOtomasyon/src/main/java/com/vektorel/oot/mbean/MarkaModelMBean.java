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
import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.service.MarkaModelService;
import com.vektorel.oot.util.PagingResult;

@ManagedBean(name = "markaModelBean")
@ViewScoped
public class MarkaModelMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4995887581871456268L;

	@ManagedProperty(value = "#{markaModelService}")
	private transient MarkaModelService markaModelService;

	@ManagedProperty(value = "#{messageBean}")
	private MessageMBean messageMBean;

	private MarkaModel markaModel;
	private LazyDataModel<Kisi> lazy;

	@PostConstruct
	private void init() {
		yeni();
		listele();
	}

	public void kaydet() {
		try {
			if (markaModel.getId() == null) {
				markaModelService.save(markaModel);
				messageMBean.mesajKayitBasarili();
			} else {
				markaModelService.update(markaModel);
				messageMBean.mesajGuncellemeBasarili();
			}
			yeni();
			listele();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void seciliKayit(Long id) {
		this.markaModel = markaModelService.getById(id);
	}

	public void seciliyiSil(Long id) {
		try {
			markaModelService.delete(id);
			messageMBean.mesajSilmeBasarili();
			listele();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void yeni() {
		markaModel = new MarkaModel();
	}

	private void listele() {
		lazy = new LazyDataModel<Kisi>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<Kisi> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				PagingResult pagingResult = markaModelService.getByPaging(
						first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}

	public MarkaModelService getMarkaModelService() {
		return markaModelService;
	}

	public void setMarkaModelService(MarkaModelService markaModelService) {
		this.markaModelService = markaModelService;
	}

	public MessageMBean getMessageMBean() {
		return messageMBean;
	}

	public void setMessageMBean(MessageMBean messageMBean) {
		this.messageMBean = messageMBean;
	}

	public MarkaModel getMarkaModel() {
		return markaModel;
	}

	public void setMarkaModel(MarkaModel markaModel) {
		this.markaModel = markaModel;
	}

	public LazyDataModel<Kisi> getLazy() {
		return lazy;
	}

	public void setLazy(LazyDataModel<Kisi> lazy) {
		this.lazy = lazy;
	}

}
