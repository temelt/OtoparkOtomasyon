package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Kisi;
import com.vektorel.oot.service.KisiService;

@Controller("kisiConverter")
@Scope("request")
public class KisiConverter implements Converter {

	@Autowired
	private transient KisiService kisiService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
//	                KisiService service = (KisiService) context.getExternalContext().getApplicationMap().get("kisiService");
	                return kisiService.getById(Long.parseLong(value));
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
