package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Arac;
import com.vektorel.oot.service.AracService;

@Controller("aracConverter")
@Scope("request")
public class AracConverter implements Converter {
	
	@Autowired
	private transient AracService aracService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
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
