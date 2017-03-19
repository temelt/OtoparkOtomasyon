package com.vektorel.oot.entity;

import javax.persistence.Column;
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
@Table(name = "yet_kullanici")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_kullanici")
public class Kullanici extends EBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7934871711877103154L;

	String uname;
	String pwd;
	Personel personel;
	String adSoyad;

	@Column(unique=true)
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Column(name="ad_soyad")
	public String getAdSoyad() {
		return adSoyad;
	}
	
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	
    @JoinColumn(name = "personel_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Personel getPersonel() {
		return personel;
	}

	public void setPersonel(Personel personel) {
		this.personel = personel;
	}

}
