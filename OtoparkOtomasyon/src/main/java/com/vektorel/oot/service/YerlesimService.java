package com.vektorel.oot.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vektorel.oot.entity.Il;
import com.vektorel.oot.entity.Ilce;
import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.YerlesimTip;

@Service
public class YerlesimService {

	@Autowired
	private transient BaseDao baseDao;

	@SuppressWarnings({"rawtypes" })
	public List getAll(YerlesimTip yerlesimTip) {
		switch (yerlesimTip) {
		case IL:
			return baseDao.getAll(Il.class);
		case ILCE:
			return baseDao.getAll(Ilce.class);
		}
		return null;
	}
	
	
	public Object getById(YerlesimTip yerlesimTip,Long id) {
		switch (yerlesimTip) {
		case IL:
			return baseDao.getById(id,Il.class);
		case ILCE:
			return baseDao.getById(id,Ilce.class);
		}
		return null;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	
	/**
	 *  select ilce.*,il.* from gnl_ilce ilce
		left join gnl_il il on (il.id = ilce.il_id)
		where il.id=7
	 * @param id
	 * @return
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Ilce> getAllByIlId(Long id) {
		Session session = baseDao.getCurrentSession();
		Criteria criteria =session.createCriteria(Ilce.class);
		criteria.add(Restrictions.eq("il.id", id));
		List lst = criteria.list();
		return lst;
		
	}
}
