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
	private String tanim;
	private String kod;
	private String adres;
	private Il il;
	private Ilce ilce;
	private Kisi sorumlusu;

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
	
    @JoinColumn(name = "sorumlu_kisi_id")
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
	public Kisi getSorumlusu() {
		return sorumlusu;
	}
	
	public void setSorumlusu(Kisi sorumlusu) {
		this.sorumlusu = sorumlusu;
	}

}
