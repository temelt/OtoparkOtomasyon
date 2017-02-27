package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.OtoparkUyelik;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author ugur
 * 
 */
@ManagedBean(name = "uyeService")
@ApplicationScoped
public class OtoparkUyelikService {

	@ManagedProperty(value = "#{baseDao}")
	private BaseDao baseDao;

	public boolean save(OtoparkUyelik entity) throws Exception {
//		if (entity.getArac() == null) {
//			throw new HRException("Lütfen Aracý giriniz");
//		}
//		if (entity.getKisi() == null) {
//			throw new HRException("Lütfen Kisi bilgilerini giriniz");
//		}
		baseDao.save(entity);
		return true;
	}

	public boolean update(OtoparkUyelik entity) throws Exception {
		if (entity.getArac() == null) {
			throw new HRException("Lütfen Aracý giriniz");
		}
		if (entity.getKisi() == null) {
			throw new HRException("Lütfen Kisi bilgilerini giriniz");
		}
		baseDao.save(entity);
		return true;
	}

	public boolean delete(OtoparkUyelik entity) {
		baseDao.delete(entity);
		return true;
	}

	public boolean delete(Long entityId) {
		baseDao.delete(entityId, OtoparkUyelik.class);
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<OtoparkUyelik> getAll(String query) {
		return baseDao.getAll(OtoparkUyelik.class);
	}

	public OtoparkUyelik getById(Long id) {
		return (OtoparkUyelik) baseDao.getById(id, OtoparkUyelik.class);
	}

	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(OtoparkUyelik.class, first, pageSize, filters);
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
