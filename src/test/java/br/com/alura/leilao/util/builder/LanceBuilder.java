package br.com.alura.leilao.util.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class LanceBuilder {
	
	private LocalDate data;
	private  Usuario usuario;
	private BigDecimal valor;
	private Leilao leilao;
	
	
	public LanceBuilder data (LocalDate data) {
		this.data = data;
		return this;
	}
	
	public LanceBuilder usuario (Usuario usuario) {
		this.usuario = usuario;
		return this;
	}
	public LanceBuilder valor(String valor) {
		this.valor = new BigDecimal(valor);
		return this;
	}
	public LanceBuilder leilao (Leilao leilao) {
		this.leilao = leilao;
		return this;
	}
	
	
	public Lance build () {
		Lance lance = new Lance();
		lance.setData(data);
		lance.setLeilao(leilao);
		lance.setUsuario(usuario);
		lance.setValor(valor);
		return lance;
	}
	
	
	
	

}
