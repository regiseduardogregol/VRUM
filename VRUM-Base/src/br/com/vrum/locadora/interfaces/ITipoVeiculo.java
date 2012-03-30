package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.TipoVeiculo;

public interface ITipoVeiculo {
	public void insert(TipoVeiculo tipoVeiculo);
	public void update(TipoVeiculo tipoVeiculo);
	public void delete(TipoVeiculo tipoVeiculo);
	public TipoVeiculo search(Long id);
	
	public List<TipoVeiculo>  	selectAll();

}
