package org.agoncal.fascicle.langchain4j.gettingstarted;

import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import dev.langchain4j.model.output.Response;
import static java.time.Duration.ofSeconds;

// tag::adocSnippet[]
public class Artist {

  private String firstName;
  private String lastName;
  private String bio;

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // Constructors, getters, setters

  public String generateBio() {
    LanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    String prompt = String.format("Write a short biography about {} {}", firstName, lastName);

    Response<String> biography = model.generate(prompt);

    this.bio = biography.content();
    return bio;
  }

  // tag::adocSkip[]
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }
  // end::adocSkip[]
}
// end::adocSnippet[]
