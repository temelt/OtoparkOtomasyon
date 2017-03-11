package com.vektorel.oot.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author ugur
 *
 */
@Entity
@Table(name = "gnl_otopark_uyelik")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_otopark_uyelik")
public class OtoparkUyelik extends EBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2857683080062979167L;
	private Arac arac;
	private Kisi kisi;
	private String aciklama;
	private BigDecimal fiyat;
	private Date uyelikBaslamaTarihi;
	private Date uyelikBitisTarihi;
	private Il il;
    private Ilce ilce;

    @JoinColumn(name = "arac_id")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

    @JoinColumn(name = "kisi_id")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Kisi getKisi() {
		return kisi;
	}

	public void setKisi(Kisi kisi) {
		this.kisi = kisi;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

	@Column(precision=16,scale=2)
	public BigDecimal getFiyat() {
		return fiyat;
	}

	public void setFiyat(BigDecimal fiyat) {
		this.fiyat = fiyat;
	}

	public Date getUyelikBaslamaTarihi() {
		return uyelikBaslamaTarihi;
	}

	public void setUyelikBaslamaTarihi(Date uyelikBaslamaTarihi) {
		this.uyelikBaslamaTarihi = uyelikBaslamaTarihi;
	}

	public Date getUyelikBitisTarihi() {
		return uyelikBitisTarihi;
	}

	public void setUyelikBitisTarihi(Date uyelikBitisTarihi) {
		this.uyelikBitisTarihi = uyelikBitisTarihi;
	}

    @JoinColumn(name = "il_id")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Il getIl() {
		return il;
	}

	public void setIl(Il il) {
		this.il = il;
	}

    @JoinColumn(name = "ilce_id")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}

}
