package pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));

	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));

	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));

	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));

	}

	public WebElement getZip() {
		return this.driver.findElement(By.name("user_zip"));

	}

	public Select getCountry() {
		WebElement country = this.driver.findElement(By.name("user_country_id"));
		Select count = new Select(country);
		return count;

	}

	public Select getState() {
		WebElement state = this.driver.findElement(By.name("user_state_id"));
		Select stat = new Select(state);
		return stat;

	}

	public Select getCity() {
		WebElement city = this.driver.findElement(By.name("user_city"));
		Select cit = new Select(city);
		return cit;

	}

	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfoFrm']/div[5]/div/fieldset/input"));

	}

	public WebElement getUploadImageButton() {
		return this.driver.findElement(By.xpath("//*[@id=\"profileInfo\"]/div/div[1]/div/a"));

	}

	public WebElement getDeleteImageButton() {
		return this.driver.findElement(By.xpath("//*[@id='profileInfo']/div/div[1]/div/a[2]/i"));

	}

	public void uploadImage() throws Exception {
		js.executeScript("arguments[0].click();", this.getUploadImageButton());
		WebElement uploadImage = this.driver.findElement(By.xpath("//input[@name = 'file']"));
		String imagePath = new File("images/profile.jpg").getCanonicalPath();
		uploadImage.sendKeys(imagePath);
	}

	public void deleteImage() {
		js.executeScript("arguments[0].click()", this.getDeleteImageButton());

	}

	public void updateProfile(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws Exception {

		this.getFirstName().clear();
		this.getLastName().clear();
		this.getAddress().clear();
		this.getPhone().clear();
		this.getZip().clear();

		this.getFirstName().sendKeys(firstName);
		this.getLastName().sendKeys(lastName);
		this.getAddress().sendKeys(address);
		this.getPhone().sendKeys(phone);
		this.getZip().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(1000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(1000);
		this.getCity().selectByVisibleText(city);
		Thread.sleep(1000);
		this.getSaveButton().submit();

	}
}
