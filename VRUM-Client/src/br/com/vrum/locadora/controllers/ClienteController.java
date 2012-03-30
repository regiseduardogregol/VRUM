package br.com.vrum.locadora.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import br.com.vrum.locadora.interfaces.ICliente;
import br.com.vrum.locadora.pojo.Cliente;

@ManagedBean
@SessionScoped
public class ClienteController {
	
	@EJB
	private ICliente client;	
	private Cliente cliente;
	private List<Cliente> clientes = new ArrayList<Cliente>();

	public void setClient(ICliente client) {
		this.client = client;
	}

	public ICliente getClient() {
		return client;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public String doNew(){
		return "clientes/cadastro.faces";
	}
	
	public ClienteController(){
		cliente = new Cliente();
	}

	public Cliente selectByCpf(ActionEvent event) {
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("cpfCliente");
		String cpf = (String) param.getValue();
		cliente = client.selectByCpf(cpf);
		return cliente;
	}
	
	public String cadastraCliente(){
		client.insert(cliente);
		return "clientes/cadastro.faces";
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getClientes() {
		clientes = client.selectAll();
		return clientes;
	}
}
