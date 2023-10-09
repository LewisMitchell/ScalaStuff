package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class HomeControllerV2 @Inject()(val controllerComponents: ControllerComponents,
                               environment: Environment) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    if(environment.mode == Mode.Prod) {
      throw new Exception("this is only thrown in prod mode")
    } else {
      Ok(views.html.index())
    }
  }
}
