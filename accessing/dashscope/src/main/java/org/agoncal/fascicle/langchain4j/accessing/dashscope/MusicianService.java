package org.agoncal.fascicle.langchain4j.accessing.dashscope;

import dev.langchain4j.model.dashscope.QwenChatModel;

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

    musicianService.useQwenChatModel();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useQwenChatModel() {
    System.out.println("### useAzureOpenAiChatModel");

    // tag::adocSnippet[]
    QwenChatModel model = QwenChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .temperature(0.3f)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useQwenChatModelRequest() {
    System.out.println("### useQwenChatModelRequest");

    // tag::adocRequest[]
    QwenChatModel model = QwenChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .topP(1.0d)
      .topK(34)
      .temperature(0.9f)
      .modelName("model")
      .maxTokens(100)
      .enableSearch(true)
      .seed(3)
      .repetitionPenalty(0.45f)
      .build();
    // end::adocRequest[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
