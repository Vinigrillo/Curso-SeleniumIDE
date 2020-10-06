package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
public class TestePrime {

	private DSL dsl;
	
	@Before
	public void inicializa(){
		dsl = new DSL();
	}
	
	@After
	public void finaliza() {
		DriverFactory.getDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadioPrime("//input[@id='j_idt728:console:0']/../..//span");		
		Assert.assertTrue(dsl.isRadioMarcado("j_idt728:console:0"));
		dsl.clicarRadioPrime("//label[.='PS4']/..//span");		
		Assert.assertTrue(dsl.isRadioMarcado("j_idt728:console:1"));
		}
	
	
	@Test
	public void deveInteragirComSelectPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt728:console", "Xbox One");
		Assert.assertEquals("Xbox One", dsl.obterTexto("j_idt728:console_label"));
	}
}


