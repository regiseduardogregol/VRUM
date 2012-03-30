package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.vrum.locadora.interfaces.ICliente;
import br.com.vrum.locadora.pojo.Cliente;

@Stateless
@Remote(ICliente.class)
public class ClienteBean implements ICliente{

	
	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(Cliente cliente) {
		manager.remove(manager.merge(cliente));
		System.out.println("Cliente Deletado");	
	}

	@Override
	public void insert(Cliente cliente) {
		manager.persist(cliente);
		System.out.println("Gravou Cliente");
	}

	@Override
	public Cliente search(Long id) {
		return manager.find(Cliente.class, id);
	}

	@Override
	public void update(Cliente cliente) {
		manager.merge(cliente);
		System.out.println("Update de Cliente");
	}

	@Override
	public List<Cliente> selectAll() {
		Query query = manager.createQuery("select o from Cliente o");
		return query.getResultList();
	}

	@Override
	public Cliente selectByCpf(String cpf) {
		Query query = manager.createQuery("select o from Cliente o " + 
				"where o.cpf = :cpf");
		query.setParameter("cpf", cpf);
		Cliente c = new Cliente();
		c = (Cliente) query.getSingleResult();
		System.out.println("==============-=============");
		System.out.println("Cliente: " + c.getNome());
		return c;
	}
	
	
	
}
