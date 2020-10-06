package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

	@Test
	public void teste() {
	//	WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		//driver.manage().window().setSize(new Dimension(1200, 765));
		//driver.manage().window().maximize();
		driver.get("http://www.google.com");
		Assert.assertEquals("Google",driver.getTitle());
		driver.quit();
	}
}
 