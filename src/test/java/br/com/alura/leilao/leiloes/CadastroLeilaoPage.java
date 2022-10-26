package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroLeilaoPage {

	private ChromeDriver browser;

	public CadastroLeilaoPage(ChromeDriver browser) {

		this.browser = browser;
	}

	public void fechar() {
		this.browser.quit();

	}

	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {

		this.browser.findElement(By.id("nome")).sendKeys(nome);
		this.browser.findElement(By.id("valorInicial")).sendKeys(valorInicial);
		this.browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
		this.browser.findElement(By.id("button-submit")).submit();
		
		return new LeiloesPage(browser);
	}

}
