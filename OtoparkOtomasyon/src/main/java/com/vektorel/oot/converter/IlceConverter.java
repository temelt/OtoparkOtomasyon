package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.vektorel.oot.entity.Ilce;
import com.vektorel.oot.service.YerlesimService;
import com.vektorel.oot.util.YerlesimTip;

@FacesConverter(value="ilceConverter")
public class IlceConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
	                YerlesimService service = (YerlesimService) context.getExternalContext().getApplicationMap().get("yerlesimService");
	                return service.getById(YerlesimTip.ILCE, Long.parseLong(value));
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
            return String.valueOf(((Ilce) object).getId());
        }else {
            return null;
        }
	}

}
