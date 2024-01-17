package org.agoncal.fascicle.langchain4j.firstlook;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import static java.time.Duration.ofSeconds;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 * curl http://localhost:8080/authors/0
 * curl http://localhost:8080/authors/1
 */
// end::adocSkip[]
@Path("/authors")
@Produces(MediaType.TEXT_PLAIN)
public class AuthorResource {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

//  private String[] scifiAuthors = {"Isaac Asimov", "Nora Jemisin", "Douglas Adams"};

  @GET
  public String getIsaacAsimovBiography() {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    String prompt = "Write a short biography about Isaac Asimov";

    String biography = model.generate(prompt);

    return biography;
  }

//  @GET
//  @Path("/{index}")
//  public String getAuthorBiography(@PathParam("index") int index) {
//
//    ChatLanguageModel model = OpenAiChatModel.builder()
//      .apiKey(OPENAI_API_KEY)
//      .modelName(GPT_3_5_TURBO)
//      .temperature(0.3)
//      .timeout(ofSeconds(60))
//      .logRequests(true)
//      .logResponses(true)
//      .build();
//
//    String prompt = "Write a short biography about " + scifiAuthors[index];
//
//    String biography = model.generate(prompt);
//
//    return biography;
//  }
}
// end::adocSnippet[]
