package com.vektorel.oot.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vektorel.oot.entity.Ilce;
import com.vektorel.oot.service.YerlesimService;
import com.vektorel.oot.util.YerlesimTip;

@Controller("ilceConverter")
@Scope("request")
public class IlceConverter implements Converter {

	@Autowired
	private transient YerlesimService yerlesimService; 
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		 if(value != null && value.trim().length() > 0) {
	            try {
//	                YerlesimService service = (YerlesimService) context.getExternalContext().getApplicationMap().get("yerlesimService");
	                return yerlesimService.getById(YerlesimTip.ILCE, Long.parseLong(value));
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
