package com.vektorel.oot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author elif
 *
 */
@Entity
@Table(name = "gnl_arac")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_arac")
public class Arac extends EBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3867221234891906297L;
	String plaka;
	MarkaModel markaModel;
	Renk renk;
	Kisi kisi;
	String kisiAdSoyad;

	public String getPlaka() {
		return plaka;
	}

	public void setPlaka(String plaka) {
		this.plaka = plaka;
	}

    @JoinColumn(name = "marka_model_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public MarkaModel getMarkaModel() {
		return markaModel;
	}

	public void setMarkaModel(MarkaModel markaModel) {
		this.markaModel = markaModel;
	}

	public Renk getRenk() {
		return renk;
	}

	public void setRenk(Renk renk) {
		this.renk = renk;
	}

    @JoinColumn(name = "kisi_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public String getKisiAdSoyad() {
		return kisiAdSoyad;
	}

	public void setKisiAdSoyad(String kisiAdSoyad) {
		this.kisiAdSoyad = kisiAdSoyad;
	}

}
