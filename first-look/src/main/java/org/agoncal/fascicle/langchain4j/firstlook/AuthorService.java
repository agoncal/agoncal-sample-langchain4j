package org.agoncal.fascicle.langchain4j.firstlook;

// tag::adocSnippet[]

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static java.time.Duration.ofSeconds;

import java.net.Proxy;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class AuthorService {

  public static void main(String[] args) {
    AuthorService authorService = new AuthorService();
    System.out.println(authorService.getIsaacAsimovBiography());
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

//  private String[] scifiAuthors = {"Isaac Asimov", "Nora Jemisin", "Douglas Adams"};

  public String getIsaacAsimovBiography() {

    ChatLanguageModel model = OpenAiChatModel.builder()
      //.baseUrl("http://localhost:8089")
      .proxy(new Proxy(Proxy.Type.HTTP, new java.net.InetSocketAddress("localhost", 8089)))
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
