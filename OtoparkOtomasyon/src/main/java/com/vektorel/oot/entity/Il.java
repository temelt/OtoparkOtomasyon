package com.vektorel.oot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author temelt
 *
 */
@Entity
@Table(name = "gnl_il")
@SequenceGenerator(allocationSize = 1, name = "default_id_generator", sequenceName = "seq_il")
public class Il extends EBase {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5703543143566678737L;
	private String kod;
    private String ad;
    
    public Il(){
        
    }

    public Il(Long id, String kod, String ad) {
        
        this.setId(id);
        this.kod = kod;
        this.ad = ad;
        
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
    
    

}