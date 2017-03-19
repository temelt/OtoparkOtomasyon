package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.service.MarkaModelService;

@Controller("markaModelConverter")
@Scope("request")
public class MarkaModelConverter implements Converter {

	@Autowired
	private transient MarkaModelService markaModelService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	                return markaModelService.getById(Long.parseLong(value));
	            } catch(NumberFormatException e) {
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
            return String.valueOf(((MarkaModel) object).getId());
        }else {
            return null;
        }
	}

}
