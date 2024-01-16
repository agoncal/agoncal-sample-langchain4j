package org.agoncal.fascicle.langchain4j.firstlook;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import static jakarta.ws.rs.core.HttpHeaders.ACCEPT;
import static jakarta.ws.rs.core.MediaType.TEXT_PLAIN;

@QuarkusTest
class AuthorResourceTest {
  // tag::adocSnippet[]
  @Test
  public void shouldGetAnAuthor() {
    given()
      .header(ACCEPT, TEXT_PLAIN)
      .pathParam("index", 0).
      when()
      .get("/authors/{index}").
      then()
      .assertThat()
      .statusCode(is(200))
      .and()
      .body(is("Isaac Asimov"));
  }
  // end::adocSnippet[]
}
