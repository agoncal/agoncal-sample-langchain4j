package org.agoncal.fascicle.langchain4j.prompts;

// tag::adocSnippet[]

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianService {

  public static void main(String[] args) throws InterruptedException {
    MusicianService musicianService = new MusicianService();

    musicianService.useTextPrompt();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // ###################
  // ### TEXT PROMPT ###
  // ###################
  public void useTextPrompt() {
    System.out.println("### useTextPrompt");

    // tag::adocTextPrompt[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(false)
      .build();

    System.out.println(model.generate("When was the first Rolling Stones album released?"));
    // end::adocTextPrompt[]
  }

  // ##################
  // ### PROMPT API ###
  // ##################
  public void usePromptAPI(){
    System.out.println("### usePromptAPI");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(false)
      .build();

    // tag::adocPromptAPI[]
    System.out.println(model.generate("When was the first Rolling Stones album released?"));
    // end::adocPromptAPI[]
  }

  // ###################
  // ### MESSAGE API ###
  // ###################
  public void useMessageAPI(){
    System.out.println("### useMessageAPI");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(false)
      .build();

    // tag::adocMessageAPI[]
    UserMessage userMessage = UserMessage.from("When was the first Rolling Stones album released?");

    System.out.println(model.generate(userMessage).content().text());
    // end::adocMessageAPI[]
  }
}
