package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vektorel.oot.entity.MarkaModel;
import com.vektorel.oot.service.MarkaModelService;

@FacesConverter(value="markaModelConverter")
public class MarkaModelConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	            	MarkaModelService service = (MarkaModelService) context.getExternalContext().getApplicationMap().get("markaModelService");
	                return service.getById(Long.parseLong(value));
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
