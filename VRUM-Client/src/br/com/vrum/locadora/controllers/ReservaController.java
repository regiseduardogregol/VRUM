package br.com.vrum.locadora.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import br.com.vrum.locadora.interfaces.IReserva;
import br.com.vrum.locadora.pojo.Cliente;
import br.com.vrum.locadora.pojo.Cobertura;
import br.com.vrum.locadora.pojo.Reserva;
import br.com.vrum.locadora.pojo.TipoVeiculo;
import br.com.vrum.locadora.pojo.Veiculo;

@ManagedBean
@SessionScoped
public class ReservaController {

	@EJB
	private IReserva reserv;
	private Reserva reserva;
	private List<Reserva> reservas = new ArrayList<Reserva>();
	private List<TipoVeiculo> tiposVeiculo = new ArrayList<TipoVeiculo>();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private List<Cobertura> coberturas = new ArrayList<Cobertura>();
	private List<Cliente>   clientes 	= new ArrayList<Cliente>();
	private Cliente	cliente;
	private String variavel;
	private boolean oi = false;
	private String mensagem;
	
	
	

	
	public boolean isOi() {
		return oi;
	}
	public void setOi(boolean oi) {
		this.oi = oi;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getVariavel() {
		return variavel;
	}
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	
	
	public void setReserv(IReserva reserv) {
		this.reserv = reserv;
	}
	public IReserva getReserv() {
		return reserv;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	
	public List<Reserva> getReservas() {
		reservas = reserv.selectAll();
		return reservas;
	}
	
	public ReservaController(){
		reserva = new Reserva();
	}
	
	
	public String doNew(){
		return "clientes/alugar.faces";
	}
	
	public Cliente selectByCpf(ActionEvent event) {
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("cpfCliente");
		String cpf = (String) param.getValue();
		try{
			cliente = reserv.selectByCpf(cpf);
			variavel = cliente.getNome();
			oi = false;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("CPF INVÁLIDO");
			oi = true;
		}
		return cliente;
	}
	
	public void verificaCpf() throws IOException{
		HttpServletResponse response = 
			(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if(oi == true){
			mensagem = "Você Ainda não está Cadastrado, " +
					"Por Favor Realize seu Cadastro para Efetuar Locação";
			response.sendRedirect("cadastro.faces");
		}else{
		 response.sendRedirect("alugarVeiculo.faces");  
		}
	}
	
	public String cadastraReserva(){
		reserva.setCliente(getCliente());
		reserv.insert(reserva);
		reserv.finalizaReserva();
		return "clientes/alugar.faces";
	}
	
	public void deletarReserva(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaDeletar");
		Long id = (Long) param.getValue();
		Reserva r = reserv.search(id);
		reserv.delete(r);
	}
	public void setTiposVeiculo(List<TipoVeiculo> tiposVeiculo) {
		this.tiposVeiculo = tiposVeiculo;
	}
	public List<TipoVeiculo> getTiposVeiculo() {
		return tiposVeiculo;
	}
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	public List<Veiculo> getVeiculos() {
		veiculos = reserv.selectAllVeiculos();
		return veiculos;
	}
	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}
	public List<Cobertura> getCoberturas() {
		coberturas = reserv.selectAllCoberturas();
		return coberturas;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public List<Cliente> getClientes() {
		clientes = reserv.selectAllClientes();
		return clientes;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	
}
