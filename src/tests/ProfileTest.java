package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProfileTest extends BasicTest {
	
	SoftAssert SoftA = new SoftAssert();

	@Test(priority = 5)
	public void editProfileTest() throws Exception {

		driver.navigate().to(baseURL + "/guest-user/login-form");

		lpp.closePopUp();

		lp.login(Email, Pass);
		String logInMessage = nsp.notificationMessage();
		SoftA.assertTrue(logInMessage.contains("Login Successfull"), "[ERROR] Login Failed!");
		
		driver.navigate().to(baseURL + "/member/profile");
		pp.updateProfile("Filip", "Dinic", "Bore Vukmirovica 32", "063415184", "18000", "United Kingdom",
				"Bristol", "Avon");
		String setMessage = nsp.notificationMessage();
		SoftA.assertTrue(setMessage.contains("Setup Successful"), "[ERROR] Profile not updated!");

		ap.logoutAccount();
		String logOutMessage = nsp.notificationMessage();
		SoftA.assertTrue(logOutMessage.contains("Logout Successfull!"), "[ERROR] Login Failed!");

	}

	@Test(priority = 10)
	public void changeProfileImageTest() throws Exception {

		driver.navigate().to(baseURL + "/guest-user/login-form");

		lpp.closePopUp();

		lp.login(Email, Pass);
		String logInMessage = nsp.notificationMessage();
		SoftA.assertTrue(logInMessage.contains("Login Successfull"), "[ERROR] Login Failed!");

		driver.navigate().to(baseURL + "/member/profile");

		pp.uploadImage();
		String imageUploaded = nsp.notificationMessage();
		SoftA.assertTrue(imageUploaded.contains("Profile Image Uploaded Successfully"), "[ERROR] Profile image not updated!");
		nsp.notificationDisappear();

		pp.deleteImage();
		String delImage = nsp.notificationMessage();
		SoftA.assertTrue(delImage.contains("Profile Image Deleted Successfully"), "[ERROR] Profile image not deleted!");
		nsp.notificationDisappear();

		ap.logoutAccount();
		String logOutMessage = nsp.notificationMessage();
		SoftA.assertTrue(logOutMessage.contains("Logout Successfull!"), "[ERROR] Logout Failed!");

	}
}
