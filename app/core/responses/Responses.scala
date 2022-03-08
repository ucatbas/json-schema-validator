package core.responses

import play.api.libs.json._

/**
 * Prepares the response message.
 * @param action Action made.
 * @param id Schema Id.
 * @param status Status -> 'success'.
 */
case class SuccessfulResponse(action: String,
														  id: String,
														  status: String)

object SuccessfulResponse{
	def apply(_action: String, _id: String) = new SuccessfulResponse(_action, _id, "success")
	implicit val format : OFormat[SuccessfulResponse] = Json.format[SuccessfulResponse]
	implicit val writes : OWrites[SuccessfulResponse] = Json.writes[SuccessfulResponse]
}
