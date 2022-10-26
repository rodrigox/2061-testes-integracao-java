package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private ChromeDriver browser;

	public LoginPage() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\dev\\AluraRepo\\2061-testes-integracao-java\\drivers\\chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		this.browser.quit();

	}

	public void preencheFormularioLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public LeiloesPage submeterFormularioLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);

	}

	public boolean isPaginaLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public String getNumeUsuarioLogado() {
		try {

			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaDeLances() {
		browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}

	public boolean isPaginaLoginComDadosInvalidos() {
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");

	}

}
