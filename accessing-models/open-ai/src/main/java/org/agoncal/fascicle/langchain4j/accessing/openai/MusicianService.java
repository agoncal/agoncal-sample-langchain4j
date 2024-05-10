package org.agoncal.fascicle.langchain4j.accessing.openai;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.language.StreamingLanguageModel;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.model.openai.OpenAiStreamingLanguageModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;

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

//    musicianService.useOpenAiLanguageTypeOfModel();
    musicianService.useOpenAiStreamingLanguageTypeOfModel();
//    musicianService.useOpenAiLanguageModel();
//    musicianService.useOpenAiLanguageModelPrompt();
//    musicianService.useOpenAiLanguageModelBuilder();
//    musicianService.useOpenAiChatModel();
//    musicianService.useOpenAiChatModelBuilder();
//    musicianService.useOpenAiModerationModel();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

  private static final String PROMPT = "When was the first Beatles album released?";

  // #############################
  // ### OPENAI LANGUAGE MODEL ###
  // #############################
  public void useOpenAiLanguageTypeOfModel() {
    System.out.println("### useOpenAiLanguageTypeOfModel");

    // tag::adocLanguageTypeOfModel[]
    LanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .temperature(0.3)
      .build();

    Response<String> completion = model.generate("When was the first Beatles album released?");

    System.out.println(completion.content());
    // end::adocLanguageTypeOfModel[]
    System.out.println(completion.finishReason());
    System.out.println(completion.tokenUsage());
  }

  public void useOpenAiLanguageModel() {
    System.out.println("### useOpenAiLanguageModel");

    OpenAiLanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Response<String> completion = model.generate("When was the first Beatles album released?");

    String content = completion.content();
    FinishReason finishReason = completion.finishReason();
    TokenUsage tokenUsage = completion.tokenUsage();

    System.out.println(content);
    System.out.println(finishReason.name());
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
  }

  public void useOpenAiLanguageModelPrompt() {
    System.out.println("### useOpenAiLanguageModelPrompt");

    OpenAiLanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Prompt prompt = new Prompt("When was the first Beatles album released?");
    Response<String> completion = model.generate(prompt);

    String content = completion.content();
    FinishReason finishReason = completion.finishReason();
    TokenUsage tokenUsage = completion.tokenUsage();

    System.out.println(content);
    System.out.println(finishReason.name());
    System.out.println(tokenUsage.inputTokenCount());
    System.out.println(tokenUsage.outputTokenCount());
    System.out.println(tokenUsage.totalTokenCount());
  }

  public void useOpenAiLanguageModelBuilder() {
    System.out.println("### useOpenAiLanguageModelBuilder");

    // tag::adocSnippet[]
    OpenAiLanguageModel model = OpenAiLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .temperature(0.3)
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocSnippet[]

    Response<String> completion = model.generate(PROMPT);

    System.out.println(completion.content());
    System.out.println(completion.finishReason());
    System.out.println(completion.tokenUsage());
  }

  // #######################################
  // ### OPENAI STREAMING LANGUAGE MODEL ###
  // #######################################
  public void useOpenAiStreamingLanguageTypeOfModel() {
    System.out.println("### useOpenAiStreamingLanguageTypeOfModel");

    // tag::adocStreamingLanguageTypeOfModel[]
    StreamingLanguageModel model = OpenAiStreamingLanguageModel.builder()
      .apiKey(OPENAI_API_KEY)
      .temperature(0.3)
      .build();

    model.generate("Who are some influential Jazz musicians?", new StreamingResponseHandler<>() {

      @Override
      public void onNext(String token) {
        System.out.print(token);
      }

      @Override
      public void onComplete(Response<String> response) {
        System.out.println("Streaming completed: " + response);
      }

      @Override
      public void onError(Throwable error) {
        error.printStackTrace();
      }
    });
    // tag::adocStreamingLanguageTypeOfModel[]
  }

  // #########################
  // ### OPENAI CHAT MODEL ###
  // #########################
  public void useOpenAiChatModel() {
    System.out.println("### useOpenAiChatModel");

    // tag::adocSimple[]
    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
    // end::adocSimple[]
  }

  public void useOpenAiChatModelBuilder() {
    System.out.println("### useOpenAiChatModelBuilder");

    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .modelName(GPT_3_5_TURBO)
      .temperature(0.9)
      .logRequests(true)
      .logResponses(true)
      .build();

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useOpenAiChatModelAiMessage() {
    System.out.println("### useOpenAiChatModelAiMessage");

    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    Response<AiMessage> completion = model.generate(sysMsg, userMsg);

    System.out.println(completion);
  }

  // ###############################
  // ### OPENAI MODERATION MODEL ###
  // ###############################
  public void useOpenAiModerationModel() {
    System.out.println("### useOpenAiModerationModel");

    // tag::adocModeration[]
    OpenAiModerationModel model = OpenAiModerationModel.withApiKey(OPENAI_API_KEY);

    Response<Moderation> completion = model.moderate("I want to kill all bass players.");

    Moderation content = completion.content();

    System.out.println(content);
    // end::adocModeration[]
  }
}
