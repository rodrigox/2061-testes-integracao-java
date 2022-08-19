package br.com.alura.leilao.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtuil;

class UsuarioDaoTest {

	private UsuarioDao dao;

	private EntityManager em;

	@BeforeEach
    private void init () {
    	this.em = JPAUtuil.getEntityManager();
    	this.dao =  new UsuarioDao(em);
		em.getTransaction().begin();

    }
	@AfterEach
	private void afterEach() {
		em.getTransaction().rollback();
	}
	@Test
	void deveBuscarUsuarioCadastradoPorUserName() {
		Usuario usuario = criarUsuario();
		Usuario usuarioResult = this.dao.buscarPorUsername(usuario.getNome());
		Assert.assertNotNull(usuarioResult);
	}
	
	@Test
	void naoDeveBuscarUsuarioCadastradoPorUserName() {
		criarUsuario();
		Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("beltrano"));
	}

	private Usuario criarUsuario() {
		Usuario usuario = new Usuario("fulano", "fulano@email.com", "12345678");
		em.persist(usuario);
		return usuario;
	}
}
