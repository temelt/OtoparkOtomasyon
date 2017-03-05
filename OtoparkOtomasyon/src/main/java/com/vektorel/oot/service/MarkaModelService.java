package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author takatas
 * 
 */

@ManagedBean(name = "markaModelService")
@ApplicationScoped
public class MarkaModelService {

	@ManagedProperty(value = "#{baseDao}")
	private BaseDao baseDao;

	public boolean save(MarkaModel entity) throws Exception {

		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Taným Boþ Olmamalýdýr");
		}

		baseDao.save(entity);

		return true;

	}

	public boolean update(MarkaModel entity) throws Exception {

		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Taným Boþ Olmamalýdýr");
		}
		baseDao.update(entity);
		return true;

	}

	public boolean delete(MarkaModel entity) throws Exception {

		baseDao.delete(entity);
		return true;

	}

	public boolean delete(Long entityId) throws Exception {

		baseDao.delete(entityId, MarkaModel.class);
		return true;

	}

	@SuppressWarnings("unchecked")
	public List<MarkaModel> getAll(String query) {

		return baseDao.getAll(MarkaModel.class);

	}

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(MarkaModel.class, first, pageSize, filters);
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public MarkaModel getById(Long id) {
		return (MarkaModel) baseDao.getById(id, MarkaModel.class);
	}
	
	public MarkaModel getMarkaId(String marka) {
		return (MarkaModel) baseDao.getMarkaAd(marka, MarkaModel.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<MarkaModel> getMarkaList(MarkaModel marka){
		
		return baseDao.getMarka(MarkaModel.class); 
	}
	
	@SuppressWarnings("unchecked")
	public List<MarkaModel> getModel(Long id){
		
		return baseDao.getModel(id, MarkaModel.class); 
	}

	public List<MarkaModel> markaModelAcomp(String term) {
		Session session = baseDao.getOpenSession();
		Criteria criteria = session.createCriteria(MarkaModel.class);
		criteria.add(
				Restrictions.ilike("tanim", term ,MatchMode.ANYWHERE)
				);
		
		criteria.setMaxResults(15);
		List lst = criteria.list();
		session.close();
		return lst;
	}
	


}
