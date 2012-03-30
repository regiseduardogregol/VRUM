package br.com.vrum.locadora.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import br.com.vrum.locadora.interfaces.ITipoVeiculo;
import br.com.vrum.locadora.pojo.TipoVeiculo;

@ManagedBean
@SessionScoped
public class TipoVeiculoController {
	
	@EJB
	private ITipoVeiculo type;
	private TipoVeiculo tipo;
	private List<TipoVeiculo> tipos = new ArrayList<TipoVeiculo>();
	
	public void setType(ITipoVeiculo type) {
		this.type = type;
	}
	public ITipoVeiculo getType() {
		return type;
	}
	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}
	public TipoVeiculo getTipo() {
		return tipo;
	}
	
	public String doNew(){
		return "admin/veiculo/newTipoVeiculo.faces";
	}
	
	public TipoVeiculoController(){
		tipo = new TipoVeiculo();
	}

	
	public String cadastraTipoVeiculo(){
		if(UsuarioController.logado == true)
		type.insert(tipo);
		return "admin/veiculo/newTipoVeiculo.faces";
	}
	
	
	public void alterarTipo(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaAlterar");
		Long id = (Long) param.getValue();
		TipoVeiculo t = type.search(id);
		tipo.setDescricao((t.getDescricao()));
		type.update(tipo);
	}
	
	public void deletarTipo(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaDeletar");
		Long id = (Long) param.getValue();
		TipoVeiculo t = type.search(id);
		type.delete(t);
	}
	public void setTipos(List<TipoVeiculo> tipos) {
		this.tipos = tipos;
	}
	public List<TipoVeiculo> getTipos() {
		tipos = type.selectAll();
		return tipos;
	}

}
