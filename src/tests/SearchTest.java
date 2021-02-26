package tests;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	SoftAssert SoftA = new SoftAssert();

	@Test(priority = 5)
	public void searchResultsTest() throws Exception {

		driver.navigate().to(baseURL + "/meals");

		locationPopupPage.setLocationName("City Center - Albany");
		Thread.sleep(2000);

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i <= 6; i++) {
			Thread.sleep(2000);
			XSSFRow row = sheet.getRow(i);

			locationPopupPage.selectLocation();
			Thread.sleep(2000);
			String Location = row.getCell(0).getStringCellValue();
			Thread.sleep(2000);
			locationPopupPage.setLocationName(Location);

			String urlFromSheet = row.getCell(1).getStringCellValue();
			driver.navigate().to(urlFromSheet);

			int resultCount = (int) row.getCell(2).getNumericCellValue();
			Assert.assertEquals(searchResultPage.numberOfMeals(), resultCount,
					"[ERROR] Number of results is not the same!");
			Thread.sleep(2000);

			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {
				String realResult = searchResultPage.nameOfMeals().get(j - 3);
				String result = row.getCell(j).getStringCellValue();
				Assert.assertTrue(realResult.contains(result), "[ERROR] The order of the results is not the same!");
				Thread.sleep(2000);
			}

		}

		SoftA.assertAll();
		fis.close();
		wb.close();

	}
}