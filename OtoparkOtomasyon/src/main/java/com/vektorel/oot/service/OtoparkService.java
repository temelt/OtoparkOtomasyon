package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.Otopark;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 * 
 */
@ManagedBean(name = "otoparkService")
@ApplicationScoped
public class OtoparkService {

	@ManagedProperty(value = "#{baseDao}")
	private BaseDao baseDao;

	public boolean save(Otopark entity) throws Exception {
		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Otopark Adý Boþ Olmamalýdýr");
		}
		baseDao.save(entity);
		return true;
	}

	public boolean update(Otopark entity) throws Exception {
		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Otopark Adý Boþ Olmamalýdýr");
		}
		baseDao.update(entity);
		return true;
	}

	public boolean delete(Otopark entity) {
		baseDao.delete(entity);
		return true;
	}

	public boolean delete(Long entityId) {
		baseDao.delete(entityId, Otopark.class);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Otopark> getAll(String query) {
		return baseDao.getAll(Otopark.class);
	}

	public Otopark getById(Long id) {
		return (Otopark) baseDao.getById(id, Otopark.class);
	}

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(Otopark.class, first, pageSize, filters);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
