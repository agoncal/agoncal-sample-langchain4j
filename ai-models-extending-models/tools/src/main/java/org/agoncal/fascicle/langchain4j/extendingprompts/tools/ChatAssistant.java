package org.agoncal.fascicle.langchain4j.extendingprompts.tools;

// tag::adocHeader[]

import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import dev.langchain4j.model.output.Response;
import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;

import java.util.List;

public class ChatAssistant {

  public static void main(String[] args) throws NoSuchMethodException {

    ChatLanguageModel model = OpenAiChatModel.builder()
      .apiKey(System.getenv("OPENAI_API_KEY"))
      .modelName(GPT_4_O)
      // tag::adocSkip[]
      .temperature(0.7)
      .timeout(ofSeconds(60))
      .logRequests(true)
      .logResponses(true)
      // end::adocSkip[]
      .build();

//    ToolSpecification toolSpecification = ToolSpecification.builder()
//      .name("getWeather")
//      .description("Returns the weather forecast for a given city")
//      .addParameter("city", type("string"), description("The city for which the weather forecast should be returned"))
//      .addParameter("temperatureUnit", enums(TemperatureUnit.class)) // enum TemperatureUnit { CELSIUS, FAHRENHEIT }
//      .build();

//    UserMessage question = new UserMessage("What is the capital city of France?");
//    Response<AiMessage> response;

    List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WeatherTools.class);
    UserMessage userMessage = UserMessage.from("What will the weather be like in London tomorrow?");
    Response<AiMessage> response = model.generate(singletonList(userMessage), toolSpecifications);
    AiMessage aiMessage = response.content();

    System.out.println(aiMessage);

    // No Tools
//    response = model.generate(List.of(question));
//    System.out.println("============= 1");
//    System.out.println(response.content());

    // One Tools method in a class
//    Method lastUpdatePrivacy = TermsAndConditionTools.class.getDeclaredMethod("lastUpdatePrivacy");
//    ToolSpecification toolSpecification = ToolSpecifications.toolSpecificationFrom(lastUpdatePrivacy);
//    response = model.generate(List.of(question), toolSpecification);
//    System.out.println("============= 2");
//    System.out.println(response.content());

    // One class with several tools
//    List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(TermsAndConditionTools.class);
//    response = model.generate(List.of(question), toolSpecification);
//    System.out.println("============= 3");
//    System.out.println(response.content());

  }
  // end::adocMethod[]
}
