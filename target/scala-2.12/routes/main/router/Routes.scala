// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  SchemaController_0: controllers.SchemaController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    SchemaController_0: controllers.SchemaController
  ) = this(errorHandler, SchemaController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, SchemaController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """schema/""" + "$" + """id<[^/]+>""", """controllers.SchemaController.create(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """schema/""" + "$" + """id<[^/]+>""", """controllers.SchemaController.download(id:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """validate/""" + "$" + """id<[^/]+>""", """controllers.SchemaController.validate(id:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_SchemaController_create0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("schema/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SchemaController_create0_invoker = createInvoker(
    SchemaController_0.create(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SchemaController",
      "create",
      Seq(classOf[String]),
      "POST",
      this.prefix + """schema/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_SchemaController_download1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("schema/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SchemaController_download1_invoker = createInvoker(
    SchemaController_0.download(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SchemaController",
      "download",
      Seq(classOf[String]),
      "GET",
      this.prefix + """schema/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_SchemaController_validate2_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("validate/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_SchemaController_validate2_invoker = createInvoker(
    SchemaController_0.validate(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.SchemaController",
      "validate",
      Seq(classOf[String]),
      "POST",
      this.prefix + """validate/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_SchemaController_create0_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_SchemaController_create0_invoker.call(SchemaController_0.create(id))
      }
  
    // @LINE:7
    case controllers_SchemaController_download1_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_SchemaController_download1_invoker.call(SchemaController_0.download(id))
      }
  
    // @LINE:8
    case controllers_SchemaController_validate2_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_SchemaController_validate2_invoker.call(SchemaController_0.validate(id))
      }
  }
}
