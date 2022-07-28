import akka.http.scaladsl.model.HttpResponse
import base.MySpecBase
import org.scalatest.concurrent.ScalaFutures.convertScalaFuture
import utils.HttpUtils._

class MySpec extends MySpecBase {

  "the checkstock/abc endpoint should return a 200" in {
//    Thread.sleep(10000) // adding a sleep so you can see the stub mappings are temporarily provisioned on port 8081.
    eventually {
      val getRequestResponse: HttpResponse = fireGetRequest(checkStockAbcEndpoint).futureValue
      getRequestResponse.status.intValue shouldBe 200
      unmarshalledResponseEntity(getRequestResponse.entity) shouldBe "Product is in stock"
    }
  }

  "the checkstock/123 endpoint should return a 404" in {
    eventually {
      val getRequestResponse: HttpResponse = fireGetRequest(checkStock123Endpoint).futureValue
      getRequestResponse.status.intValue shouldBe 404
      unmarshalledResponseEntity(getRequestResponse.entity) shouldBe "Product is not in stock"
    }
  }
}
