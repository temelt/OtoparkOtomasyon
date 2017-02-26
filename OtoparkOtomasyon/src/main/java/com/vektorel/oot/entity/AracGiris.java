package com.vektorel.oot.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author erhan
 *
 */
@Entity
@Table(name = "gnl_arac_giris")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_arac_giris")
public class AracGiris extends EBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2664167977216524575L;
	Arac arac;
	Date girisTarihi;
	Date cikisTarihi;
	OtoparkBolum otoparkBolum;
	Boolean aracCikti;
	
    @JoinColumn(name = "arac_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public Date getGirisTarihi() {
		return girisTarihi;
	}

	public void setGirisTarihi(Date girisTarihi) {
		this.girisTarihi = girisTarihi;
	}

	public Date getCikisTarihi() {
		return cikisTarihi;
	}

	public void setCikisTarihi(Date cikisTarihi) {
		this.cikisTarihi = cikisTarihi;
	}

    @JoinColumn(name = "otopark_bolum_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public OtoparkBolum getOtoparkBolum() {
		return otoparkBolum;
	}

	public void setOtoparkBolum(OtoparkBolum otoparkBolum) {
		this.otoparkBolum = otoparkBolum;
	}
	
	public Boolean getAracCikti() {
		return aracCikti;
	}
	
	public void setAracCikti(Boolean aracCikti) {
		this.aracCikti = aracCikti;
	}

}
