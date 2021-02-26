package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {
	
	SoftAssert SoftA = new SoftAssert();

	@Test(priority = 5)
	public void editProfileTest() throws Exception {

		driver.navigate().to(baseURL + "/guest-user/login-form");

		locationPopupPage.closePopUp();

		loginPage.login(Email, Pass);
		String logInMessage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(logInMessage.contains("Login Successfull"), "[ERROR] Login Failed!");
		
		driver.navigate().to(baseURL + "/member/profile");
		profilePage.updateProfile("Filip", "Dinic", "Bore Vukmirovica 32", "063415184", "18000", "United Kingdom",
				"Bristol", "Avon");
		String setMessage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(setMessage.contains("Setup Successful"), "[ERROR] Profile not updated!");

		authPage.logoutAccount();
		String logOutMessage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(logOutMessage.contains("Logout Successfull!"), "[ERROR] Login Failed!");

	}

	@Test(priority = 10)
	public void changeProfileImageTest() throws Exception {

		driver.navigate().to(baseURL + "/guest-user/login-form");

		locationPopupPage.closePopUp();

		loginPage.login(Email, Pass);
		String logInMessage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(logInMessage.contains("Login Successfull"), "[ERROR] Login Failed!");

		driver.navigate().to(baseURL + "/member/profile");

		profilePage.uploadImage();
		String imageUploaded = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(imageUploaded.contains("Profile Image Uploaded Successfully"), "[ERROR] Profile image not updated!");
		notificationSistemPage.notificationDisappear();

		profilePage.deleteImage();
		String delImage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(delImage.contains("Profile Image Deleted Successfully"), "[ERROR] Profile image not deleted!");
		notificationSistemPage.notificationDisappear();

		authPage.logoutAccount();
		String logOutMessage = notificationSistemPage.notificationMessage();
		SoftA.assertTrue(logOutMessage.contains("Logout Successfull!"), "[ERROR] Logout Failed!");

	}
}
