package br.ce.wcaquino.tests;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.Propriedades;
import br.ce.wcaquino.pages.HomePage;
import br.ce.wcaquino.pages.MenuPage;

	
public class SaldoTest extends BaseTest {
	
	HomePage homePage = new HomePage();
	MenuPage menuPage = new MenuPage();
	
	@Test
	public void testSaldoConta(){
		menuPage.acessarTelaPrincipal();
		Assert.assertEquals("500.00", homePage.obterSaldoConta(Propriedades.NOME_CONTA_ALTERADA));
	}
	
}
