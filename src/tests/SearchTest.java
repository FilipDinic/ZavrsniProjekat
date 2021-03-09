package tests;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	SoftAssert SoftA = new SoftAssert();

	@Test(priority = 5)
	public void searchResultsTest() throws Exception {

		driver.navigate().to(baseURL + "/meals");

		lpp.setLocationName("City Center - Albany");

		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");

		for (int i = 1; i < sheet.getLastRowNum(); i++) {

			XSSFRow row = sheet.getRow(i);

			lpp.selectLocation();

			String Location = row.getCell(0).getStringCellValue();

			lpp.setLocationName(Location);

			String urlFromSheet = row.getCell(1).getStringCellValue();
			driver.navigate().to(urlFromSheet);

			int resultCount = (int) row.getCell(2).getNumericCellValue();

			SoftA.assertEquals(srp.numberOfMeals(), resultCount,
					"[ERROR] Number of results is not the same!");

			for (int j = 3; j < 3 + row.getCell(2).getNumericCellValue(); j++) {

				String result = row.getCell(j).getStringCellValue();

				SoftA.assertTrue((srp.nameOfMeals().get(j - 3)).contains(result),
						"[ERROR] The order of the results is not the same!");

			}

		}

		SoftA.assertAll();
		fis.close();
		wb.close();

	}
}