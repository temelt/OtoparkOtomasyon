package com.vektorel.oot.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author eaytac
 *
 */

@Entity
@Table(name = "gnl_odeme")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_odeme")
public class Odeme extends EBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2295427160359364029L;
	AracGiris aracGiris;
	BigDecimal fiyat;
	OdemeYonTip odemeYonTip;
	Date tarih;
	Personel islemYapanPersonel;

    @JoinColumn(name = "arac_giris_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public AracGiris getAracGiris() {
		return aracGiris;
	}

	public void setAracGiris(AracGiris aracGiris) {
		this.aracGiris = aracGiris;
	}

	@Column(name="fiyat", precision = 16, scale=2)
	public BigDecimal getFiyat() {
		return fiyat;
	}

	public void setFiyat(BigDecimal fiyat) {
		this.fiyat = fiyat;
	}

	@Enumerated
	public OdemeYonTip getOdemeYonTip() {
		return odemeYonTip;
	}

	public void setOdemeYonTip(OdemeYonTip odemeYonTip) {
		this.odemeYonTip = odemeYonTip;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getTarih() {
		return tarih;
	}

	public void setTarih(Date tarih) {
		this.tarih = tarih;
	}

    @JoinColumn(name = "islem_ypn_personel_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public Personel getIslemYapanPersonel() {
		return islemYapanPersonel;
	}
    
    public void setIslemYapanPersonel(Personel islemYapanPersonel) {
		this.islemYapanPersonel = islemYapanPersonel;
	}

}
