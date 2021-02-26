package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage extends BasicPage {

	public SearchResultPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public List<WebElement> searchResult() {
		List<WebElement> result = this.driver.findElements(By.xpath("//*[@class='product-name']/a"));
		return result;

	}

	public List<String> nameOfMeals() {
		List<String> meals = new ArrayList<String>();
		for (int i = 0; i < this.searchResult().size(); i++) {
			String result = this.searchResult().get(i).getText();
			meals.add(result);
		}
		return meals;
	}

	public int numberOfMeals() {
		int counter = this.searchResult().size();
		return counter;

	}
}
