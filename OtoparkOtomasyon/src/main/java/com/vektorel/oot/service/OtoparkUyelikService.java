package com.vektorel.oot.service;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.entity.OtoparkUyelik;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.HRException;
import com.vektorel.oot.util.OrderUtil;
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
//			throw new HRException("L�tfen Arac� giriniz");
//		}
//		if (entity.getKisi() == null) {
//			throw new HRException("L�tfen Kisi bilgilerini giriniz");
//		}
		baseDao.save(entity);
		return true;
	}

	public boolean update(OtoparkUyelik entity) throws Exception {
//		if (entity.getArac() == null) {
//			throw new HRException("L�tfen Arac� giriniz");
//		}
//		if (entity.getKisi() == null) {
//			throw new HRException("L�tfen Kisi bilgilerini giriniz");
//		}
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
		PagingResult result = new PagingResult();
		Session session = baseDao.getOpenSession();
		Criteria criteria = session.createCriteria(OtoparkUyelik.class);
		
		if(filters.containsKey("arac")){
			criteria.add(Restrictions.ilike("arac", filters.get("arac").toString(),MatchMode.ANYWHERE));
		}
		
		if(filters.containsKey("kisi")){
			criteria.add(Restrictions.ilike("kisi", filters.get("kisi").toString(),MatchMode.ANYWHERE));
		}
		
		if(filters.containsKey("aciklama")){
			criteria.add(Restrictions.ilike("aciklama", filters.get("aciklama").toString(),MatchMode.ANYWHERE));
		}
		
		criteria.setProjection(Projections.rowCount());
		result.setRowCount((Long) criteria.uniqueResult());
				
		criteria.setProjection(null);
		criteria.setFirstResult(first);
		criteria.setMaxResults(pageSize);
		
//		if(order.getField()!=null){
//			if(order.getOrderType()==OrderUtil.OrderType.ASC)
//				criteria.addOrder(Order.asc(order.getField()));
//			else
//				criteria.addOrder(Order.desc(order.getField()));
//		}
//		
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		result.setList(criteria.list());
		session.close();
		return result;
	}
	
		
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
