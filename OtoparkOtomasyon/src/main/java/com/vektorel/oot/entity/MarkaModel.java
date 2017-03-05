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
@Table(name = "gnl_marka_model")
@SequenceGenerator(allocationSize = 1,name = "default_id_generator",sequenceName = "seq_marka_model")
public class MarkaModel extends EBase {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7716334496753163066L;
	String tanim;
	String aciklama;
	MarkaModel markaModel;

	public String getTanim() {
		return tanim;
	}

	public void setTanim(String tanim) {
		this.tanim = tanim;
	}

	public String getAciklama() {
		return aciklama;
	}

	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}

    @JoinColumn(name = "ust_marka_model_id")
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
	public MarkaModel getMarkaModel() {
		return markaModel;
	}

	public void setMarkaModel(MarkaModel markaModel) {
		this.markaModel = markaModel;
	}

    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof MarkaModel){
    		if(((MarkaModel) obj).getId().equals(this.getId())){
    			return true;
    		}
    	}
    	return false;
    }
}
