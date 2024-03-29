package org.agoncal.fascicle.langchain4j.context;

// tag::adocSnippet[]

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
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

//    musicianService.useNoMemory();
    musicianService.sendingMultipleMessages();
//    musicianService.useConversationalChain();
//    musicianService.useConversationalChainWithMemory();
  }

  private static final String AZURE_OPENAI_KEY = System.getenv("AZURE_OPENAI_KEY");
  private static final String AZURE_OPENAI_ENDPOINT = System.getenv("AZURE_OPENAI_ENDPOINT");
  private static final String AZURE_OPENAI_DEPLOYMENT_NAME = System.getenv("AZURE_OPENAI_DEPLOYMENT_NAME");

  private static final String PROMPT = "When was the first Beatles album released?";

  // #################
  // ### NO MEMORY ###
  // #################
  public void useNoMemory() throws InterruptedException {
    System.out.println("### useNoMemory");

    // tag::adocNoMemory[]
    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(false)
      .build();

    System.out.println(model.generate("My name is Antonio"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("My favourite Rock band is the Rolling Stones"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("When was their first album released?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("What's the name of the singer?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate("What's my name?"));
    // end::adocNoMemory[]
  }

  // #################################
  // ### SENDING MULTIPLE MESSAGES ###
  // #################################
  public void sendingMultipleMessages() throws InterruptedException {
    System.out.println("### sendingMultipleMessages");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(false)
      .build();

    // tag::adocMultipleMessages[]
    UserMessage firstMessage = UserMessage.from("My name is Antonio");
    UserMessage secondMessage = UserMessage.from("My favourite Rock band is the Rolling Stones");
    UserMessage thirdMessage = UserMessage.from("When was their first album released?");
    UserMessage forthMessage = UserMessage.from("What's the name of the singer?");
    UserMessage fifthMessage = UserMessage.from("What's my name?");

    System.out.println(model.generate(firstMessage).content().text());
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(firstMessage, secondMessage).content().text());
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(firstMessage, secondMessage, thirdMessage).content().text());
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(firstMessage, secondMessage, thirdMessage, forthMessage).content().text());
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(firstMessage, secondMessage, thirdMessage, forthMessage, fifthMessage).content().text());
    // end::adocMultipleMessages[]
  }


  // ################################
  // ### USE CONVERSATIONAL CHAIN ###
  // ################################
  public void useConversationalChain() throws InterruptedException {
    System.out.println("### useConversationalChain");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(true)
      .build();

    // tag::adocChain[]
    ConversationalChain chain = ConversationalChain.builder()
      .chatLanguageModel(model)
      .build();

    System.out.println(chain.execute("My name is Antonio"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("My favourite Rock band is the Rolling Stones"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("When was their first album released?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's the name of the singer?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's my name?"));
    // end::adocChain[]
  }


  // ################################
  // ### USE CONVERSATIONAL CHAIN ###
  // ################################
  public void useConversationalChainWithMemory() throws InterruptedException {
    System.out.println("### useConversationalChainWithMemory");

    AzureOpenAiChatModel model = AzureOpenAiChatModel.builder()
      .apiKey(AZURE_OPENAI_KEY)
      .endpoint(AZURE_OPENAI_ENDPOINT)
      .deploymentName(AZURE_OPENAI_DEPLOYMENT_NAME)
      .temperature(0.7)
      .logRequestsAndResponses(true)
      .build();

    // tag::adocChatMemory[]
    ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(2);

    ConversationalChain chain = ConversationalChain.builder()
      .chatLanguageModel(model)
      .chatMemory(chatMemory)
      .build();

    System.out.println(chain.execute("My name is Antonio"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("My favourite Rock band is the Rolling Stones"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("When was their first album released?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's the name of the singer?"));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(chain.execute("What's my name?"));
    // end::adocChatMemory[]
  }
}
// end::adocSnippet[]
