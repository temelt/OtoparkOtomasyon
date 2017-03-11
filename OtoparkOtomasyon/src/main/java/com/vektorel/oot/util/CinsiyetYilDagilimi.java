package com.vektorel.oot.util;

import java.io.Serializable;

public class CinsiyetYilDagilimi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622175082837707571L;
	private Integer yil;
	private Integer cinsiyet;
	private Integer sayisi;
	
	public Integer getYil() {
		return yil;
	}
	public void setYil(Integer yil) {
		this.yil = yil;
	}
	
	public Integer getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(Integer cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	
	public Integer getSayisi() {
		return sayisi;
	}
	public void setSayisi(Integer sayisi) {
		this.sayisi = sayisi;
	}	
	
}
