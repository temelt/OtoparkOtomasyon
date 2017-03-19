package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Odeme;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.OrderUtil;
import com.vektorel.oot.util.PagingResult;

/**
 * 
 * @author eaytac
 * 
 */

@Service
public class OdemeService {
	
	@Autowired
	private transient BaseDao baseDao;
	
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

	@Transactional
	public PagingResult getByPaging(int first, int pageSize, Map<String, Object> filters, OrderUtil order) {
		PagingResult result = new PagingResult();
		Session session = baseDao.getCurrentSession();
		Criteria criteria = session.createCriteria(Odeme.class);
		
//		if(filters.containsKey("soyad")){
//			criteria.add(Restrictions.ilike("soyad", filters.get("soyad").toString(),MatchMode.ANYWHERE));
//		}
		
		
		if(filters.containsKey("aracGiris.arac.plaka")){
			Criteria crt = criteria.createAlias("aracGiris.arac", "arcAls");
			crt.add(Restrictions.ilike("arcAls.plaka", filters.get("aracGiris.arac.plaka").toString(),MatchMode.ANYWHERE));
		}
		
		criteria.setProjection(Projections.rowCount());
		result.setRowCount((Long) criteria.uniqueResult());
				
		criteria.setProjection(null);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		
		if(order.getField()!=null){
			if(order.getOrderType()==OrderUtil.OrderType.ASC)
				criteria.addOrder(Order.asc(order.getField()));
			else
				criteria.addOrder(Order.desc(order.getField()));
		}
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		result.setList(criteria.list());
		return result;
//		return baseDao.getByPaging(Odeme.class, first, pageSize, filters);
	}

}
