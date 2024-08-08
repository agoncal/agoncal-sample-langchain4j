package org.agoncal.fascicle.langchain4j.rag.rag;


import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

// tag::adocSnippet[]
public interface ChatAssistant {

  @SystemMessage("""
    You are an expert of the company Vintage Store.
    Focus on the customer and answer the questions.
    Keep your answers short and to the point.
    """)
  @UserMessage("Here is my question: {{question}}")
  String chat(@V("question") String question);

}
// end::adocSnippet[]
