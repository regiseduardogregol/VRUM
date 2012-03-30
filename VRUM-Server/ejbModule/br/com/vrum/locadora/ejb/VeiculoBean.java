package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.com.vrum.locadora.interfaces.IVeiculo;
import br.com.vrum.locadora.pojo.TipoVeiculo;
import br.com.vrum.locadora.pojo.Veiculo;

@Stateless
@Remote(IVeiculo.class)
public class VeiculoBean implements IVeiculo{
	
	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(Veiculo veiculo) {
		manager.remove(manager.merge(veiculo));
		System.out.println("Veiculo Deletado");
	}

	@Override
	public void insert(Veiculo veiculo) {
		manager.persist(veiculo);
		System.out.println("Gravou Veiculo");
	}

	@Override
	public Veiculo search(Long id) {
		return manager.find(Veiculo.class, id);
	}

	@Override
	public void update(Veiculo veiculo) {
		manager.merge(veiculo);
		System.out.println("Update de Veiculo");	
	}

	@Override
	public List<Veiculo> selectAll() {
		Query query = manager.createQuery("select o from Veiculo o");
		return query.getResultList();
	}

	@Override
	public List<TipoVeiculo> selectAllTiposVeiculo() {
		Query query = manager.createQuery("select o from TipoVeiculo o");
		return query.getResultList();
	}


}
