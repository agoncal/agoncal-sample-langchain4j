package org.agoncal.fascicle.langchain4j.accessing.chatglm;

import dev.langchain4j.model.chatglm.ChatGlmChatModel;

import java.time.Duration;

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

    musicianService.useChatGLMChatModel();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###############################
  // ### AZURE OPENAI CHAT MODEL ###
  // ###############################
  public void useChatGLMChatModel() {
    System.out.println("### useChatGLMChatModel");

    // tag::adocSnippet[]
    ChatGlmChatModel model = ChatGlmChatModel.builder()
      .temperature(0.3)
      .build();
    // end::adocSnippet[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useChatGLMChatModelRequest() {
    System.out.println("### useChatGLMChatModelRequest");

    // tag::adocRequest[]
    ChatGlmChatModel model = ChatGlmChatModel.builder()
      .maxRetries(3)
      .topP(1.0d)
      .temperature(0.9d)
      .timeout(Duration.ofSeconds(10))
      .topP(0.3d)
      .maxLength(100)
      .build();
    // end::adocRequest[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }
}
