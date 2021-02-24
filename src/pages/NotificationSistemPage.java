package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSistemPage extends BasicPage {

	public NotificationSistemPage(WebDriver driver, WebDriverWait waiter, JavascriptExecutor js) {
		super(driver, waiter, js);

	}

	public WebElement getAlert() {
		return this.driver.findElement(By.xpath(
				"//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String notificationMessage() {
		String message = getAlert().getText();
		return message;
	}

	public void notificationDisappear() {
		waiter.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[contains(@class, 'system_message')][contains(@style, 'display: none;')]")));

	}
}
