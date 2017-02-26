package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.Odeme;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author eaytac
 * 
 */

@ManagedBean(name = "odemeService")
@ApplicationScoped
public class OdemeService {
	
	@ManagedProperty(value = "#{baseDao}")
	private BaseDao baseDao;
	
	public boolean save(Odeme entity) throws Exception {
		baseDao.save(entity);
		return true;
	}

	public boolean update(Odeme entity) throws Exception {
		baseDao.update(entity);
		return true;
	}

	public boolean delete(Odeme entity) {
		baseDao.delete(entity);
		return true;
	}

	public boolean delete(Long entityId) {
		baseDao.delete(entityId, Odeme.class);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Odeme> getAll(String query) {
		return baseDao.getAll(Odeme.class);
	}

	public Odeme getById(Long id) {
		return (Odeme) baseDao.getById(id, Odeme.class);
	}

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(Odeme.class, first, pageSize, filters);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
