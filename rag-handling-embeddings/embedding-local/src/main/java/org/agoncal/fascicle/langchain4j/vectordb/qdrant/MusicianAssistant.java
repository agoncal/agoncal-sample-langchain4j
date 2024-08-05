package org.agoncal.fascicle.langchain4j.vectordb.qdrant;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.output.Response;

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

    musicianAssistant.textToEmbeddingAllMiniLmL6V2();
  }

  public void textToEmbeddingAllMiniLmL6V2() {
    System.out.println("### textToEmbeddingAllMiniLmL6V2");

    // tag::adocTextToEmbeddingAllMiniLmL6V2[]
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment = TextSegment.from("Isaac Asimov is a writer and is a biochemist.");
    Response<Embedding> embedding = embeddingModel.embed(segment);

    System.out.println(embedding.content());
    // end::adocTextToEmbeddingAllMiniLmL6V2[]
  }
}
