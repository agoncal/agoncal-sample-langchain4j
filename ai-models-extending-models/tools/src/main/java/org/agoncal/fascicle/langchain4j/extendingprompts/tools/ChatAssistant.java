package org.agoncal.fascicle.langchain4j.extendingprompts.tools;

// tag::adocHeader[]

import dev.langchain4j.agent.tool.DefaultToolExecutor;
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.agent.tool.ToolExecutor;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.agent.tool.ToolSpecifications;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ToolExecutionResultMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O;
import static java.time.Duration.ofSeconds;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatAssistant {

  public static void main(String[] args) throws Exception {

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


    // STEP 1: User specify tools and query
    System.out.println("####################################");
    System.out.println("STEP 1: User specify tools and query");
    // Tools
    List<ToolSpecification> toolSpecifications = ToolSpecifications.toolSpecificationsFrom(WeatherTools.class);
    // User query
    List<ChatMessage> chatMessages = new ArrayList<>();
    UserMessage userMessage = UserMessage.from("What will the weather be like in London tomorrow?");
    chatMessages.add(userMessage);

    // STEP 2: Model generate function arguments
    System.out.println("#########################################");
    System.out.println("STEP 2: Model generate function arguments");
    // Tool_choice: With multiple tools it's set to "auto" by default.
    AiMessage aiMessage = model.generate(singletonList(userMessage), toolSpecifications).content();
    aiMessage.toolExecutionRequests().forEach(toolSpec -> { // return all tools to call to answer the user query
      System.out.println("Function name: " + toolSpec.name());
      System.out.println("Function args:" + toolSpec.arguments());
    });
    chatMessages.add(aiMessage);

    // STEP 3: User execute function to obtain tool results
    System.out.println("####################################################");
    System.out.println("STEP 3: User execute function to obtain tool results");
    WeatherTools tools = new WeatherTools();
    ToolExecutor toolExecutor = new DefaultToolExecutor(tools, tools.getClass().getDeclaredMethod("getWeather", String.class));
    ToolExecutionRequest toolExecutionRequest = aiMessage.toolExecutionRequests().get(0);
    String result = toolExecutor.execute(toolExecutionRequest, UUID.randomUUID().toString());
    ToolExecutionResultMessage toolExecutionResultMessages = ToolExecutionResultMessage.from(toolExecutionRequest, result);
    chatMessages.addAll(List.of(toolExecutionResultMessages));

    // STEP 4: Model generate final response
    System.out.println("#####################################");
    System.out.println("STEP 4: Model generate final response");
    AiMessage finalResponse = model.generate(chatMessages).content();
    System.out.println(finalResponse.text()); //According to the payment data, the payment status of transaction T1005 is Pending.


  }
  // end::adocMethod[]

  private static List<ToolExecutionResultMessage> toolExecutor(
    Object objectWithTools,
    List<ToolExecutionRequest> toolExecutionRequests) throws Exception {
    String memoryId = UUID.randomUUID().toString();
    return toolExecutionRequests.stream()
      .map(request -> {
        try {
          ToolExecutor toolExecutor = new DefaultToolExecutor(objectWithTools,
            objectWithTools.getClass().getDeclaredMethod(request.name(),
              String.class));
          return ToolExecutionResultMessage.from(request, toolExecutor.execute(request, memoryId));
        } catch (NoSuchMethodException e) {
          System.err.println("No such tool found: " + request.name());
        }
        return null;
      })
      .collect(toList());
  }

}
