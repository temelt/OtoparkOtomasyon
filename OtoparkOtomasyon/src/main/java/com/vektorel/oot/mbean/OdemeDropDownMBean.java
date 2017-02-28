package com.vektorel.oot.mbean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;

public class OdemeDropDownMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9142938225210755089L;
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	private String adSoyad;
	private Map<String,String> adSoyadListe;

	
	
}
