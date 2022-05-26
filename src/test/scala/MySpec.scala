import akka.http.scaladsl.model.{HttpResponse, ResponseEntity, StatusCode}
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import utils.HttpUtils._
import utils.WiremockUtils._
import akka.http.scaladsl.unmarshalling.Unmarshal

import scala.concurrent.Future

class MySpec extends MySpecBase {

  private val endpointOne = "/checkstock/abc"
  private val endpointTwo = "/checkstock/123"

  override def beforeAll(): Unit = {
    stubResponse(endpointOne, 200, "Product is in stock")
    stubResponse(endpointTwo, 404, "Product is not in stock")
  }

  "the checkstock/abc endpoint should return a 200" in {
    eventually {
      val getRequestResponse: HttpResponse = fireGetRequest(endpointOne).futureValue
      getRequestResponse.status.intValue shouldBe 200
      unmarshalledResponseEntity(getRequestResponse.entity) shouldBe "Product is in stock"
    }
  }

  "the checkstock/123 endpoint should return a 404" in {
    eventually {
      val getRequestResponse: HttpResponse = fireGetRequest(endpointTwo).futureValue
      getRequestResponse.status.intValue shouldBe 404
      unmarshalledResponseEntity(getRequestResponse.entity) shouldBe "Product is not in stock"
    }
  }
}
