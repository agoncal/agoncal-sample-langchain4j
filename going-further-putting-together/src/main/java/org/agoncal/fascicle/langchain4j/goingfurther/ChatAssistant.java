package org.agoncal.fascicle.langchain4j.goingfurther;

// tag::adocSnippet[]

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ChatAssistant {

  @SystemMessage("You are an expert of the company Vintage Store.")
  @UserMessage("{{question}}")
  String chat(@V("question") String question);

}
// end::adocSnippet[]
