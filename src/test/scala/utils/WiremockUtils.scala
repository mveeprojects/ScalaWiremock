package utils

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.{aResponse, stubFor, urlEqualTo}
import com.github.tomakehurst.wiremock.stubbing.StubMapping

object WiremockUtils {

  WireMock.configureFor(8081)

  def stubResponse(endpoint: String, responseStatus: Int, responseBody: String): StubMapping =
    stubFor(
      WireMock
        .get(urlEqualTo(endpoint))
        .willReturn(
          aResponse()
            .withStatus(responseStatus)
            .withBody(responseBody)
        )
    )
}
