package org.agoncal.fascicle.langchain4j.gettingstarted;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import static java.time.Duration.ofSeconds;

// tag::adocSnippet[]
public class Artist {

  private String firstName;
  private String lastName;
  private String bio;

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {
    Artist artist = new Artist("Nora", "Jemisin");

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    System.out.println(artist.generateBio(model));
  }

  public String generateBio(ChatLanguageModel model) {

    String prompt = String.format("Write a one sentence biography about %s %s", firstName, lastName);

    this.bio = model.generate(prompt);
    return bio;
  }

  // Constructors, getters, setters

  // tag::adocSkip[]

  public Artist(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

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
