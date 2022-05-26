import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach}
import org.scalatest.concurrent.Eventually
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import config.TestConfig.testConf._

trait MySpecBase extends AnyFreeSpec with Matchers with Eventually with BeforeAndAfterEach with BeforeAndAfterAll {
  override implicit val patienceConfig: PatienceConfig =
    PatienceConfig(patience.timeout, patience.interval)
}
