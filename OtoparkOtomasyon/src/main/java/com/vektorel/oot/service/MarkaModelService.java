package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author takatas
 * 
 */

@Service
public class MarkaModelService {

	@Autowired
	private transient BaseDao baseDao;

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


	@Transactional
	public MarkaModel getById(Long id) {
		return (MarkaModel) baseDao.getById(id, MarkaModel.class);
	}
	
	@Transactional
	public MarkaModel getMarkaId(String marka) {
		return (MarkaModel) baseDao.getMarkaAd(marka, MarkaModel.class);
	}
	


	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<MarkaModel> markaModelAcomp(String term) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(MarkaModel.class);
		criteria.add(
				Restrictions.ilike("tanim", term ,MatchMode.ANYWHERE)
				);
		
		criteria.setMaxResults(15);
		List lst = criteria.list();
		return lst;
	}
	


}
