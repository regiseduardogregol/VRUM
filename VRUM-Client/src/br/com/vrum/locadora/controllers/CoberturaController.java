package br.com.vrum.locadora.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import br.com.vrum.locadora.interfaces.ICobertura;
import br.com.vrum.locadora.pojo.Cobertura;

@ManagedBean
@SessionScoped
public class CoberturaController {
	
	@EJB
	private ICobertura cobert;
	private Cobertura cobertura;
	private List<Cobertura> coberturas = new ArrayList<Cobertura>();
	
	public void setCobert(ICobertura cobert) {
		this.cobert = cobert;
	}
	public ICobertura getCobert() {
		return cobert;
	}
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}
	public Cobertura getCobertura() {
		return cobertura;
	}
	
	
	public CoberturaController(){
		cobertura = new Cobertura();
	}
	public String doNew(){
		return "admin/cobertura/newCobertura.faces";
	}
	
	public String cadastraCobertura(){
		cobert.insert(cobertura);
		return "admin/cobertura/newCobertura.faces";
	}
	
	
	public void alterarCobertura(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaAlterar");
		Long id = (Long) param.getValue();
		Cobertura c = cobert.search(id);
		c.setDescricao((cobertura.getDescricao()));
		cobert.update(c);
	}
	
	public void deletarCobertura(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaDeletar");
		Long id = (Long) param.getValue();
		Cobertura c = cobert.search(id);
		cobert.delete(c);
	}
	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}
	public List<Cobertura> getCoberturas() {
		coberturas = cobert.selectAll();
		return coberturas;
	}

}
