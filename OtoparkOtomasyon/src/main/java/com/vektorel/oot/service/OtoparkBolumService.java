package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.OtoparkBolum;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author takatas
 * 
 */

@Service
public class OtoparkBolumService {

	@Autowired
	private transient BaseDao baseDao;

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
	@Transactional
	public List<OtoparkBolum> getAll(String query) {

		return baseDao.getAll(OtoparkBolum.class);

	}
	
	@Transactional
	public OtoparkBolum getById(Long id) {
		return (OtoparkBolum) baseDao.getById(id, OtoparkBolum.class);
	}
	

	@Transactional
	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(OtoparkBolum.class, first, pageSize, filters);
	}
	

}
