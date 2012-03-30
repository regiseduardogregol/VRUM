package br.com.vrum.locadora.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import br.com.vrum.locadora.interfaces.IUsuario;
import br.com.vrum.locadora.pojo.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioController {
	
	@EJB
	private IUsuario user;
	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String login;
	private String senha;
	static boolean logado = false;
	private String variavel = "";
	private String mensagem = "";
	
	
	
	public void setUser(IUsuario user) {
		this.user = user;
	}
	public IUsuario getUser() {
		return user;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	
	public UsuarioController(){
		usuario = new Usuario();
	}

	public Usuario autenticaUsuario(ActionEvent event){
		UIComponent link = event.getComponent();
		UIComponent link2 = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("login");
		UIParameter param2 = (UIParameter) link2.findComponent("senha");
		login = (String) param.getValue();
		senha = (String) param2.getValue();
		try{
			usuario = user.autentica(login, senha);
			variavel = usuario.getNome();
			logado = true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("LOGIN ou SENHA INVÁLIDA");
			logado = false;
		}
		return usuario;
	}
	
	public void verificaUser() throws IOException{
		HttpServletResponse response = 
			(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if(logado == true){
			mensagem = "Seja Bem Vindo";
			response.sendRedirect("index.faces");
		}else{
		 response.sendRedirect("error.faces"); 
		 mensagem = "Você Ainda não está Cadastrado";
		}
		
	}
	
	public String doNew(){
		return "admin/usuario/newUsuario.faces";
	}
	
	public String cadastraUsuario(){
		user.insert(usuario);
		return "admin/usuario/newUsuario.faces";
	}
	
	
	public void alterarUsuario(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaAlterar");
		Long id = (Long) param.getValue();
		Usuario u = user.search(id);
		usuario.setNome(u.getNome());
		user.update(usuario);
	}
	
	public void deletarUsuario(ActionEvent event){
		UIComponent link = event.getComponent();
		UIParameter param = (UIParameter) link.findComponent("idParaDeletar");
		Long id = (Long) param.getValue();
		Usuario u = user.search(id);
		user.delete(u);
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Usuario> getUsuarios() {
		usuarios = user.selectAll();
		return usuarios;
	}
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	public String getVariavel() {
		return variavel;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
}
