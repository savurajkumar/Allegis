package bean;

import java.io.Serializable;

import models.AppUser;

public class LoginBean implements Serializable {

	public String userName;
	public String password;

	public AppUser toAppUser() {

		AppUser appUser = new AppUser();

		appUser.email = userName;
		appUser.password = password;

		return appUser;

	}

	@Override
	public String toString() {
		return "LoginBean [userName=" + userName + ", password=" + password
				+ "]";
	}

}