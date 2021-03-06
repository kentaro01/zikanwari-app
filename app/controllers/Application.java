package controllers;

import static play.data.Form.form;

import java.util.List;
import java.util.Map;

import models.SampleData;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.createForm;
import views.html.editForm;
import views.html.index;

public class Application extends Controller {

	public static Result index() {
		List<SampleData> data = SampleData.find.all();
		return ok(index.render("時間割", 5600, data));
	}

	public static Result create (Long weeks,Long time) {
		Form<SampleData> dataform = form(SampleData.class);
		
		return ok(createForm.render("時間割", dataform, weeks ,time));
	}

	public static Result save() {
		Form<SampleData> dataform = form(SampleData.class).bindFromRequest();
		if (dataform.hasErrors()) {
			
		}
		Map<String, String> map = dataform.data();
		SampleData data = dataform.get();
		data.lesson = map.get("lesson");
		data.teacher = map.get("teacher");
		Long weeks = Long.parseLong(map.get("weeks"));
		Long time = Long.parseLong(map.get("time"));
		data.weeks = weeks;
		data.time = time ;
		data.save();
		flash("success");
		return home;
	}

	public static Result edit(Long id) {
		Form<SampleData> dataForm = form(SampleData.class).fill(
				SampleData.find.byId(id));
		return ok(editForm.render(id, dataForm));
	}

	public static Result update(Long id) {
		Form<SampleData> dataForm = form(SampleData.class).bindFromRequest();
		if (dataForm.hasErrors()) {
			return badRequest(editForm.render(id, dataForm));
		}
		dataForm.get().update(id);
		flash("success");
		return home;
	}

	public static Result delete(Long id) {
		SampleData.find.ref(id).delete();
		flash("complete delete");
		return home;
	}

	public static Result home = redirect(routes.Application.index());

}
