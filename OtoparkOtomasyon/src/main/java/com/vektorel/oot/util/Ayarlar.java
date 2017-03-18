package com.vektorel.oot.util;

import java.io.Serializable;

public class Ayarlar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8419694506705053993L;
	String uygulamaAdi;
	String uygulamaAdresi;
	String gelistiren;
	String versiyon;

	public String getUygulamaAdi() {
		return uygulamaAdi;
	}

	public void setUygulamaAdi(String uygulamaAdi) {
		this.uygulamaAdi = uygulamaAdi;
	}

	public String getUygulamaAdresi() {
		return uygulamaAdresi;
	}

	public void setUygulamaAdresi(String uygulamaAdresi) {
		this.uygulamaAdresi = uygulamaAdresi;
	}

	public String getGelistiren() {
		return gelistiren;
	}

	public void setGelistiren(String gelistiren) {
		this.gelistiren = gelistiren;
	}

	public String getVersiyon() {
		return versiyon;
	}

	public void setVersiyon(String versiyon) {
		this.versiyon = versiyon;
	}

}
