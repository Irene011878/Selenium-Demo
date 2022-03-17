package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class SeleniumDemo {
	public String baseUrl = ("https://demoqa.com/");
	String driverPath = "src/test/resources/chromedriver";
	public WebDriver driver;
	
	@BeforeTest
	private void startTesting() {
		System.out.println("This is a Before Text");
		
	}
	@BeforeMethod
	private void startDriver() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	@Test
	public void sampleTestMethod() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		Actions action = new Actions(driver);
        WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../.."));
        executor.executeScript("arguments[0].click();", elementsSection);
		WebElement buttonSection = driver.findElement(By.xpath("//span[text()='Buttons']/.."));
		action.moveToElement(buttonSection).perform();
		executor.executeScript("arguments[0].click();", buttonSection);
		
		WebElement singleClickButton = driver.findElement(By.xpath("//div[@class='mt-4'][2]//button"));
		singleClickButton.click();
		WebElement dynamicMessage = driver.findElement(By.cssSelector("#dynamicClickMessage"));
		Assert.assertEquals(dynamicMessage.getText(), "You have done a dynamic click");
	}
	
	@Test
	public void sampleTestMethod2() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30); //es para hacer esperas
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		Actions action = new Actions(driver); //obj clase action para realizar comandos que luego podemos ejecutar
		WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../.."));
        executor.executeScript("arguments[0].click();", elementsSection);
		WebElement linkSection = driver.findElement(By.xpath("//span[text()='Links']/..")); //con esto localizo
		wait.until(ExpectedConditions.visibilityOf(linkSection)); //que espere hasta que se cumpla las condiciones
		executor.executeScript("arguments[0].scrollIntoView(true);", linkSection);
		//executor.executeScript("arguments[0].click();", linkSection);
		linkSection.click();
		Thread.sleep(5000);
		WebElement linkBtn = driver.findElement(By.cssSelector("#simpleLink"));  //#simpleLink //*[@id="simpleLink"]
		linkBtn.click();
		String mainWindow = driver.getWindowHandle();
		for(String window: driver.getWindowHandles()) {
			driver.switchTo().window(window); //van a checar todas las ventanas abiertas y se va quedar en la ultima abierta
			
		}
		Assert.assertEquals("ToolsQA", driver.getTitle());
	}
	
	@Test
	public void sampleTestMethod3() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,30); //es para hacer esperas
		JavascriptExecutor executor = (JavascriptExecutor)driver; 
		Actions action = new Actions(driver); //obj clase action para realizar comandos que luego podemos ejecutar
		WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']/../.."));
        executor.executeScript("arguments[0].click();", elementsSection);
		WebElement dynamicPrpertiesSection = driver.findElement(By.xpath("//span[contains(text(),'Dynamic Properties')]")); //con esto localizo
		wait.until(ExpectedConditions.visibilityOf(dynamicPrpertiesSection)); //que espere hasta que se cumpla las condiciones
		executor.executeScript("arguments[0].scrollIntoView(true);", dynamicPrpertiesSection);
		dynamicPrpertiesSection.click();
		Thread.sleep(5000);
		WebElement dynamicBtn = driver.findElement(By.xpath("//button[@id='colorChange']"));  //#simpleDynamic //*[@id="simpleDynamic"]
		dynamicBtn.click();
		String mainWindow = driver.getWindowHandle();
		for(String window: driver.getWindowHandles()) {
			driver.switchTo().window(window); //van a checar todas las ventanas abiertas y se va quedar en la ultima abierta
			
		}
		Assert.assertEquals("ToolsQA", driver.getTitle());
	}
	
	
	
	@AfterMethod
    public void terminateBrowser() throws InterruptedException{
		Thread.sleep(5000);
        driver.quit();
    }

}

