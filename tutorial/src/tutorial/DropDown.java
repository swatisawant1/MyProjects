package tutorial;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DropDown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Swati\\Documents\\tutorial\\driver\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.spicejet.com/#sourceautocomplete");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("r-1862ga2 r-1loqt21 r-1i10wst r-tceitz r-u8s1d css-76zvg2")).click();
		driver.findElement(By.xpath("(//div[@class='css-76zvg2 r-12rm3iy r-ubezar'])[5]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='css-1dbjc4n r-b5h31w r-95jzfe'] //div[@class='css-76zvg2 r-cqee49 r-ubezar r-1kfrs79'])[2]")).click();
	
	}

}
