package org.agoncal.fascicle.langchain4j.firstlook;

// tag::adocSnippet[]

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class AuthorService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  private String[] scifiAuthors = {"Isaac Asimov", "Nora Jemisin", "Douglas Adams"};

  public static void main(String[] args) {
    AuthorService authorService = new AuthorService();

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .build();

    System.out.println(authorService.getAuthorBiography(model, 1));
  }
  // end::adocSnippet[]

  // tag::adocBio[]
  public String getAuthorBiography(ChatLanguageModel model, int index) {

    String prompt = "Write a short biography about " + scifiAuthors[index];

    return model.generate(prompt);
  }
}
// end::adocBio[]
