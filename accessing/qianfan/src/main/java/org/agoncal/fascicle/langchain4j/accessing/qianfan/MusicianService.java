package org.agoncal.fascicle.langchain4j.accessing.qianfan;

// tag::adocSnippet[]

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

    musicianService.useAzureOpenAiChatModel();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useAzureOpenAiChatModel() {
    System.out.println("### useAzureOpenAiChatModel");

    QianfanChatModel model = QianfanChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .temperature(0.3)
      .build();

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
// end::adocSnippet[]
