package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;

@FacesConverter(value="kisiConverter")
public class KisiConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	                KisiService service = (KisiService) context.getExternalContext().getApplicationMap().get("kisiService");
	                return service.getById(Long.parseLong(value));
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
            return String.valueOf(((Kisi) object).getId());
        }else {
            return null;
        }
	}

}
