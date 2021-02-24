package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public WebElement getFavoriteButton() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'itemfav link')]"));

	}

	public WebElement getQuantityInput() {
		return this.driver
				.findElement(By.xpath("//*[@id='body']/section[1]/div/div/div[2]/div/div[3]/div[1]/ul/li[3]/input"));

	}

	public WebElement getAddToCart() {
		return this.driver.findElement(By.xpath("//*[contains(@class, 'js-proceedtoAddInCart')]"));

	}

	public void addMealToCart(int quan) {
		String quantity = String.valueOf(quan);
		this.getQuantityInput().sendKeys(Keys.CONTROL, "a");
		this.getQuantityInput().sendKeys(quantity);
		this.getAddToCart().click();

	}

	public void addMealFavorite() {
		this.getFavoriteButton().click();

	}
}
