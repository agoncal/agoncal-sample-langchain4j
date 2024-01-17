package org.agoncal.fascicle.langchain4j.firstlook;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AuthorResourceTest {

  private static final int WIREMOCK_PORT = 8089;
  static WireMockServer wireMockServer;

  @BeforeAll
  static void beforeAll() {
    wireMockServer = new WireMockServer(options().port(WIREMOCK_PORT));
    wireMockServer.start();
  }

  @AfterAll
  static void afterAll() {
    wireMockServer.stop();
  }

  @BeforeEach
  void setup() {
    wireMockServer.resetAll();
    wireMockServer.stubFor(WiremockUtils.defaultBiographyStub());
  }

  @Test
  public void shouldGetIsaacAsimovBiography() {
    given()
      .header(ACCEPT, TEXT_PLAIN).
    when()
      .get("/authors").
    then()
      .assertThat()
      .statusCode(is(200))
      .and()
      .body(is("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
  }
//
//  @Test
//  public void shouldGetAnAuthor() {
//    given()
//      .header(ACCEPT, TEXT_PLAIN)
//      .pathParam("index", 0).
//      when()
//      .get("/authors/{index}").
//      then()
//      .assertThat()
//      .statusCode(is(200))
//      .and()
//      .body(is("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
//  }
}
