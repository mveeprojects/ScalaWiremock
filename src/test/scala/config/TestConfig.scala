package config

import pureconfig.ConfigSource
import pureconfig.generic.auto._

import scala.concurrent.duration.Duration

object TestConfig {
  val testConf: TestConfig = ConfigSource.default.loadOrThrow[TestConfig]

  case class PatienceConfig(timeout: Duration, interval: Duration)

  case class WiremockConfig(port: Int)

  case class TestConfig(patience: PatienceConfig, wiremock: WiremockConfig)
}
