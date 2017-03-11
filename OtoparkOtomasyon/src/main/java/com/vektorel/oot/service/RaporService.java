package com.vektorel.oot.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.vektorel.oot.util.BaseDao;
import com.vektorel.oot.util.CinsiyetYilDagilimi;


@ManagedBean(name = "raporService")
@ApplicationScoped
public class RaporService {

	
	private static final String CINSIYET_YIL_DAGILIMI=" Select " 
													    +" cast(EXTRACT(YEAR FROM k.dogumtarihi) as integer ) yil ,  "
													    +" cast(cinsiyet as integer) , "
													    +" cast(count(k.id)  as integer)  as sayisi "
													    +"from gnl_kisi as k  group by k.cinsiyet, EXTRACT(YEAR FROM k.dogumtarihi) "
													    +"order by yil,cinsiyet ";
	
	@ManagedProperty("#{baseDao}")
	private transient BaseDao baseDao;
	
	@SuppressWarnings("rawtypes")
	public List<CinsiyetYilDagilimi> getCinsiyetYilDagilimiListesi() {
		Session session = baseDao.getOpenSession();
		SQLQuery query = session.createSQLQuery(CINSIYET_YIL_DAGILIMI);
		List list = query.list();
		session.close();
		List<CinsiyetYilDagilimi> retList=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Object[] el  = (Object[]) list.get(i);
			CinsiyetYilDagilimi d=new CinsiyetYilDagilimi();
			d.setYil(Integer.parseInt(el[0].toString()));
			d.setCinsiyet(Integer.parseInt(el[1].toString()));
			d.setSayisi(Integer.parseInt(el[2].toString()));
			retList.add(d);
		}		
		return retList;
	}
	
	
	public BaseDao getBaseDao() {
		return baseDao;
	}
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
