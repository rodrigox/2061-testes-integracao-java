package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	protected WebDriver browser;
	
	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\dev\\AluraRepo\\2061-testes-integracao-java\\drivers\\chromedriver.exe");
		if(browser == null) {
			this.browser = new ChromeDriver();
		}else {
			this.browser = browser;
		}
		//Timeout selenium 
		this.browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	public void fechar() {
		this.browser.quit();

	}

}
