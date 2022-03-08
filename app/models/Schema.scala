package models

import play.api.libs.json.{Json, OFormat, OWrites, Reads}

case class Schema( id: String,
									 json: String,
									 created: Long)
object Schema{

	implicit val schemaFormat : OFormat[Schema] = Json.format[Schema]
	implicit val schemaWrites : OWrites[Schema] = Json.writes[Schema]
	implicit val schemaReads : Reads[Schema] = Json.reads[Schema]

}


