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

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private ChromeDriver browser;

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\dev\\AluraRepo\\2061-testes-integracao-java\\drivers\\chromedriver.exe");
	}

	@BeforeEach
	public void BeforeEach() {
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);

	}

	@AfterEach
	public void after() {
		this.browser.quit();
	}

	@Test
	public void deveriaLogarComDadosValidos() {

		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

	}

	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assert.assertTrue("fulano", browser.getPageSource().contains("Usuário e senha inválidos."));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());

	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemLogar() {
		browser.navigate().to("http://localhost:8080/leiloes/2");
		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login"));
		Assert.assertFalse(browser.getPageSource().contains("Dados do Leilão"));


	}

}
