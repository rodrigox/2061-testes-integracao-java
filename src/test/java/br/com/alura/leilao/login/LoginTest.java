package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private LoginPage loginPage;

	@BeforeEach
	public void beforeEach() {
		this.loginPage = new LoginPage();
	}

	@AfterEach
	public void after() {
		this.loginPage.fechar();
	}

	@Test
	public void deveriaLogarComDadosValidos() {
		loginPage.preencheFormularioLogin("fulano", "pass");
		loginPage.submeterFormularioLogin();
		Assert.assertFalse(loginPage.isPaginaLogin());
		Assert.assertEquals("fulano", loginPage.getNumeUsuarioLogado());
	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		loginPage.preencheFormularioLogin("invalido", "123");
		loginPage.submeterFormularioLogin();

		Assert.assertTrue(loginPage.isPaginaLoginComDadosInvalidos());
		Assert.assertNull("fulano", loginPage.getNumeUsuarioLogado());
		Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
	}

	@Test
	public void naoDeveriaAcessarPaginaRestritaSemLogar() {
		loginPage.navegaParaPaginaDeLances();
		Assert.assertTrue(loginPage.isPaginaLogin());
		Assert.assertFalse(loginPage.contemTexto("Dados do Leilão"));

	}

}
