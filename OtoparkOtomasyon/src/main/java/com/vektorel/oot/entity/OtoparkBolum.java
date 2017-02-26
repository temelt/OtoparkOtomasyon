package com.vektorel.oot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author takatas
 *
 */
@Entity
@Table(name = "gnl_otopark_bolum")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_otopark_bolum")
public class OtoparkBolum extends EBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4747719177043798279L;
	Otopark otopark;
	String tanim;
	String kod;
	OtoparkBolum otoparkBolum;

    @JoinColumn(name = "otopark_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Otopark getOtopark() {
		return otopark;
	}

	public void setOtopark(Otopark otopark) {
		this.otopark = otopark;
	}

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

    @JoinColumn(name = "otopark_bolum_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public OtoparkBolum getOtoparkBolum() {
		return otoparkBolum;
	}

	public void setOtoparkBolum(OtoparkBolum otoparkBolum) {
		this.otoparkBolum = otoparkBolum;
	}

}
