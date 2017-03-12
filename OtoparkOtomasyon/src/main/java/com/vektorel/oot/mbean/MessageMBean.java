package com.vektorel.oot.mbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Controller;

/**
 * 
 * @author temelt
 *
 */
@Controller("messageBean")
public class MessageMBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3921610128144835016L;
	
	
	private static final String KAYIT="KAYIT";
	private static final String KAYIT_EKLENDI="Kaydýnýz Eklenmiþtir.";
	private static final String KAYIT_SILINDI="Kaydýnýz Silinmiþtir.";
	private static final String KAYIT_GUNCELLENDI="Kaydýnýz Güncellendi.";

	public void mesajHataGoster(String baslik, String detay) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, baslik, detay));
	}
	
	public void mesajUyariGoster(String baslik, String detay) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, baslik, detay));
	}
	
	public void mesajKayitBasarili() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, KAYIT, KAYIT_EKLENDI));
	}
	
	public void mesajGuncellemeBasarili() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, KAYIT, KAYIT_GUNCELLENDI));
	}
	
	public void mesajSilmeBasarili() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, KAYIT, KAYIT_SILINDI));
	}
}
