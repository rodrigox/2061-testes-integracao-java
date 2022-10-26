package br.com.alura.leilao.leiloes;

import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroLeilaoPage {

 	private ChromeDriver browser;

	public CadastroLeilaoPage(ChromeDriver browser) {
		
		this.browser =browser;
		//browser.navigate().to(URL_LEILOES);
	}

	public void fechar() {
		this.browser.quit();

	}

	}
