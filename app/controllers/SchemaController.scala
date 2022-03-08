package controllers

import core.exceptions.BaseException
import core.exceptions.ErrorMessages._
import core.responses._
import models.Schema
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import repositories.SchemaRepository
import services.SchemaService

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SchemaController @Inject()(schemaService: SchemaService,
																 schemaRepository: SchemaRepository)(implicit executionContext: ExecutionContext)  extends InjectedController {

	def create(id: String): Action[JsValue] = Action.async(parse.tolerantJson) { req =>
		schemaRepository.findOne(id) map {
			case schema: Schema => Forbidden(Json.toJson(BaseException("uploadSchema", id, schemaIdExists(id))))
			case _ 							=> schemaService.create(id, req.body.toString()) match {
				case schema: Future[Schema] => Created(Json.toJson(SuccessfulResponse("uploadSchema", id)))
				case _ 					  					=> BadRequest(Json.toJson(BaseException("uploadSchema", id, invalidRequestBody)))
			}
		}
	}

	def download(id: String): Action[AnyContent] = Action.async { _ =>
		schemaRepository.findOne(id) map {
			case schema: Schema => Ok(Json.parse(schema.json))
			case _ 							=> NotFound(Json.toJson(BaseException("downloadDocument", id, schemaNotFound(id))))
		}
	}

	def validate(id: String): Action[JsValue] = Action.async(parse.json) { req =>
		schemaRepository.findOne(id) map {
			case schema: Schema => schemaService.validate(schema, req.body.toString()) match {
				case schema: Future[Schema] => Ok(Json.toJson(SuccessfulResponse("validateDocument", id)))
				case _ 											=> BadRequest(Json.toJson(BaseException("validateDocument", id, invalidRequestBody)))
			}
			case _ 	 						=> NotFound(Json.toJson(BaseException("validateDocument", id, schemaNotFound(id))))
		}
	}
}

