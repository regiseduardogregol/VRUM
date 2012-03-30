package br.com.vrum.locadora.interfaces;

import java.util.List;

import br.com.vrum.locadora.pojo.Usuario;

public interface IUsuario {
	public void insert(Usuario usuario);
	public void update(Usuario usuario);
	public void delete(Usuario usuario);
	public Usuario search(Long id);
	public Usuario autentica(String login, String senha);
	
	public List<Usuario>  	selectAll();
	

}
