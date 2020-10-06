package br.ce.wcaquino.test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class Desafio {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	
	@Before
	public void inicializa(){
		driver = new ChromeDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}
	
	@After
	public void finaliza(){
		driver.quit();	
	}
	
	@Test
	public void nomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void sobrenomeObrigatorio() {
		page.setNome("Vinicius");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void sexoObrigatorio() {
		page.setNome("Vinicius");
		page.setSobrenome("Grillo");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void VegetarianoECarne() {
		page.setNome("Vinicius");
		page.setSobrenome("Grillo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariana();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?",dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void oQueEhEsporte() {
		page.setNome("Vinicius");
		page.setSobrenome("Grillo");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Karate", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?",dsl.alertaObterTextoEAceita());
	}
}
