package br.com.vrum.locadora.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import br.com.vrum.locadora.interfaces.ICobertura;
import br.com.vrum.locadora.pojo.Cobertura;

@FacesConverter(value="coberturaConverter")
public class CoberturaConverter implements Converter{
	
	public ICobertura ejb;
	
	public CoberturaConverter() throws NamingException{
		InitialContext ic = new InitialContext();
		ejb = (ICobertura) ic.lookup(ICobertura.class.getName());		
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if(value==null || value.equalsIgnoreCase("0")||value.equalsIgnoreCase("-1")){
			return null;
		}
		return ejb.search(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Cobertura)value).getId().toString();
	}

}
