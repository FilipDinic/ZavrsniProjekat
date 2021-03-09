package tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MealItemTest extends BasicTest {

	SoftAssert SoftA = new SoftAssert();

	@Test(priority = 5)
	public void addMealToCartTest() throws Exception {

		driver.navigate().to(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		lpp.closePopUp();

		mp.addMealToCart(5);

		String errorMessage = nsp.notificationMessage();
		SoftA.assertTrue(errorMessage.contains("The Following Errors Occurred:"), "[ERROR] Error message not exist!");
		nsp.notificationDisappear();

		lpp.selectLocation();
		lpp.setLocationName("City Center - Albany");

		mp.addMealToCart(3);

		String mealAddedToCart = nsp.notificationMessage();
		SoftA.assertTrue(mealAddedToCart.contains("Meal Added To Cart"), "[ERROR] Meal is not added to cart!");

	}

	@Test(priority = 10)
	public void addMealToFavorite() throws Exception {

		driver.navigate().to(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");

		lpp.closePopUp();

		mp.addMealFavorite();

		String pleaseLogin = nsp.notificationMessage();
		SoftA.assertTrue(pleaseLogin.contains("Please login first!"), "[ERROR] Login Failed!");

		driver.get(baseURL + "/guest-user/login-form");
		lp.login(Email, Pass);

		driver.get(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		mp.addMealFavorite();

		String productAddToFavourite = nsp.notificationMessage();
		SoftA.assertTrue(productAddToFavourite.contains("Product has been added to your favorites."),
				"[ERROR] Product is not added to favorite!");

	}

	@Test(priority = 15)
	public void clearCartTest() throws Exception {

		driver.navigate().to(baseURL + "/meals");
		SoftAssert SoftA = new SoftAssert();

		lpp.setLocationName("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			XSSFRow row = sheet.getRow(i);

			XSSFCell mealUrlSheet = row.getCell(0);
			String mealUrl = mealUrlSheet.getStringCellValue();

			driver.navigate().to(mealUrl);

			mp.addMealToCart(3);

			SoftA.assertEquals(nsp.notificationMessage(), "Meal Added To Cart",
					"[ERROR] Cart is empty!");
		}

		SoftA.assertAll();

		csp.clearAll();

		String removeAll = nsp.notificationMessage();
		SoftA.assertTrue(removeAll.contains("All meals removed from Cart successfully"),
				"[ERROR] Meals are not removed!");

		fis.close();
		wb.close();

	}
}
