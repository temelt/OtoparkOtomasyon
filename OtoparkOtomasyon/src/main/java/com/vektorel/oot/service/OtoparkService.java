package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Otopark;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author temelt
 * 
 */
@Service
public class OtoparkService {

	@Autowired
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

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Otopark> getAll(String query) {
		return baseDao.getAll(Otopark.class);
	}

	@Transactional
	public Otopark getById(Long id) {
		return (Otopark) baseDao.getById(id, Otopark.class);
	}

	@Transactional
	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters) {
		return baseDao.getByPaging(Otopark.class, first, pageSize, filters);
	}
	
}
