package br.com.alura.leilao.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtuil;
import br.com.alura.leilao.util.builder.LanceBuilder;
import br.com.alura.leilao.util.builder.LeilaoBuilder;
import br.com.alura.leilao.util.builder.UsuarioBuilder;

class LanceDaoTest {

	
	private LanceDao dao;
	
	private LeilaoDao leilaoDao;

	private EntityManager em;

	@BeforeEach
    private void init () {
    	this.em = JPAUtuil.getEntityManager();
    	this.dao =  new LanceDao(em);
    	this.leilaoDao =  new LeilaoDao(em);

		em.getTransaction().begin();

    }
	
	@Test
	void deveBuscarMaiorLance() {
		Usuario usuario = criarUsuario();
		
		Leilao leilao = new LeilaoBuilder()
				.nome("Leilao da Receita").valorInicial("100").data(LocalDate.now()).usuario(usuario).build();
		leilao = leilaoDao.salvar(leilao);

		
		Lance  lance1 = new LanceBuilder().data(LocalDate.now()).usuario(usuario).valor("100").leilao(leilao).build();
		lance1 = dao.salvar(lance1);
		
		Lance  lance2 = new LanceBuilder().data(LocalDate.now()).usuario(usuario).valor("200").leilao(leilao).build();
		lance2 = dao.salvar(lance2);

		
		Lance  lance3 = new LanceBuilder().data(LocalDate.now()).usuario(usuario).valor("300").leilao(leilao).build();
		lance3 = dao.salvar(lance3);


		Lance maiorLance = dao.buscarMaiorLanceDoLeilao(leilao);
		assertEquals(new BigDecimal("300"), maiorLance.getValor());		
				
		
	}
	
	private Usuario criarUsuario() {
		Usuario usuario = new UsuarioBuilder()
				.nome("fulano")
				.email("fulano@gmail.com")
				.senha("12345678")
				.build();
		em.persist(usuario);
		return usuario;
	}
	

}
