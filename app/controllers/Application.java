package controllers;

import play.*;
import play.mvc.*;
import static play.data.Form.*;
import views.html.*;
public class Application extends Controller {

	
	
	public static Result index() {
		
		return ok(index.render("時間割", 5600));
    }
	public static Result create() {
		Form<Computer> computerForm = form(Computer.class);
		return ok(createForm.render(computerForm));
	}
	

}