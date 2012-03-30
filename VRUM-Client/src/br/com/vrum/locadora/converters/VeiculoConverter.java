package br.com.vrum.locadora.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.vrum.locadora.interfaces.IVeiculo;
import br.com.vrum.locadora.pojo.Veiculo;

@FacesConverter(value="veiculoConverter")
public class VeiculoConverter implements Converter{
	
	private IVeiculo ejb;
	
	public VeiculoConverter() throws NamingException{
		InitialContext ic = new InitialContext();
		ejb = (IVeiculo) ic.lookup(IVeiculo.class.getName());
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
		return ((Veiculo)value).getId().toString();
	}

}
