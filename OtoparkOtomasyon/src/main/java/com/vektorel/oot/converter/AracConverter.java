package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vektorel.oot.entity.Arac;
import com.vektorel.oot.service.AracService;

@FacesConverter(value="aracConverter")
public class AracConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	            	AracService aracService = (AracService) context.getExternalContext().getApplicationMap().get("aracService");
	                return aracService .getById(Long.parseLong(value));
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	        }else {
	            return null;
	        }
		 return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		if(object != null) {
            return String.valueOf(((Arac) object).getId());
        }else {
            return null;
        }
	}

}
