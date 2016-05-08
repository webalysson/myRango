
package com.alysson.myrango.util;

import com.alysson.myrango.model.Modulo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;



@FacesConverter(forClass = Modulo.class,value = "conversor")
public class Conversor implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            return (Modulo) component.getAttributes().get(value);
        }
        return null;        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Modulo) {
            Modulo modulo = (Modulo) value;
            if (modulo != null && modulo instanceof Modulo && modulo.getId() != null) {
                component.getAttributes().put( modulo.getId().toString(), modulo);
                return modulo.getId().toString();
            }
        }
        return "";        
    }
    
}
