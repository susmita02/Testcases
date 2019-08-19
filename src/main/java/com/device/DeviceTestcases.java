package com.device;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeviceTestcases {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeMethod
	
	public void browserSetup() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\Documents\\Drivers\\chromedriver_75\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
        driver.get("https://www.vzwdt.com/orbit");
		
	}
	
	@Test
	
	public void loginPage() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("susmitaadm");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345");
		driver.findElement(By.xpath("//span[contains(text(),'Login')]")).click();
		String homepagetitle =driver.getTitle();
		System.out.println(homepagetitle);
		
		driver.findElement(By.xpath("//a[contains(@href,'device') and @class='container-item']")).click();
		
		Thread.sleep(30);
//		WebElement header = driver.findElement(By.xpath("//li[@class='orbitAllPageTitle']"));
		System.out.println("Device page displayed");
		
		WebElement element = driver.findElement(By.xpath("//button[@type = 'button' and @class = 'btn btn-primary']"));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
		
//		driver.findElement(By.xpath("//button[@type = 'button' and @class = 'btn btn-primary']")).click();
		
		WebElement adddevicedropdown = driver.findElement(By.xpath("//span[contains(text(),'Select Device Category')]"));
		Select category = new Select(adddevicedropdown);
		category.selectByVisibleText("Dark");
		
		driver.findElement(By.xpath("//button[contains(text(),'Add Device')]")).click();
		
		System.out.println(" Add device page opened");
		
	
		
		
	}
	
   @AfterMethod()
	   
	   public void tearDown() {
		   driver.quit();
	   }
   }

