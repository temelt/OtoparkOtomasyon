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
 * @author vektorel
 */
@Entity
@Table(name = "gnl_ilce")
@SequenceGenerator(allocationSize = 1, name = "default_id_generator", sequenceName = "seq_ilce")
public class Ilce extends EBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5709281920425480301L;
	private String kod;
    private String ad;
    private Il il;

    public Ilce() {

    }

    public Ilce(Long id, String kod, String ad, Il il) {

        this.setId(id);
        this.kod = kod;
        this.ad = ad;
        this.il = il;

    }

    @Column(name = "kod")
    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    @Column(name = "ad")
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    @JoinColumn(name = "il_id")
    @ManyToOne(fetch = FetchType.LAZY)
    public Il getIl() {
        return il;
    }

    public void setIl(Il il) {
        this.il = il;
    }

}