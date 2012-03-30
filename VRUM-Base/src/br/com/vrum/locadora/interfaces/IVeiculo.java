package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.TipoVeiculo;
import br.com.vrum.locadora.pojo.Veiculo;

public interface IVeiculo {
	public void insert(Veiculo veiculo);
	public void update(Veiculo veiculo);
	public void delete(Veiculo veiculo);
	public Veiculo search(Long id);
	
	public List<Veiculo>  	 selectAll();
	public List<TipoVeiculo> selectAllTiposVeiculo();
}
