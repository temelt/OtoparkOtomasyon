package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.OtoparkBolum;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author takatas
 * 
 */

@ManagedBean(name = "otoparkBolumService")
@ApplicationScoped
public class OtoparkBolumService {

	@ManagedProperty(value = "#{baseDao}")
	BaseDao baseDao;

	public boolean save(OtoparkBolum entity) throws Exception {

		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Taným Boþ Olmamalýdýr");
		}

		baseDao.save(entity);

		return true;

	}

	public boolean update(OtoparkBolum entity) throws Exception {

		if (entity.getTanim() == null || entity.getTanim().trim().equals("")) {
			throw new HRException("Taným Boþ Olmamalýdýr");
		}
		baseDao.update(entity);
		return true;

	}

	public boolean delete(OtoparkBolum entity) throws Exception {

		baseDao.delete(entity);
		return true;

	}

	public boolean delete(Long entityId) throws Exception {

		baseDao.delete(entityId, OtoparkBolum.class);
		return true;

	}
	
	@SuppressWarnings("unchecked")
	public List<OtoparkBolum> getAll(String query) {

		return baseDao.getAll(OtoparkBolum.class);

	}
	
	public OtoparkBolum getById(Long id) {
		return (OtoparkBolum) baseDao.getById(id, OtoparkBolum.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<OtoparkBolum> getMarkaList(OtoparkBolum otoparkBolum){
		
		return baseDao.getMarka(OtoparkBolum.class); 
	}
	
	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(OtoparkBolum.class, first, pageSize, filters);
	}
	

}
