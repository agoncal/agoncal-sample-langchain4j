package org.agoncal.fascicle.langchain4j.invoking.memory;

// tag::adocSnippet[]

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) throws InterruptedException {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

//    musicianAssistant.useNoMemory();
    musicianAssistant.sendingMultipleMessages();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  // #################
  // ### NO MEMORY ###
  // #################
  public void useNoMemory() throws InterruptedException {
    System.out.println("### useNoMemory");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    // tag::adocNoMemory[]
    UserMessage firstMessage = UserMessage.from("My name is Antonio");
    UserMessage secondMessage = UserMessage.from("My favourite Rock band is the Beatles");
    UserMessage thirdMessage = UserMessage.from("When was their first album released?");
    UserMessage forthMessage = UserMessage.from("What's the name of the singer?");
    UserMessage fifthMessage = UserMessage.from("What's my name?");

    System.out.println(model.generate(firstMessage));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(secondMessage));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(thirdMessage));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(forthMessage));
    // tag::adocSkip[]
    Thread.sleep(5000);
    // end::adocSkip[]
    System.out.println(model.generate(fifthMessage));
    // end::adocNoMemory[]
  }

  // #################################
  // ### SENDING MULTIPLE MESSAGES ###
  // #################################
  public void sendingMultipleMessages() throws InterruptedException {
    System.out.println("### sendingMultipleMessages");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocMultipleMessages[]
    UserMessage firstMessage = UserMessage.from("My name is Antonio");
    UserMessage secondMessage = UserMessage.from("My favourite Rock band is the Beatles");
    UserMessage thirdMessage = UserMessage.from("When was their first album released?");
    UserMessage forthMessage = UserMessage.from("What's the name of the singer?");
    UserMessage fifthMessage = UserMessage.from("What's my name?");

    System.out.println(model.generate(firstMessage, secondMessage, thirdMessage, forthMessage, fifthMessage).content().text());
    // end::adocMultipleMessages[]
  }
}
