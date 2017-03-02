package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;

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
	private LazyDataModel<MarkaModel> lazy;
	private List <String> liste = new ArrayList<>();
	private List<MarkaModel> markaListe;
	private String deger;
	
	private TreeNode root;

	@PostConstruct
	private void init() {
		yeni();
		listele();
		markaComboDoldur();
		markaListe = markaModelService.getMarkaList(markaModel);
		System.out.println("---");
	}

	public void kaydet() {

		MarkaModel marka = markaModelService.getMarkaId(deger);

		try {
			if (markaModel.getId() == null) {
				markaModel.setMarkaModel(marka);
				markaModelService.save(markaModel);
				messageMBean.mesajKayitBasarili();
			} else {
				markaModel.setMarkaModel(marka);
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
		lazy = new LazyDataModel<MarkaModel>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133036500137030751L;

			@SuppressWarnings("unchecked")
			@Override
			public List<MarkaModel> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				PagingResult pagingResult = markaModelService.getByPaging(first, pageSize, filters);
				this.setRowCount(pagingResult.getRowCount().intValue());
				return pagingResult.getList();
			}
		};
	}

	private void markaComboDoldur() {

		List<MarkaModel> markaListe = markaModelService.getMarkaList(markaModel);
		for (MarkaModel markaModel : markaListe) {
			 liste.add(markaModel.getTanim());

		}
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

	public LazyDataModel<MarkaModel> getLazy() {
		return lazy;
	}

	public void setLazy(LazyDataModel<MarkaModel> lazy) {
		this.lazy = lazy;
	}

//	public Map<String, String> getListe() {
//		return liste;
//	}

	 public void setListe(List<String> liste) {
	 this.liste = liste;
	 }

	 public List<String> getListe() {
		return liste;
	}
	 
	public List<MarkaModel> getMarkaListe() {
		return markaListe;
	}

	public void setDeger(String deger) {
		this.deger = deger;
	}

	public String getDeger() {
		return deger;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	public void setRoot(TreeNode root) {
		this.root = root;
	}

}
