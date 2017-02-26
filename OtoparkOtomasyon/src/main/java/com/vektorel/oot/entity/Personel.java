package com.vektorel.oot.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author tugce
 * 
 */
@Entity
@Table(name = "gnl_personel")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_personel")
public class Personel extends EBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2236208793676833386L;
	Kisi kisi;
	String sicilNo;
	BigDecimal maas;

    @JoinColumn(name = "kisi_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public String getSicilNo() {
		return sicilNo;
	}

	public void setSicilNo(String sicilNo) {
		this.sicilNo = sicilNo;
	}

	@Column(precision=16,scale=2)
	public BigDecimal getMaas() {
		return maas;
	}

	public void setMaas(BigDecimal maas) {
		this.maas = maas;
	}

}
