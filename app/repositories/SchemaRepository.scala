package repositories

import models.Schema
import core.exceptions.ErrorMessages._
import core.exceptions.BaseException
import play.api.Configuration
import slick.jdbc.JdbcProfile
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SchemaRepository @Inject()(implicit executionContext: ExecutionContext,
																 protected val dbConfigProvider: DatabaseConfigProvider,
																 configuration: Configuration) extends HasDatabaseConfigProvider[JdbcProfile] {
	import dbConfig.profile.api._

	class SchemaTable(_tag: Tag) extends Table[Schema](_tag, "schema"){
		def id: Rep[String] = column[String]("id", O.PrimaryKey)
		def json: Rep[String] = column[String]("json")
		def created: Rep[Long] = column[Long]("created")

		def * = (id, json, created)<>((Schema.apply _).tupled, Schema.unapply)
	}

	private val schemaTable = TableQuery[SchemaTable]


	def findOne(id: String) = {
		db.run(schemaTable.filter(_.id === id ).result.headOption).flatMap{
			case Some(value) => Future.successful(value.copy())
			case _ 					 => Future.successful(BaseException(" ", id,schemaNotFound(id)))
		}
	}

	def create(schema: Schema) = {
		db.run(schemaTable ++= Seq(schema)).flatMap{
			case Some(value) => Future.successful(schema)
			case _  				 => Future.successful(BaseException(" ", schema.id ,unknownFailure))
		}
	}
}

