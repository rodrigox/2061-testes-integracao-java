package br.com.alura.leilao.leiloes;

import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
	private ChromeDriver browser;

	public LeiloesPage(ChromeDriver browser) {
		
		this.browser =browser;
		//browser.navigate().to(URL_LEILOES);
	}

	public void fechar() {
		this.browser.quit();

	}

	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_CADASTRO_LEILAO);
		return new CadastroLeilaoPage(browser);
	}

}
