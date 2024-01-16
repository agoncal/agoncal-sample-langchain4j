package org.agoncal.fascicle.langchain4j.firstlook;

// tag::adocSnippet[]
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static java.time.Duration.ofSeconds;

public class Author {

  public static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    String prompt = "Explain in three lines how to make a beautiful painting";

    String response = model.generate(prompt);

    System.out.println(response);
  }
}
// end::adocSnippet[]
