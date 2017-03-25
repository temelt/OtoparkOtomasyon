package com.vektorel.test;

import tr.gov.nvi.tckimlik.ws.KPSPublic;
import tr.gov.nvi.tckimlik.ws.KPSPublicSoap;

import com.cdyne.ws.IP2Geo;
import com.cdyne.ws.IP2GeoSoap;
import com.cdyne.ws.IPInformation;

public class Test {
	public static void main(String[] args) {
		
		//KOD
		//wsimport -keep https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx?wsdl
		
		//Jar 
		//wsimport -keep -clientjar KPS.jar https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx?wsdl
		
		
		try {
			IP2GeoSoap client = new IP2Geo().getIP2GeoSoap();
			IPInformation info = client.resolveIP("24.133.221.76", "test");
			System.out.println(info.getCity());

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			KPSPublicSoap client = new KPSPublic().getKPSPublicSoap();
			boolean b = client.tcKimlikNoDogrula(12354L, "ÝSÝM", "SOYÝSÝM",2017);
			System.out.println(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
