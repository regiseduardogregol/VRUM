package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.vrum.locadora.interfaces.ITipoVeiculo;
import br.com.vrum.locadora.pojo.TipoVeiculo;

@Stateless
@Remote(ITipoVeiculo.class)
public class TipoVeiculoBean implements ITipoVeiculo{
	
	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(TipoVeiculo tipoVeiculo) {
		manager.remove(manager.merge(tipoVeiculo));
		System.out.println("Tipo de veiculo Deletado");
		
	}

	@Override
	public void insert(TipoVeiculo tipoVeiculo) {
		manager.persist(tipoVeiculo);
		System.out.println("Gravou Tipo de Veiculo");
	}

	@Override
	public TipoVeiculo search(Long id) {
		return manager.find(TipoVeiculo.class, id);
	}

	@Override
	public void update(TipoVeiculo tipoVeiculo) {
		manager.merge(tipoVeiculo);
		System.out.println("Update de Tipo de Veiculo");
	}

	@Override
	public List<TipoVeiculo> selectAll() {
		Query query = manager.createQuery("select o from TipoVeiculo o");
		return query.getResultList();
	}


}
