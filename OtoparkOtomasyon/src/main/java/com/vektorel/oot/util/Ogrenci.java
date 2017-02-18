package com.vektorel.oot.util;

public class Ogrenci {

	public Ogrenci() {
		// TODO Auto-generated constructor stub
	}

	public Ogrenci(String adSoyad, String okul, String adres) {
		super();
		this.adSoyad = adSoyad;
		this.okul = okul;
		this.adres = adres;
	}

	private String adSoyad;
	private String okul;
	private String adres;

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public String getOkul() {
		return okul;
	}

	public void setOkul(String okul) {
		this.okul = okul;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

}
