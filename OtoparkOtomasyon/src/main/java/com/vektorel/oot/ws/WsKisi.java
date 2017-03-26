package com.vektorel.oot.ws;

import java.util.Date;

public class WsKisi {
	
	public WsKisi() {
	}

	
	public WsKisi(Long id, Long tc, String ad, String soyad, Date dogumTarihi) {
		super();
		this.id = id;
		this.tc = tc;
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
	}


	private Long id;
	private Long tc;
	private String ad;
	private String soyad;
	private Date dogumTarihi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTc() {
		return tc;
	}

	public void setTc(Long tc) {
		this.tc = tc;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public Date getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(Date dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

}
