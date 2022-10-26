package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";
	private ChromeDriver browser;

	public LeiloesPage(ChromeDriver browser) {
		this.browser = browser;
	}

	public void fechar() {
		this.browser.quit();

	}

	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_CADASTRO_LEILAO);
		return new CadastroLeilaoPage(browser);
	}

	public boolean isLeilaoCadastrado(String nome, String valor, String hoje) {
		WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3"));

		return colunaNome.getText().equals(nome) 
				&& colunaValorInicial.getText().equals(valor) 
				&& colunaDataAbertura.getText().equals(hoje) ;
	}

}
