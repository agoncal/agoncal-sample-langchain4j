package org.agoncal.fascicle.langchain4j.accessing.localai;

// tag::adocSnippet[]

import dev.langchain4j.model.localai.LocalAiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianService {

  public static void main(String[] args) {
    MusicianService musicianService = new MusicianService();

    musicianService.useLocalAiChatModel();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useLocalAiChatModel() {
    System.out.println("### useLocalAiChatModel");

    LocalAiChatModel model = LocalAiChatModel.builder()
      .temperature(0.3)
      .build();

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
// end::adocSnippet[]
