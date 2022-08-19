package br.com.alura.leilao.dao;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtuil;

class UsuarioDaoTest {

	private UsuarioDao dao;

	private EntityManager em;


	@Test
	void deveBuscarUsuarioCadastradoPorUserName() {
		em = JPAUtuil.getEntityManager();
		this.dao = new UsuarioDao(em);
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();

		Usuario usuarioResult = this.dao.buscarPorUsername("fulano");
		Assert.assertNotNull(usuarioResult);
	}
	
	@Test
	void naoDeveBuscarUsuarioCadastradoPorUserName() {
		em = JPAUtuil.getEntityManager();
		this.dao = new UsuarioDao(em);
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();

		Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
	}

}
