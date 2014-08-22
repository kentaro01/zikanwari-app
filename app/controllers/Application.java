package controllers;

import static play.data.Form.form;

import java.util.List;

import models.SampleData;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createForm;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		List<SampleData> data = SampleData.find.all();
		return ok(index.render("時間割", 5600, data));
	}

	public static Result create() {
		Form<SampleData> dataform = form(SampleData.class);
		return ok(createForm.render("時間割", dataform));
	}

	public static Result save() {
		Form<SampleData> dataform = form(SampleData.class).bindFromRequest();
		if (dataform.hasErrors()) {
			return badRequest(createForm.render("時間割", dataform));
		}
		dataform.get().save();
		flash("success");
		return home;
	}

	public static Result home = redirect(routes.Application.index());

}
