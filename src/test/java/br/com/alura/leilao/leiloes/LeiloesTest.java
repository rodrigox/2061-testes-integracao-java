package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
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
		
		String hoje  = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "leilao do dia "+ hoje;
		String valor = "500,00";
		
		this.leiloesPage = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome,valor,hoje));
	}
}
