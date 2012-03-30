package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.Cobertura;

public interface ICobertura {
	public void insert(Cobertura cobertura);
	public void update(Cobertura cobertura);
	public void delete(Cobertura cobertura);
	public Cobertura search(Long id);

	public List<Cobertura>  	selectAll();
}
