package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.vrum.locadora.interfaces.ICobertura;
import br.com.vrum.locadora.pojo.Cobertura;

@Stateless
@Remote(ICobertura.class)
public class CoberturaBean implements ICobertura{
	
	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(Cobertura cobertura) {
		manager.remove(manager.merge(cobertura));
		System.out.println("Cobertura Deletada");	
	}

	@Override
	public void insert(Cobertura cobertura) {
		manager.persist(cobertura);
		System.out.println("Gravou Cobertura");
		
	}

	@Override
	public Cobertura search(Long id) {
		return manager.find(Cobertura.class, id);
	}

	@Override
	public void update(Cobertura cobertura) {
		manager.merge(cobertura);
		System.out.println("Update de Cobertura");
		
	}

	@Override
	public List<Cobertura> selectAll() {
		Query query = manager.createQuery("select o from Cobertura o");
		return query.getResultList();
	}

}
