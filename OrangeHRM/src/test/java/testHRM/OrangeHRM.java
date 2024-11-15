package testHRM;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utility.All_Linkes;

public class OrangeHRM implements All_Linkes {

	WebDriver driver;

	@BeforeTest
	public void setUp() {

		System.out.println("Before Test Excution");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@Test(priority = 1, enabled = false)
	public void DologinTestwithInvalidCredential() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("1234");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		String msg_expected = "Invalid credentials";
		String msg_actual = driver.findElement(By.xpath(exep_xpath)).getText();

		Assert.assertEquals(msg_expected, msg_actual);
		Thread.sleep(1000);
	}

	@Test(priority = 2, enabled = false)
	public void loginTestwithValidCredential() {

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Verify if login was successful by checking the page title.
		String pageTitle = driver.getTitle();
		logOut();
		Assert.assertEquals(pageTitle, "OrangeHRM");
	}

	@Test(priority = 3, enabled = false)
	public void addEmployee() throws InterruptedException, IOException {

		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Radhe");
		driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys("Gopal");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Bhathacharya");
		driver.findElement(By.xpath(emp_id_xpath)).sendKeys("1234");
//		WebElement emp_id = driver.findElement(By.xpath(emp_id_xpath));
//		emp_id.clear();
//		emp_id.sendKeys("3456");

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-plus']")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("D://Auto_Test//Project1//FilesUpload//ImageUpload.exe");
		Thread.sleep(5000);

		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Verify the Employee is Successfully add in the personal deatils.
		String confi_msg = driver.findElement(By.xpath("//h6[normalize-space()='Personal Details']")).getText();
		logOut();
		Assert.assertEquals(confi_msg, "Personal Details");

	}

	@Test(priority = 4, enabled = false)
	public void searchEmployeeByName() throws InterruptedException {

		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		driver.findElements(By.tagName("input")).get(1).sendKeys("Anuj Kumar Chauhan");
		// driver.findElements(By.tagName("input")).get().sendKeys("3456");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		// Verify add Record found.
		Thread.sleep(3000);
		List<WebElement> element = driver.findElements(By.xpath("//span[@class='oxd-text oxd-text--span']"));
		String expected_msg = "Record Found";
		String actual_msg = element.get(0).getText();
		logOut();
		Assert.assertTrue(actual_msg.contains(expected_msg));

		/*
		 * for(int i=0; i<element.size(); i++) { System.out.println(" At index :"+ i +
		 * "Text is :" + element.get(i).getText()); }
		 */
	}

	@Test(priority = 5, enabled = false)
	public void searchEmployeeById() throws InterruptedException {

		String empId = "0389";
		String actual_msg = " ";
		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		driver.findElements(By.tagName("input")).get(2).sendKeys(empId);
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		Thread.sleep(3000);

//		JavascriptExcuter excuter = (JavascriptExcuter) driver;
//		excuter.excuteScript("window.scrollBy(0, " + 500 + ")")

		List<WebElement> row_list = driver.findElements(By.xpath("(//div[@role='row'])"));

		if (row_list.size() > 1) {
			actual_msg = driver.findElement(By.xpath("((//div[@role='row'])[2]/div[@role='cell'])[2]")).getText();
		}

		logOut();
		Assert.assertEquals(empId, actual_msg);

	}

	@Test(priority = 6, enabled = false)
	public void fileUpload() throws IOException, InterruptedException {

		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item']")).click();
		driver.findElement(By.linkText("Data Import")).click();
		driver.findElement(By.xpath("//div[@class='oxd-file-button']")).click();

		Runtime.getRuntime().exec("D://Auto_Test//Project1//FilesUpload//FileUpload.exe");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[normalize-space()='Upload']")).submit();
		logOut();
	}

	@Test(priority = 7, enabled = false)
	public void deleteEmployeeByName() throws InterruptedException {

		logIn();

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
		driver.findElements(By.tagName("input")).get(1).sendKeys("emily");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		// ----------------------------------Delete Employee------------------------//
		Thread.sleep(3000);
		// Delete Button1.
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']")).click();
		// Delete Button(popUp).
		driver.findElement(By.xpath("//button[normalize-space()='Yes, Delete']")).click();

		// Verify Message "No Record Found"
		String msg = driver.findElement(By.xpath("//span[normalize-space()='No Records Found']")).getText();
		Assert.assertEquals(msg, "No Records Found");
		Thread.sleep(4000);
		logOut();
	}

	@Test(priority = 8, enabled = false)
	public void ListEmployee () throws InterruptedException {
		
		logIn();
		
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Employee List']")).click();
	
		Thread.sleep(3000);
		List<WebElement> totalLinksElements = driver.findElements(By.xpath("//ul[@class='oxd-pagination__ul']/li"));
		int totalLinks = totalLinksElements.size();
		
		for(int i=0; i<totalLinks; i++) {
			
			try {
			String currentLinkText = totalLinksElements.get(i).getText();
			
			int page = Integer.parseInt(currentLinkText);
			System.out.println("Page :" + page);
			
			totalLinksElements.get(i).click();
			Thread.sleep(2000);
			List<WebElement> emp_list = driver.findElements(By.xpath("//div[@class='oxd-table-card']/div/div[3]"));
			for(int j=0; j<emp_list.size(); j++) {
				
				// print First Name Of Each Row
				String firstName = emp_list.get(j).getText();
				System.out.println(firstName);
			   }
			}
			catch(Exception e){
				System.out.println("Not a Number.");
			}
		}
		
		Thread.sleep(5000);
		logOut();
	}
	
	@Test(priority = 9, enabled = true)
	public void applyLeave() throws InterruptedException {
		
		logIn();
		
		driver.findElement(By.linkText("Leave")).click();
		driver.findElement(By.linkText("Apply")).click();
		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")).click();
		driver.findElement(By.xpath("//*[contains(text(),'CAN')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='oxd-date-input']/input)[2]")).sendKeys("2024-15-04");
		driver.findElement(By.xpath("(//div[@class='oxd-date-input']/input)[1]")).sendKeys("2024-05-04");
		
		driver.findElement(By.tagName("textarea")).sendKeys("This is my Personal Leave");
		driver.findElement(By.xpath("//button[normalize-space()='Apply']")).click();
		
		
		Thread.sleep(5000);
		logOut();
		
	}

	public void logIn() { // Login Method.

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	public void logOut() { // LogOut Method.

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();

	}

	@AfterTest
	public void tearDown() throws InterruptedException {

		// Thread.sleep(3000);
		// logOut();
		Thread.sleep(5000);// wait 5 sec before logout.
		driver.quit();
	}

}
