package org.agoncal.fascicle.langchain4j.gettingstarted;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

// tag::adocSnippet[]
@Testcontainers
public class MusicianTest {

  static String MODEL_NAME = "tinyllama";

  @Container
  static GenericContainer<?> ollamaContainer = new GenericContainer<>("langchain4j/ollama-" + MODEL_NAME + ":latest")
    .withExposedPorts(11434);

  static String baseUrl() {
    return String.format("http://%s:%d", ollamaContainer.getHost(), ollamaContainer.getFirstMappedPort());
  }

  @Test
  public void shouldGenerateMusicianTopThreeAlbums() {

    ChatLanguageModel model = OllamaChatModel.builder()
      .baseUrl(baseUrl())
      .modelName(MODEL_NAME)
      .timeout(Duration.ofMinutes(5))
      .build();

    Musician musician = new MusicianService().generateTopThreeAlbums(model, "Miles", "Davis");

    assertTrue(musician.albums().contains("Kind of Blue"));
  }
}
// end::adocSnippet[]
