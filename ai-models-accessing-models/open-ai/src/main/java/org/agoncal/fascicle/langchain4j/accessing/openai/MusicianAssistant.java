package org.agoncal.fascicle.langchain4j.accessing.openai;

import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.language.LanguageModel;
import dev.langchain4j.model.language.StreamingLanguageModel;
import dev.langchain4j.model.moderation.Moderation;
import dev.langchain4j.model.moderation.ModerationModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiLanguageModel;
import dev.langchain4j.model.openai.OpenAiModerationModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingLanguageModel;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.model.output.TokenUsage;

import java.time.Duration;

// tag::adocSkip[]

/**
 * @author Antonio Goncalves
 * http://www.antoniogoncalves.org
 * --
 */
// end::adocSkip[]
public class MusicianAssistant {

  public static void main(String[] args) {
    MusicianAssistant musicianAssistant = new MusicianAssistant();

//    musicianService.useOpenAiLanguageTypeOfModel();
//    musicianService.useOpenAiLanguageModel();
//    musicianService.useOpenAiLanguageModelPrompt();
//    musicianService.useOpenAiLanguageModelBuilder();
//    musicianService.useOpenAiStreamingLanguageTypeOfModel();

//    musicianService.useOpenAiChatTypeOfModel();
    musicianAssistant.useOpenAiChatModelBuilder();
//    musicianService.useOpenAiStreamingChatTypeOfModel();

//    musicianService.useOpenAiModerationTypeOfModel();
//    musicianService.useOpenAiImageTypeOfModel();
  }

  private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");
  private static final String OPENAI_ORGANIZATION = System.getenv("OPENAI_ORGANIZATION");
  private static final String PROMPT = "When was the first Beatles album released?";

  // #############################
  // ### OPENAI LANGUAGE MODEL ###
  // #############################
  public void useOpenAiLanguageTypeOfModel() {
    System.out.println("### useOpenAiLanguageTypeOfModel");

    // tag::adocLanguageTypeOfModel[]
    LanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Response<String> response = model.generate("When was the first Beatles album released?");

    String content = response.content();

    System.out.println(content);
    // end::adocLanguageTypeOfModel[]
    System.out.println(response.finishReason());
    System.out.println(response.tokenUsage());
  }

  public void useOpenAiLanguageModel() {
    System.out.println("### useOpenAiLanguageModel");

    OpenAiLanguageModel model = OpenAiLanguageModel.withApiKey(OPENAI_API_KEY);

    Response<String> response = model.generate("When was the first Beatles album released?");

    String content = response.content();
    FinishReason finishReason = response.finishReason();
    TokenUsage tokenUsage = response.tokenUsage();

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
    Response<String> response = model.generate(prompt);

    String content = response.content();
    FinishReason finishReason = response.finishReason();
    TokenUsage tokenUsage = response.tokenUsage();

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

    Response<String> response = model.generate(PROMPT);

    System.out.println(response.content());
    System.out.println(response.finishReason());
    System.out.println(response.tokenUsage());
  }

  // #######################################
  // ### OPENAI STREAMING LANGUAGE MODEL ###
  // #######################################
  public void useOpenAiStreamingLanguageTypeOfModel() {
    System.out.println("### useOpenAiStreamingLanguageTypeOfModel");

    // tag::adocStreamingLanguageTypeOfModel[]
    StreamingLanguageModel model = OpenAiStreamingLanguageModel.withApiKey(OPENAI_API_KEY);

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
    // end::adocStreamingLanguageTypeOfModel[]
  }

  // #########################
  // ### OPENAI CHAT MODEL ###
  // #########################
  public void useOpenAiChatTypeOfModel() {
    System.out.println("### useOpenAiChatTypeOfModel");

    // tag::adocChatTypeOfModel[]
    ChatLanguageModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    String content = model.generate("What inspired the author to start writing?");

    System.out.println(content);
    // end::adocChatTypeOfModel[]
  }

  public void useOpenAiSimpleConf() {
    System.out.println("### useOpenAiSimpleConf");

    // tag::adocSimpleConf[]
    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    // end::adocSimpleConf[]

    String content = model.generate("What inspired the author to start writing?");

    System.out.println(content);
  }

  public void useOpenAiChatModelBuilder() {
    System.out.println("### useOpenAiChatModelBuilder");

    // tag::adocRichConf[]
    OpenAiChatModel model = OpenAiChatModel.builder()
      .apiKey(OPENAI_API_KEY)
      .organizationId(OPENAI_ORGANIZATION)
      .modelName(GPT_4_O)
      .frequencyPenalty(0.5)
      .temperature(0.9)
      .maxRetries(3)
      .maxTokens(150)
      .topP(1.0)
      .seed(42)
      .timeout(Duration.ofSeconds(30))
      .logRequests(true)
      .logResponses(true)
      .build();
    // end::adocRichConf[]

    String completion = model.generate("When was the first Rolling Stones album released?");

    System.out.println(completion);
  }

  public void useOpenAiChatModelAiMessage() {
    System.out.println("### useOpenAiChatModelAiMessage");

    OpenAiChatModel model = OpenAiChatModel.withApiKey(OPENAI_API_KEY);

    SystemMessage sysMsg = new SystemMessage("You are a music expert.");
    UserMessage userMsg = new UserMessage("When was the first Rolling Stones album released?");
    Response<AiMessage> response = model.generate(sysMsg, userMsg);

    System.out.println(response);
  }

  // ###################################
  // ### OPENAI STREAMING CHAT MODEL ###
  // ###################################
  public void useOpenAiStreamingChatTypeOfModel() {
    System.out.println("### useOpenAiStreamingChatTypeOfModel");

    // tag::adocStreamingChatTypeOfModel[]
    StreamingChatLanguageModel model = OpenAiStreamingChatModel.withApiKey(OPENAI_API_KEY);

    model.generate("What are some common formats and sizes of video tapes?", new StreamingResponseHandler<>() {

      @Override
      public void onNext(String token) {
        System.out.print(token);
      }

      @Override
      public void onComplete(Response<AiMessage> response) {
        System.out.println("Streaming completed: " + response);
      }

      @Override
      public void onError(Throwable error) {
        error.printStackTrace();
      }
    });
    // end::adocStreamingChatTypeOfModel[]
  }

  // ###############################
  // ### OPENAI MODERATION MODEL ###
  // ###############################
  public void useOpenAiModerationTypeOfModel() {
    System.out.println("### useOpenAiModerationTypeOfModel");

    // tag::adocModerationTypeOfModel[]
    ModerationModel model = OpenAiModerationModel.withApiKey(OPENAI_API_KEY);

    Response<Moderation> response = model.moderate("I want to kill all bass players.");

    Moderation content = response.content();

    System.out.println(content);
    // end::adocModerationTypeOfModel[]
  }

  // ##########################
  // ### OPENAI IMAGE MODEL ###
  // ##########################
  public void useOpenAiImageTypeOfModel() {
    System.out.println("### useOpenAiImageTypeOfModel");

    // tag::adocImageTypeOfModel[]
    ImageModel model = OpenAiImageModel.withApiKey(OPENAI_API_KEY);

    Response<Image> response = model.generate("Jazz album cover showing all main Jazz artist, à la Sergent Pepper");

    Image content = response.content();

    System.out.println(content.url());
    // end::adocImageTypeOfModel[]
  }
}
