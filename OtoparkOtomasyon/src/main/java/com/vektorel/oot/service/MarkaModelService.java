package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
		
		if(entity.getTanim() == null || entity.getTanim().trim().equals("")){
			throw new HRException("Taným Olmamalýdýr");
		}

		baseDao.save(MarkaModel.class);

		return true;

	}

	public boolean update(MarkaModel entity) throws Exception {

		if(entity.getTanim() == null || entity.getTanim().trim().equals("")){
			throw new HRException("Taným Olmamalýdýr");
		}		
		baseDao.update(MarkaModel.class);
		return true;

	}

	public boolean delete(MarkaModel entity) throws Exception {

		baseDao.delete(MarkaModel.class);
		return true;

	}

	public boolean delete(Long entityId) throws Exception {

		baseDao.delete(MarkaModel.class);
		return true;

	}

	@SuppressWarnings("unchecked")
	public List<MarkaModel> getAll(String query) {

		return baseDao.getAll(MarkaModel.class);

	}

	public PagingResult getByPaging(int first, int pageSize,
			Map<String, Object> filters) {
		return baseDao.getByPaging(MarkaModel.class, first, pageSize, filters);
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public MarkaModel getById(Long id) {
		return (MarkaModel) baseDao.getById(id, MarkaModel.class);
	}

}
