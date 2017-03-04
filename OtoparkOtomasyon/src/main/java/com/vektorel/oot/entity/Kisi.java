package com.vektorel.oot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author temelt
 *
 */
@Entity
@Table(name = "gnl_kisi")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_kisi")
public class Kisi extends EBase{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2361328748941723164L;
	
	private String ad;
    private String soyad;
    private Date dogumTarihi;
    private Long tc;
    private String babaAdi;
    private String anaAdi;
    private Cinsiyet cinsiyet;
    private Il il;
    private Ilce ilce;
    private String acikAdres;
    private String tel;
    @SuppressWarnings("unused")
	private String adSoyad;
    @SuppressWarnings("unused")
	private int yas;

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public Date getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(Date dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public Long getTc() {
        return tc;
    }

    public void setTc(Long tc) {
        this.tc = tc;
    }

    public String getBabaAdi() {
        return babaAdi;
    }

    public void setBabaAdi(String babaAdi) {
        this.babaAdi = babaAdi;
    }

    public String getAnaAdi() {
        return anaAdi;
    }

    public void setAnaAdi(String anaAdi) {
        this.anaAdi = anaAdi;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cinsiyet")
    public Cinsiyet getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(Cinsiyet cinsiyet) {
        this.cinsiyet = cinsiyet;
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

    public String getAcikAdres() {
        return acikAdres;
    }

    public void setAcikAdres(String acikAdres) {
        this.acikAdres = acikAdres;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Transient
    public String getAdSoyad() {
        return this.ad+" "+this.soyad;
    }
    
    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    @SuppressWarnings("deprecation")
	@Transient
    public int getYas() {
        if(this.dogumTarihi!=null){
            return new Date().getYear()-this.dogumTarihi.getYear();
        }
        return -1;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    @Override
    public String toString() {
        return "Kisi{" + "id=" + getId()  + "ad=" + ad + ", soyad=" + soyad + ", dogumTarihi=" + dogumTarihi + ", tc=" + tc + ", babaAdi=" + babaAdi + ", anaAdi=" + anaAdi + ", cinsiyet=" + cinsiyet + ", il=" + il + ", ilce=" + ilce + ", acikAdres=" + acikAdres + ", tel=" + tel + ", adSoyad=" + getAdSoyad() + ", yas=" + getYas() + '}';
    }
    
}