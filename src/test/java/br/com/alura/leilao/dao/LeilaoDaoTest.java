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
		Usuario usuario = criarUsuario();
		Leilao leilao = new Leilao("Mochila",new BigDecimal("70"), LocalDate.now(),usuario); 
		leilao = dao.salvar(leilao);
		Leilao salvo   = dao.buscarPorId(leilao.getId());
		Assert.assertNotNull(salvo);

	}
	
	
	@Test
	void deveAtualizarLeilao() {
		Usuario usuario = criarUsuario();
		Leilao leilao = new Leilao("Mochila",new BigDecimal("70"), LocalDate.now(),usuario); 
		leilao.setNome("CARRO");
		leilao.setValorInicial(new BigDecimal("1000"));
		leilao = dao.salvar(leilao);
		Leilao salvo   = dao.buscarPorId(leilao.getId());
		Assert.assertEquals("CARRO",salvo.getNome());
		Assert.assertEquals(new BigDecimal("1000"),salvo.getValorInicial());


	}
	

	private Usuario criarUsuario() {
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.persist(usuario);
		return usuario;
	}
}
