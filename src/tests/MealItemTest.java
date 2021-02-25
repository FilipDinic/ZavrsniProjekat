package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	@Test(priority = 5)
	public void addMealToCartTest() throws Exception {

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopupPage.closePopUp();

		mealPage.addMealToCart(5);

		String errorMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(errorMessage, "The Following Errors Occurred:" + "\n" + "Please Select Location",
				"[ERROR] Error message not exist!");
		notificationSistemPage.notificationDisappear();

		locationPopupPage.selectLocation();
		locationPopupPage.setLocationName("City Center - Albany");

		mealPage.addMealToCart(4);

		String mealAddedToCart = notificationSistemPage.notificationMessage();
		Assert.assertEquals(mealAddedToCart, "Meal Added To Cart", "[ERROR] Meal is not added to cart!");

	}

	@Test(priority = 10)
	public void addMealToFavorite() throws Exception {

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		locationPopupPage.closePopUp();

		mealPage.addMealFavorite();

		String pleaseLogin = notificationSistemPage.notificationMessage();
		Assert.assertEquals(pleaseLogin, "Please login first!", "[ERROR] Login Failed!");

		this.driver.get(baseURL + "/guest-user/login-form");
		loginPage.login(Email, Pass);

		this.driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mealPage.addMealFavorite();

		String productAddToFavourite = notificationSistemPage.notificationMessage();
		Assert.assertEquals(productAddToFavourite, "Product has been added to your favorites.",
				"[ERROR] Product is not added to favorite!");

	}

	@Test(priority = 15)
	public void clearCartTest() throws Exception {

		this.driver.get(baseURL + "/meals");
		SoftAssert SoftA = new SoftAssert();

		locationPopupPage.setLocationName("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);

			XSSFCell mealUrlSheet = row.getCell(0);
			String mealUrl = mealUrlSheet.getStringCellValue();

			this.driver.get(mealUrl);

			mealPage.addMealToCart(3);

			SoftA.assertEquals(notificationSistemPage.notificationMessage(), "Meal Added To Cart",
					"[ERROR] Cart is empty!");
		}

		SoftA.assertAll();

		cartSummaryPage.clearAll();

		String removeAll = notificationSistemPage.notificationMessage();
		Assert.assertEquals(removeAll, "All meals removed from Cart successfully", "[ERROR] Meals are not removed!");

		fis.close();
		wb.close();

	}
}
