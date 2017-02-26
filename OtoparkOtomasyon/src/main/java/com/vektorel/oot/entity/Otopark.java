package com.vektorel.oot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author temelt
 * 
 */
@Entity
@Table(name = "gnl_otopark")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_otopark")
public class Otopark extends EBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1770217009007371885L;
	String tanim;
	String kod;
	String adres;
	Il il;
	Ilce ilce;

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

    @JoinColumn(name = "il_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Il getIl() {
		return il;
	}

	public void setIl(Il il) {
		this.il = il;
	}

    @JoinColumn(name = "ilce_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Ilce getIlce() {
		return ilce;
	}

	public void setIlce(Ilce ilce) {
		this.ilce = ilce;
	}

}
