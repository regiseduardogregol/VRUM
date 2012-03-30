package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.Cliente;
import br.com.vrum.locadora.pojo.Cobertura;
import br.com.vrum.locadora.pojo.Reserva;
import br.com.vrum.locadora.pojo.Veiculo;

public interface IReserva {
	public void insert(Reserva reserva);
	public void update(Reserva reserva);
	public void delete(Reserva reserva);
	public Reserva search(Long id);
	
	public List<Reserva>  	selectAll();
	public Cliente			selectByCpf(String cpf);
	public List<Veiculo> 	selectAllVeiculos();
	public List<Cobertura>	selectAllCoberturas();
	public List<Cliente>	selectAllClientes();
	
	
	void finalizaReserva();

}
