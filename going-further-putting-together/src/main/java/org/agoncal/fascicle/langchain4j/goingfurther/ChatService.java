package org.agoncal.fascicle.langchain4j.goingfurther;
// tag::adocSnippet[]

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.service.AiServices;

import java.util.Scanner;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class ChatService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String input;
    System.out.println("You are chating with the Vintage Store chat bot, ask any questions.:");
    System.out.println("Type 'exit' to quit the chat bot.");

    while (true) {
      input = scanner.nextLine();

      if ("exit".equalsIgnoreCase(input)) {
        System.out.println("Exiting...");
        break;
      }

      System.out.println(answerQuestion(input));
    }

    scanner.close();
  }

  private static String answerQuestion(String question) {
    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .build();

    ChatAssistant assistant = AiServices.create(ChatAssistant.class, model);

    return assistant.chat(question);
  }
}
// end::adocSnippet[]
