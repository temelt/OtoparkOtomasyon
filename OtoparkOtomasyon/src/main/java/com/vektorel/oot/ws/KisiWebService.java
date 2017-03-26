package com.vektorel.oot.ws;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;

@Service
@WebService(name="KisiWs",serviceName="KisiWs",portName="KisiWs")
@SOAPBinding(style=Style.DOCUMENT)
public class KisiWebService {
	
	@Autowired
	private transient KisiService kisiService;
	
	@WebMethod(operationName="KisiGetir",exclude=false)
	public @WebResult WsKisi KisiGetir(@WebParam(name="tcKimlikNo") long tc){
		WsKisi kisi=new WsKisi();
		Kisi db = kisiService.getByTC(tc);
		kisi.setAd(db.getAd());
		kisi.setDogumTarihi(db.getDogumTarihi());
		kisi.setId(db.getId());
		kisi.setSoyad(db.getSoyad());
		kisi.setTc(db.getTc());
		return kisi;
	}
}
