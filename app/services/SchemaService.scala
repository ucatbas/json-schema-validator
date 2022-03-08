package services

import com.github.fge.jackson.JsonLoader
import com.github.fge.jsonschema.main.JsonSchemaFactory
import com.google.gson.{Gson, GsonBuilder, JsonObject}
import core.exceptions.BaseException
import core.exceptions.ErrorMessages.invalidRequestBody
import models.Schema
import play.api.libs.json.Json
import play.api.mvc.Results._
import repositories.SchemaRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SchemaService @Inject()(implicit val ec: ExecutionContext,
                              schemaRepository: SchemaRepository) {

  val factory = JsonSchemaFactory.byDefault()

  def clear(str: String) = {
    val jsonObject = new Gson().fromJson(str, classOf[JsonObject])
    var s = jsonObject.toString
    val gson = new GsonBuilder().create
    gson.toJson(jsonObject)
  }

  def create(id: String, json: String) = {
    val now = System.currentTimeMillis()
    val schema = Schema(id, json, now)
    for {
        schemaRes <- schemaRepository.create(schema)
      } yield schemaRes
  }

  def validate(schema: Schema, node: String) = {
    val ns = clear(node)
    val schemaNode = JsonLoader.fromString(schema.json)
    val schemaJson = factory.getJsonSchema(schemaNode)
    val jsonNode = JsonLoader.fromString(ns)
    val report = schemaJson.validate(jsonNode)
    if(report.isSuccess) Future.successful(schema)
    else BadRequest(Json.toJson(BaseException(schema.id, "validateDocument", invalidRequestBody)))
  }
}
