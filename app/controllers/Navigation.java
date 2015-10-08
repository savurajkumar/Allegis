package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Navigation extends Controller {

	public Result clientManagement() {
		return ok(views.html.clientmanagement.render());

	}

	public Result userManagement() {
		return ok(views.html.login_page.render());

	}

	public Result signUp() {
		return ok(views.html.userManagment.render());

	}

	public Result hiringManagement() {
		return ok(views.html.hiringmanager.render());

	}

	public Result roles() {
		return ok(views.html.roleDesription.render());

	}

	public Result responsibilities() {
		return ok(views.html.responsibilities.render());

	}

	public Result skills() {
		return ok(views.html.skills.render());

	}

	public Result domain() {
		return ok(views.html.domain.render());

	}

	public Result education() {
		return ok(views.html.education.render());

	}

	public Result certification() {
		return ok(views.html.certifications.render());

	}

}
