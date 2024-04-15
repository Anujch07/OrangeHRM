package testFlipkart;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.All_Linkes;

public class Get_all_Value_fromlist implements All_Linkes {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get(base_url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement we = driver.findElement(By.xpath(search_xpath));
		we.sendKeys("Mobile");
		we.sendKeys(Keys.ENTER);

		List<WebElement> list = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		int length = list.size();
		System.out.println(length);

		if (length != 0) {

			for (WebElement input_Element : list) {

				String mob_list = input_Element.getText();
				System.out.println(mob_list);

				// Motorola G34 5G (Charcoal Black, 128 GB)
				boolean status = input_Element.getText().contains("Motorola G34 5G (Charcoal Black, 128 GB)");
				System.out.println("Status: " + status);
				
				if(status == true) {
					 input_Element.click();
				}else {
					System.out.println("Status Doesn't Match");
				}

			}
		}else {
			System.out.println("Length is Equal to Zero");
		}

	}
}
