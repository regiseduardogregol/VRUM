package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.vrum.locadora.exceptions.CpfException;
import br.com.vrum.locadora.exceptions.LocacaoException;
import br.com.vrum.locadora.interceptors.LocacaoInterceptor;
import br.com.vrum.locadora.interfaces.IReserva;
import br.com.vrum.locadora.pojo.Cliente;
import br.com.vrum.locadora.pojo.Cobertura;
import br.com.vrum.locadora.pojo.Reserva;
import br.com.vrum.locadora.pojo.Veiculo;

@Stateless
@Remote(IReserva.class)
public class ReservaBean implements IReserva {

	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(Reserva reserva) {
		manager.remove(manager.merge(reserva));
		System.out.println("Reserva Deletado");
		
	}

	@Override
	@Interceptors(LocacaoInterceptor.class)
	public void insert(Reserva reserva) throws LocacaoException{
		manager.persist(reserva);
		System.out.println("Gravou Reserva");
		
	}

	@Override
	public Reserva search(Long id) {
		return manager.find(Reserva.class, id);
	}

	@Override
	public void update(Reserva reserva) {
		manager.merge(reserva);
		System.out.println("Update de Reserva");		
	}

	@Override
	public List<Reserva> selectAll() {
		Query query = manager.createQuery("select o from Reserva o");
		return query.getResultList();
	}
	
	
	@PostConstruct
	public void injetorPostConstruct() {
		System.out.println("Executando o @PostConstructor");
	}
	@PostActivate
	public void ativando() {
		System.out.println("Ativando " + this);
	}
	
	@PrePassivate
	public void passivando() {
		System.out.println("Passivando " + this);
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Removendo uma instância da classe ReservaBean do Container");
	}
	
	@Remove
	public void finalizaReserva() {
		System.out.println("Finalizando a transação");
		
	}

	@Override
	public Cliente selectByCpf(String cpf) throws CpfException{
		
		Query query = manager.createQuery("select o from Cliente o " + 
		"where o.cpf = :cpf");
		query.setParameter("cpf", cpf);
		Cliente c = new Cliente();
		c = (Cliente) query.getSingleResult();
		System.out.println("==============-=============");
		System.out.println("Cliente: " + c.getNome());
		return c;
	}

	@Override
	public List<Cobertura> selectAllCoberturas(){
		Query query = manager.createQuery("select o from Cobertura o");
		return query.getResultList();
	}

	@Override
	public List<Veiculo> selectAllVeiculos() {
		Query query = manager.createQuery("select o from Veiculo o");
		return query.getResultList();
	}

	@Override
	public List<Cliente> selectAllClientes() {
		Query query = manager.createQuery("select o from Cliente o");
		return query.getResultList();
	}
}
