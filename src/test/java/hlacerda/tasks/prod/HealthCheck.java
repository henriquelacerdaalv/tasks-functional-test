package hlacerda.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;



public class HealthCheck {

	
	@Test
	public void healthCheck() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		try {
			driver.get("http://172.18.0.1:9999/tasks/");
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		}
		finally {
			driver.quit();
		}
	}
}
