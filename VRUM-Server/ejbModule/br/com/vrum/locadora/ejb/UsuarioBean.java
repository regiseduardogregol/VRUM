package br.com.vrum.locadora.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.vrum.locadora.interfaces.IUsuario;
import br.com.vrum.locadora.pojo.Usuario;

@Stateless
@Remote(IUsuario.class)
public class UsuarioBean implements IUsuario{
	
	@PersistenceContext(unitName="vrum")
	private EntityManager manager;

	@Override
	public void delete(Usuario usuario) {
		manager.remove(manager.merge(usuario));
		System.out.println("Usuario Deletado");
	}

	@Override
	public void insert(Usuario usuario) {
		manager.persist(usuario);
		System.out.println("Gravou Usuario");
	}

	@Override
	public Usuario search(Long id) {
		return manager.find(Usuario.class, id);
	}

	@Override
	public void update(Usuario usuario) {
		manager.merge(usuario);
		System.out.println("Update de User");
	}

	@Override
	public List<Usuario> selectAll() {
		Query query = manager.createQuery("select o from Usuario o");
		return query.getResultList();
	}
	
	@Override
	public Usuario autentica(String login, String senha) {
		Query query = manager.createQuery("SELECT o FROM Usuario o " +
				"where o.login = :login and o.senha = :senha");
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		System.out.println("===================== Usuário =============================");
		System.out.println("Login: "  + login);
		System.out.println("Senha: " + senha);
		return (Usuario) query.getSingleResult();
		
	}

}
