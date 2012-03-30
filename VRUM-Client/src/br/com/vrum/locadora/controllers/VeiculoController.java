package br.com.vrum.locadora.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import br.com.vrum.locadora.interfaces.IVeiculo;
import br.com.vrum.locadora.pojo.TipoVeiculo;
import br.com.vrum.locadora.pojo.Veiculo;

@ManagedBean
@SessionScoped
public class VeiculoController {
	
	@EJB
	private IVeiculo veic;
	private Veiculo veiculo;
	private List<Veiculo>     veiculos      = new ArrayList<Veiculo>();
	private List<TipoVeiculo> tiposVeiculos = new ArrayList<TipoVeiculo>();
	private TipoVeiculo		  tipo;
	

	public void setVeic(IVeiculo veic) {
		this.veic = veic;
	}
	public IVeiculo getVeic() {
		return veic;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public List<Veiculo> getVeiculos() {
		veiculos = veic.selectAll();
		return veiculos;
	}

	
	public VeiculoController(){
		veiculo = new Veiculo();
		
	}
	
	public String doNew(){
		return "admin/veiculo/newVeiculo.faces";
	}
	
	public String cadastraVeiculo(){
		if(UsuarioController.logado == true)
		veic.insert(veiculo);
		return "admin/veiculo/newVeiculo.faces";
	}
	
	
	public void alterarVeiculo(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaAlterar");
		Long id = (Long) param.getValue();
		Veiculo v = veic.search(id);
		veiculo.setDescricao((v.getDescricao()));
		veic.update(veiculo);
	}
	
	public void deletarVeiculo(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaDeletar");
		Long id = (Long) param.getValue();
		Veiculo v = veic.search(id);
		veic.delete(v);
	}
	
	public void setTiposVeiculos(List<TipoVeiculo> tiposVeiculos) {
		this.tiposVeiculos = tiposVeiculos;
	}
	public List<TipoVeiculo> getTiposVeiculos() {
		tiposVeiculos = veic.selectAllTiposVeiculo();
		return tiposVeiculos;
	}
	public void setTipo(TipoVeiculo tipo) {
		this.tipo = tipo;
	}
	public TipoVeiculo getTipo() {
		return tipo;
	}
	

}
