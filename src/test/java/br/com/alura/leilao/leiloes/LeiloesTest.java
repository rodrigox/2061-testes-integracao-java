package br.com.alura.leilao.leiloes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

	private LeiloesPage leiloesPage;

	@AfterEach
	public void after() {
		this.leiloesPage.fechar();
	}


	@Test
	public void deveriaCadastrarLeilao() {
		LoginPage loginPage = new LoginPage();
		loginPage.preencheFormularioLogin("fulano", "pass");
		this.leiloesPage = loginPage.submeterFormularioLogin();
		CadastroLeilaoPage paginaDeCadastro =  this.leiloesPage.carregarFormulario(); 
	}
}
