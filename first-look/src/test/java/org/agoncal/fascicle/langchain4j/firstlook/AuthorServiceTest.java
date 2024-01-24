package org.agoncal.fascicle.langchain4j.firstlook;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

@WireMockTest(httpPort = 8089)
class AuthorServiceTest {

  static WireMockServer wireMockServer;

//  @BeforeAll
//  static void beforeAll() {
//    wireMockServer = new WireMockServer(options().port(8089));
//    wireMockServer.start();
//  }
//
//  @AfterAll
//  static void afterAll() {
//    wireMockServer.stop();
//  }
//
//  @BeforeEach
//  void setup() {
//    wireMockServer.resetAll();
//  }

  @Test
  public void shouldGetIsaacAsimovBiography(WireMockRuntimeInfo wmRuntimeInfo) {
    stubFor(post("https://api.openai.com/v1/chat/completions").willReturn(ok()));

    String url = wmRuntimeInfo.getHttpBaseUrl();
    System.out.println("url = " + url);
    WireMock wireMock = wmRuntimeInfo.getWireMock();
    wireMock.startStubRecording("https://api.openai.com/v1/chat/completions");


    AuthorService authorService = new AuthorService();
    String biography = authorService.getIsaacAsimovBiography();
    assertTrue(biography.contains("Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
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
