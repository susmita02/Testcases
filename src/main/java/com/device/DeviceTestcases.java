package com.device;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        driver.manage().timeouts().implicitlyWait(40000, TimeUnit.SECONDS);
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
       System.out.println("Device page displayed");


       Set<String> handler = driver.getWindowHandles();
       Iterator<String> it = handler.iterator();
       String deviceList = it.next();
       System.out.println("DeviceList window id "    +deviceList);

       wait = new WebDriverWait(driver, 20);   // wait for 20 seconds
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@type = 'button' and @class = 'btn btn-primary']"))));
       driver.findElement(By.xpath("//button[@type = 'button' and @class = 'btn btn-primary']")).click();
       System.out.println("Add device pop up opened");
      
       
       String devCat = it.next();
       wait = new WebDriverWait(driver, 20);
       driver.switchTo().window(devCat);
       System.out.println("Devicecategory window id "    +devCat);
       
          // wait for 20 seconds
       WebElement adddevicedropdown=   wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("#form-control ui-select-search ng-pristine ng-valid ng-touched"))));
       Select select = new Select(adddevicedropdown);
       try {
       select.selectByVisibleText("Dark");
       } catch (Exception e) {
      e.printStackTrace();
       }
 
        driver.findElement(By.xpath("//button[contains(text(),'Add Device')]")).click();
    
       System.out.println(" Add device page opened");

    
      

 }
	
   @AfterMethod()
	   
	   public void tearDown() {
		   driver.quit();
	   }
   }

