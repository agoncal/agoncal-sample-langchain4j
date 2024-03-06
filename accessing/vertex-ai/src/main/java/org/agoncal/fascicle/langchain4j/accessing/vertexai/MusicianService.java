package org.agoncal.fascicle.langchain4j.accessing.vertexai;

import dev.langchain4j.model.vertexai.VertexAiChatModel;

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

    musicianService.useVertexAiLanguageModelBuilder();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###################################
  // ### AZURE OPENAI LANGUAGE MODEL ###
  // ###################################
  public void useVertexAiLanguageModelBuilder() {
    System.out.println("### useVertexAiLanguageModelBuilder");

    // tag::adocSnippet[]
    VertexAiChatModel model = VertexAiChatModel.builder()
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.generate(PROMPT);
  }
}
