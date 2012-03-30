package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.Cliente;

public interface ICliente {
	public void insert(Cliente cliente);
	public void update(Cliente cliente);
	public void delete(Cliente cliente);
	public Cliente search(Long id);
	
	public List<Cliente>  	selectAll();
	public Cliente			selectByCpf(String cpf);
}
