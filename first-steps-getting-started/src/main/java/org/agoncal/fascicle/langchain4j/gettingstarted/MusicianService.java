package org.agoncal.fascicle.langchain4j.gettingstarted;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.model.output.Response;
import static java.lang.System.exit;
import static java.time.Duration.ofSeconds;

import java.util.List;

// tag::adocSnippet[]
public class MusicianService {

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public static void main(String[] args) {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_4_O)
      .temperature(0.3)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      .build();

    Musician musician = new MusicianService().generateTopThreeAlbums(model, args[0], args[1]);

    System.out.println(musician);
    exit(0);
  }

  Musician generateTopThreeAlbums(ChatLanguageModel model, String firstName, String lastName) {
    SystemMessage systemMessage = SystemMessage.from("You are an expert in Jazz music.");
    UserMessage userMessage = UserMessage.from(String.format("List the top 3 albums of %s %s as bullet points. ", firstName, lastName));
    List<ChatMessage> messages = List.of(systemMessage, userMessage);

    Response<AiMessage> bio = model.generate(messages);

    return new Musician(firstName, lastName, bio.content().text());
  }
}
// end::adocSnippet[]
