package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;

@Repository
public class LanceDao {

	private EntityManager em;

	
	public LanceDao(EntityManager em) {
		super();
		this.em = em;
	}

	public Lance salvar(Lance lance) {
		return em.merge(lance);
	}

	public Lance buscarMaiorLanceDoLeilao(Leilao leilao) {
		return em.createQuery("SELECT l FROM Lance l WHERE l.valor = (SELECT MAX(lance.valor) FROM Lance lance)", Lance.class)
				.getSingleResult();
	}
	
}
