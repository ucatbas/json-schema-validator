package core.exceptions

import play.api.libs.json.{Json, OFormat, OWrites}

case class BaseException(action: String,
                         id: String,
                         status: String,
                         message: String)

object BaseException{
  def apply(_id: String, _action: String, _message: String) = new BaseException(_id, _action, "error", _message)
  implicit val format : OFormat[BaseException] = Json.format[BaseException]
  implicit val writes : OWrites[BaseException] = Json.writes[BaseException]
}

object ErrorMessages {
  def invalidRequestBody                      = s"Invalid request body"
  def schemaNotFound(id: String)              = s"Schema for id `$id` was not found"
  def schemaIdExists(id: String)              = s"Schema with id `$id` already exists"
  def unknownFailure                          = "An unknown error occurred. If this persists please contact support"
}