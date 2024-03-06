package org.agoncal.fascicle.langchain4j.accessing.bedrock;

// tag::adocSnippet[]

import dev.langchain4j.model.bedrock.BedrockAnthropicChatModel;

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

    musicianService.useBedrockLanguageModelBuilder();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###################################
  // ### AZURE OPENAI LANGUAGE MODEL ###
  // ###################################
  public void useBedrockLanguageModelBuilder() {
    System.out.println("### useBedrockLanguageModelBuilder");

    BedrockAnthropicChatModel model = BedrockAnthropicChatModel.builder()
      .build();

    String completion = model.generate(PROMPT);

  }
}
// end::adocSnippet[]
