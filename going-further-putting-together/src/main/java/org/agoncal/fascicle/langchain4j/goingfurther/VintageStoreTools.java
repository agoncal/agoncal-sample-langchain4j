package org.agoncal.fascicle.langchain4j.goingfurther;

import dev.langchain4j.agent.tool.Tool;

import java.time.LocalDateTime;

// tag::adocSnippet[]
public class VintageStoreTools {

  @Tool
  LocalDateTime lastUpdate() {
    return LocalDateTime.of(2021, 10, 1, 12, 0);
  }
}
// end::adocSnippet[]
