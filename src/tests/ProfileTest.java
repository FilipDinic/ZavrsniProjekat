package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test(priority = 5)
	public void editProfileTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		locationPopupPage.closePopUp();

		loginPage.login(Email, Pass);
		String logInMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(logInMessage, "Login Successfull", "[ERROR] Login Failed!");

		this.driver.get(baseURL + "/member/profile");
		profilePage.updateProfile("Filip", "Dinic", "Bore Vukmirovica 32", "063415184", "18000", "United Kingdom",
				"Bristol", "Avon");
		String setMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(setMessage, "Setup Successful", "[ERROR] Profile not updated!");

		authPage.logoutAccount();
		String logOutMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(logOutMessage, "Logout Successfull!", "[ERROR] Login Failed!");

	}

	@Test(priority = 10)
	public void changeProfileImageTest() throws Exception {

		this.driver.get(baseURL + "/guest-user/login-form");

		locationPopupPage.closePopUp();

		loginPage.login(Email, Pass);
		String logInMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(logInMessage, "Login Successfull", "[ERROR] Login Failed!");

		this.driver.get(baseURL + "/member/profile");

		profilePage.uploadImage();
		String imageUploaded = notificationSistemPage.notificationMessage();
		Assert.assertEquals(imageUploaded, "Profile Image Uploaded Successfully", "[ERROR] Profile image not updated!");
		notificationSistemPage.notificationDisappear();

		profilePage.deleteImage();
		String delImage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(delImage, "Profile Image Deleted Successfully", "[ERROR] Profile image not deleted!");
		notificationSistemPage.notificationDisappear();

		authPage.logoutAccount();
		String logOutMessage = notificationSistemPage.notificationMessage();
		Assert.assertEquals(logOutMessage, "Logout Successfull!", "[ERROR] Logout Failed!");

	}
}
