package br.com.alura.leilao.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtuil;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;

class LeilaoDaoTest {

	private LeilaoDao dao;

	private EntityManager em;

	@BeforeEach
    private void init () {
    	this.em = JPAUtuil.getEntityManager();
    	this.dao =  new LeilaoDao(em);
		em.getTransaction().begin();

    }
	@AfterEach
	private void afterEach() {
		em.getTransaction().rollback(); 
	}
	
	@Test
	void deveCadastrarLeilao() {
		Usuario usuario = new UsuarioBuilder()
				.nome("fulano")
				.email("fulano@gmail.com")
				.senha("12345678")
				.build();
		em.persist(usuario);

		
		Leilao leilao = new LeilaoBuilder()
				.nome("Mochila")
				.valorInicial("70")
				.usuario(usuario
				).data(LocalDate.now()).build();
		
		leilao = dao.salvar(leilao);
		Leilao salvo   = dao.buscarPorId(leilao.getId());
		Assert.assertNotNull(salvo);

	}
	
	
	@Test
	void deveAtualizarLeilao() {

		Usuario usuario = new UsuarioBuilder()
		.nome("fulano")
		.email("fulano@gmail.com")
		.senha("12345678")
		.build();
		em.persist(usuario);

		
		Leilao leilao = new LeilaoBuilder()
				.nome("CARRO")
				.valorInicial("1000")
				.usuario(usuario
				).data(LocalDate.now()).build();

		leilao = dao.salvar(leilao);
		
		Leilao salvo   = dao.buscarPorId(leilao.getId());
		Assert.assertEquals("CARRO",salvo.getNome());
		Assert.assertEquals(new BigDecimal("1000"),salvo.getValorInicial());


	}
	

	
}
