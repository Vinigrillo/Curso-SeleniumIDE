package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteCampoTreinamento{

	private DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver()
				.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();

	}

	@After
	public void finaliza(){
		killDriver();
	}
	
	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Vinicius");
		Assert.assertEquals("Vinicius", dsl.obterValorCampo("elementosForm:nome"));

	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste/n/naasldjdlks/nUlitma linha");
		Assert.assertEquals("Teste/n/naasldjdlks/nUlitma linha", dsl.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveInteragirComCombo() {

		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau incompleto");
		Assert.assertEquals("2o grau incompleto", dsl.obterValorCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Superior")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		combo.deselectByVisibleText("Corrida");

		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	}

	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");

		WebElement button = getDriver().findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", button.getAttribute("value"));
	}

	@Test
	public void deveInteragirComlinks() {
		dsl.clicarLinks("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test

	public void deveBuscarTextosNaPagina() {
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}

	@Test
	public void testJavaScript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		// js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'Radio'");

		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}

	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Usuario A", "Botao", "elementosForm:tableUsuarios");
	}
}