package utils

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HttpUtils {

  implicit val actorSystem: ActorSystem = ActorSystem()

  private val baseUrlAndPort    = "http://localhost:8081"

  def fireGetRequest(endpoint: String): Future[HttpResponse] =
    Http()
      .singleRequest(HttpRequest(HttpMethods.GET, s"$baseUrlAndPort$endpoint"))

  def unmarshalledResponseEntity(entity: ResponseEntity): String = {
    Unmarshal(entity).to[String].futureValue
  }
}
