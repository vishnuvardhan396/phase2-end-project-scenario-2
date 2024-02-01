package starthealthScenario2;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestStarthealthPage {
	
	WebDriver driver;
	
	@BeforeClass
	public void startBrowser()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.starhealth.in/");
	}
	
	@Test(priority='1')
	public void click_onPlans() throws InterruptedException
	{
		Actions a = new Actions(driver);
		WebElement plan = driver.findElement(By.xpath("//div[@class='Header_nav-link-container__nlUUp dropdown-active flex only-desktop']/descendant::img[2]"));
		a.moveToElement(plan).build().perform();
		Thread.sleep(1500);
	}
	
	@Test(priority='2')
	public void click_on_option() throws InterruptedException
	{
		WebElement formyfamilylink = driver.findElement(By.xpath("//div[@class='ant-popover-inner']/descendant::a[2]"));
		formyfamilylink.click();
		Thread.sleep(4000);
	}
	
	@Parameters({"Name","Mobile","pin"})
	@Test(priority='3')
	public void enter_user_details(String Name, String Mobile, String pin)
	{
		driver.findElement(By.id("name")).sendKeys(Name);
		driver.findElement(By.id("phoneNumber")).sendKeys(Mobile);
		driver.findElement(By.id("pinCode")).sendKeys(pin);
		
		// click on get quite button
		driver.findElement(By.xpath("")).click();
	}
	
	@Test(priority='4',dataProvider = "testdata")
	public void navigate_back(String expectedname, String expectedphone,String expectedpin ) throws InterruptedException
	{
		driver.navigate().back();
		Thread.sleep(1500);
	
	String actualname =	driver.findElement(By.id("name")).getText();
	Assert.assertEquals(actualname, expectedname);
	
	String actualphone =	driver.findElement(By.id("phoneNumber")).getText();
	Assert.assertEquals(actualphone, expectedphone);

	String actualpin =	driver.findElement(By.id("pinCode")).getText();
	Assert.assertEquals(actualpin, expectedpin);
		
		
	}
	
	@DataProvider(name="testdata")
	public   Object[][] datasupply() throws FileNotFoundException
	{
		// get the data from excel sheet 
		
		Object[][] inputdata = Xls_DataProvider.getTestData("Sheet1");
		
		return inputdata;
		
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}

}