package org.agoncal.fascicle.langchain4j.accessing.qianfan;

import dev.langchain4j.model.qianfan.QianfanChatModel;

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

    musicianService.useQianfanChatModel();
  }

  private static final String QIANFAN_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String QIANFAN_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### QIANFAN CHAT MODEL ###
  // ###############################
  public void useQianfanChatModel() {
    System.out.println("### useAzureOpenAiuseQianfanChatModelChatModel");

    // tag::adocSnippet[]
    QianfanChatModel model = QianfanChatModel.builder()
      .apiKey(QIANFAN_KEY)
      .endpoint(QIANFAN_ENDPOINT)
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useQianfanChatModelRequest() {
    System.out.println("### useQianfanChatModelRequest");

    // tag::adocRequest[]
    QianfanChatModel model = QianfanChatModel.builder()
      .apiKey(QIANFAN_KEY)
      .endpoint(QIANFAN_ENDPOINT)
      .modelName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .baseUrl("https://api.qianfanapi.com")
      .penaltyScore(0.0d)
      .secretKey("secretKey")
      .maxRetries(3)
      .topP(1.0d)
      .temperature(0.9)
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocRequest[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
