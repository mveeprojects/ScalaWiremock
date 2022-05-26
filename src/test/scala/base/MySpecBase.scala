package base

import config.TestConfig.testConf.patience
import org.scalatest.concurrent.Eventually
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import utils.WiremockUtils.{clearAllStubMappings, createStubMapping}

trait MySpecBase extends AnyFreeSpec with Matchers with Eventually with BeforeAndAfterEach with BeforeAndAfterAll {

  override implicit val patienceConfig: PatienceConfig =
    PatienceConfig(patience.timeout, patience.interval)

  val endpointOne = "/checkstock/abc"
  val endpointTwo = "/checkstock/123"

  override def beforeAll(): Unit = {
    createStubMapping(endpointOne, 200, "Product is in stock")
    createStubMapping(endpointTwo, 404, "Product is not in stock")
  }

  override def afterAll(): Unit = clearAllStubMappings()
}
