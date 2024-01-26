package org.agoncal.fascicle.langchain4j.chatting;

import static dev.langchain4j.model.openai.OpenAiModelName.GPT_3_5_TURBO_0301;
import dev.langchain4j.model.openai.OpenAiTokenizer;

import java.util.List;

public class Tokenization {

  // tag::adocSnippet[]
  String prompt = """
    Isaac Asimov (1920-1992), a Russian-born American author and biochemist,
    achieved renown as a prolific science fiction writer and science popularizer.
    Immigrating to the U.S. as a child, he began his literary career with the
    publication of "Marooned off Vesta" in 1939. Asimov's extensive body of work
    includes iconic novels like "Foundation" and "I, Robot." Beyond fiction,
    he became a revered science communicator, writing numerous accessible books
    and essays that demystified complex scientific concepts for a broad audience,
    leaving an enduring legacy in both literature and science education.
    """;
  // end::adocSnippet[]

  public static void main(String[] args) {
    Tokenization tokenization = new Tokenization();
    tokenization.tokenizeOpenAI();
  }

  private void tokenizeOpenAI() {
    System.out.println("### tokenizeOpenAI");
    OpenAiTokenizer tokenizer = new OpenAiTokenizer(GPT_3_5_TURBO_0301);

    // Estimate
    int nbOfTokens = tokenizer.estimateTokenCountInText(prompt);
    System.out.println("Number of tokens: " + nbOfTokens + "\n");

    // Encode
    List<Integer> tokens = tokenizer.encode(prompt);
    tokens.forEach(token -> System.out.print(token + " "));
    System.out.println("\n");

    // Decode
    String prompt = tokenizer.decode(tokens);
    System.out.println("Prompt: " + prompt);
  }
}
