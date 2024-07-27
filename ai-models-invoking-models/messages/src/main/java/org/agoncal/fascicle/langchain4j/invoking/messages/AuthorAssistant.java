package org.agoncal.fascicle.langchain4j.invoking.messages;

// tag::adocSnippet[]

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.PdfFileContent;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.data.pdf.PdfFile;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;

import java.net.URISyntaxException;
import java.nio.file.Paths;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class AuthorAssistant {

  public static void main(String[] args) throws Exception {
    AuthorAssistant authorAssistant = new AuthorAssistant();

//    authorAssistant.useUserMessage();
//    authorAssistant.useUserMessageFrom();
//    authorAssistant.useSystemMessage();
//    authorAssistant.useUserMessageContent();
    authorAssistant.useUserMessagePdfContent();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  public void useUserMessage() {
    System.out.println("### useUserMessage");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocUserMessage[]
    UserMessage userMessage = new UserMessage("What genre is Brave New World?");
    Response<AiMessage> reponse = model.generate(userMessage);
    // end::adocUserMessage[]

    System.out.println(reponse.content().text());
  }

  public void useUserMessageFrom() {
    System.out.println("### useUserMessageFrom");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocUserMessageFrom[]
    UserMessage userMessage = UserMessage.from("What genre is Brave New World?");
    Response<AiMessage> reponse = model.generate(userMessage);
    // end::adocUserMessageFrom[]

    System.out.println(reponse.content().text());
  }

  public void useSystemMessage() {
    System.out.println("### useSystemMessage");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocSystemMessage[]
    SystemMessage systemMessage = new SystemMessage("You are a library assistant and can output any book upon user request.");
    UserMessage userMessage = new UserMessage("What genre is Brave New World?");
    Response<AiMessage> reponse = model.generate(systemMessage, userMessage);
    // end::adocSystemMessage[]

    System.out.println(reponse.content().text());
  }

  public void useUserMessageContent() {
    System.out.println("### useUserMessageContent");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocUserMessageContent[]
    TextContent textContent = TextContent.from("What genre is Brave New World?");
    UserMessage userMessage = UserMessage.from(textContent);
    Response<AiMessage> reponse = model.generate(userMessage);
    // end::adocUserMessageContent[]

    System.out.println(reponse.content().text());
  }

  public void useUserMessagesPdfContent() throws URISyntaxException {
    System.out.println("### useUserMessagePdfContent");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocUserMessagesPdfContent[]
    PdfFile urlPdfFile = PdfFile.builder()
      .url("src/main/resources/brave_new_world_chapter_I.pdf")
      .build();
    PdfFileContent pdfFileContent = new PdfFileContent(urlPdfFile);

    UserMessage userMessage = UserMessage.from("Summarize the following PDF file");
    UserMessage pdfMessage = UserMessage.from(pdfFileContent);
    Response<AiMessage> reponse = model.generate(userMessage, pdfMessage);
    // end::adocUserMessagesPdfContent[]

    System.out.println(reponse.content().text());
  }

  public void useUserMessagePdfContent() throws URISyntaxException {
    System.out.println("### useUserMessagePdfContent");

    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    // tag::adocUserMessagePdfContent[]
    UserMessage userMessage = UserMessage.from(
      TextContent.from("Summarize the following PDF file"),
      PdfFileContent.from(Paths.get("src/main/resources/brave_new_world_chapter_I.pdf").toUri())
    );
    Response<AiMessage> reponse = model.generate(userMessage);
    // end::adocUserMessagePdfContent[]

    System.out.println(reponse.content().text());
  }
}
