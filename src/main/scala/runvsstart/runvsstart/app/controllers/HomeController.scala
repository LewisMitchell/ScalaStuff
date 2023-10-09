package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents,
                              environment: Environment) extends BaseController {
  def index() = Action { implicit request: Request[AnyContent] =>
    if(environment.mode == Mode.Dev) {
      Ok(views.html.index(Some("dev")))
    } else if(environment.mode == Mode.Test) {
      Ok(views.html.index(Some("test")))
    } else if(environment.mode == Mode.Prod) {
      Ok(views.html.index(Some("production")))
    } else {
      throw new Exception("invalid mode")
    }
  }
}
