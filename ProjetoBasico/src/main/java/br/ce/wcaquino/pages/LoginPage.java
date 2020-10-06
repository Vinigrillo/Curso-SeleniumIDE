package br.ce.wcaquino.pages;

import br.ce.wcaquino.core.BasePage;
import br.ce.wcaquino.core.DriverFactory;

public class LoginPage extends BasePage {

	public void acessarTelaInicia() {
		DriverFactory.getDriver().get("https://seubarriga.wcaquino.me/");
	}
	
	public void setEmail(String email) {
		escreve("email", email);
	}
	
	public void setSenha(String senha) {
		escreve("senha", senha);
	}
	
	public void entrar() {
		clicarBotaoPorTexto("Entrar");
	}
	
	public void logar(String email,String senha) {
		setEmail(email);
		setSenha(senha);
		entrar();
		
	}
}
