package controllers;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import models.AppUser;

import org.apache.commons.codec.binary.Base64;

import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Constants;
import bean.LoginBean;

public class Application extends Controller {

	public Result index() {

		return ok(views.html.index.render());
	}

	public static final Form<LoginBean> loginForm = Form.form(LoginBean.class);

	public Result userRegister() {

		AppUser appUser = new AppUser();

		appUser.firstName = "Ramesh";
		appUser.lastName = "Kolla";
		appUser.middleName = "Reddy";
		appUser.email = "thrymr@gmail.com";
		appUser.password = "123";

		try {
			final Random random = new SecureRandom();
			final byte[] saltArray = new byte[32];
			final String randomSalt = Base64.encodeBase64String(saltArray);
			final String passwordWithSalt = appUser.password + randomSalt;
			final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			final byte[] passBytes = passwordWithSalt.getBytes();
			final String hashedPasswordWithSalt = Base64
					.encodeBase64String(sha256.digest(passBytes));
			appUser.password = hashedPasswordWithSalt;
			appUser.salt = randomSalt;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		appUser.save();

		return ok("save success");

	}

	public Result loginPage() {
		// final String message = null;
		int match = 0;
		session().remove(Constants.LOGGED_IN_USER_ID);

		final Form<LoginBean> loginFilledForm = loginForm.bindFromRequest();
		Logger.debug("form data" + loginFilledForm.toString());
		final LoginBean loginBean = loginFilledForm.get();
		if (loginFilledForm.hasErrors()) {
			return redirect(routes.Application.index());

		} else {

			try {
				Logger.debug("Login Bean:" + loginBean);
				final List<AppUser> emailList = AppUser.find.where()
						.eq("email", loginBean.userName.trim().toLowerCase())
						.findList();
				Logger.debug("email list" + emailList.size());

				if (emailList.size() == 1) {
					if (emailList.get(0).matchPassword(
							loginBean.password.trim())) {
						session(Constants.LOGGED_IN_USER_ID,
								emailList.get(0).id + "");

						/*
						 * return redirect(routes.SampleController
						 * .getnewsDetails());
						 */

						return ok(views.html.login_page.render());

					} else {

						Logger.error("password not matched");
						/*
						 * return redirect(routes.SampleController
						 * .getLoginFormPage());
						 */

						return ok(views.html.index.render());

					}
				}

			} catch (final NullPointerException e) {
				e.printStackTrace();
			}
		}
		return TODO;
	}
	
	
	
	

}
