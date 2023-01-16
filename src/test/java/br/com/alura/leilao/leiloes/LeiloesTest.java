package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

	private LeiloesPage leiloesPage;
	private CadastroLeilaoPage paginaDeCadastro;


	@AfterEach
	public void after() {
		this.leiloesPage.fechar();
	}
	
	@BeforeEach
	public void beforEach() {
		LoginPage loginPage = new LoginPage();
		sleep();
		loginPage.preencheFormularioLogin("fulano", "pass");
		sleep();
		this.leiloesPage = loginPage.submeterFormularioLogin();
		sleep();
		this.paginaDeCadastro =  this.leiloesPage.carregarFormulario(); 
	}

	@Test
	public void deveriaCadastrarLeilao() {
		
		
		String hoje  = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "leilao do dia "+ hoje;
		String valor = "500.00";
		sleep();
		this.leiloesPage = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		Assert.assertTrue(leiloesPage.isLeilaoCadastrado(nome,valor,hoje));
		sleep();
	}
	
  @Test
  public void deveriaValidarCadastroDeLeilao() {
		this.leiloesPage = paginaDeCadastro.cadastrarLeilao("", "", "");
		Assert.assertFalse(this.paginaDeCadastro.isPaginaAtual());
		Assert.assertTrue(this.leiloesPage.isPaginaAtual());

		sleep();
		Assert.assertTrue(this.paginaDeCadastro.isMensagemValidacaoVisivel());
		sleep();
  }

	private void sleep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
