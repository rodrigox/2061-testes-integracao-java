package br.com.alura.leilao.util.builder;

import br.com.alura.leilao.model.Usuario;

public class UsuarioBuilder {

	private String nome; 
	private String email;
	private String senha;
	
	
	public UsuarioBuilder nome (String nome) {
		this.nome = nome;
		return this;
	}
	
	public UsuarioBuilder email (String email) {
		this.email = email;
		return this;
	}
	
	public UsuarioBuilder senha (String senha) {
		this.senha = senha;
		return this;
	}

	
	public Usuario build () {
		return new Usuario(nome,email,senha);
	}
}
