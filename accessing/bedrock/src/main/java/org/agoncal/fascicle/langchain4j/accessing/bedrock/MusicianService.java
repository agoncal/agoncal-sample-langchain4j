package org.agoncal.fascicle.langchain4j.accessing.bedrock;

import dev.langchain4j.model.bedrock.BedrockAnthropicChatModel;
import software.amazon.awssdk.regions.Region;

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

  // ##############################
  // ### BEDROCK LANGUAGE MODEL ###
  // ##############################
  public void useBedrockLanguageModelBuilder() {
    System.out.println("### useBedrockLanguageModelBuilder");

    // tag::adocSnippet[]
    BedrockAnthropicChatModel model = BedrockAnthropicChatModel.builder()
      .build();
    // end::adocSnippet[]

    String completion = model.generate(PROMPT);

  }

  // ##########################
  // ### BEDROCK CHAT MODEL ###
  // ##########################
  public void useBedrockChatModelRequest() {
    System.out.println("### useBedrockChatModelRequest");

    // tag::adocRequest[]
    BedrockAnthropicChatModel model = BedrockAnthropicChatModel.builder()
      .maxRetries(3)
      .topP(1.0f)
      .temperature(0.9f)
      .model(BedrockAnthropicChatModel.Types.AnthropicClaudeV1)
      .maxTokens(100)
      .anthropicVersion("v2")
      .assistantPrompt("assistant prompt")
      .humanPrompt("human prompt")
      .region(Region.AF_SOUTH_1)
      .build();
    // end::adocRequest[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

}
