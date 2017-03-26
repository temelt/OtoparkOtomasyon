package com.vektorel.oot.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;
import com.vektorel.oot.ws.WsKisi;

@Controller
@RequestMapping("/kisi")
public class KisiController {
	
@Autowired
private transient KisiService kisiService;

	@RequestMapping(value = "/List", method = RequestMethod.GET)
	public @ResponseBody List<Kisi> kisilistesi() {
		
		List<Kisi> liste = kisiService.getAll(null);
		
		return liste;
	}
	
	@RequestMapping(value = "/ListDto", method = RequestMethod.GET)
	public @ResponseBody List<WsKisi> ListDto() {
		
		List<Kisi> liste = kisiService.getAll(null);
		List<WsKisi> retList=new ArrayList<>();
		for (Kisi kisi : liste) {
			retList.add(new WsKisi(kisi.getId(), kisi.getTc(), kisi.getAd(), kisi.getSoyad(), kisi.getDogumTarihi()));
		}
		return retList;
	}
}
