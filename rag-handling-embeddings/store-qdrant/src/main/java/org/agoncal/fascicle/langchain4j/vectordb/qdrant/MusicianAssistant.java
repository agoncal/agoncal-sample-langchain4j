package org.agoncal.fascicle.langchain4j.vectordb.qdrant;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;

import java.util.ArrayList;
import java.util.List;

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

//    musicianAssistant.useQdrantToStoreEmbeddingsSimple();
//    musicianAssistant.useQdrantToStoreEmbeddingsComplex();
//    musicianAssistant.useQdrantToStoreEmbeddings();
    musicianAssistant.useQdrantToStoreJustEmbeddings();
  }

  public void useQdrantToStoreEmbeddingsSimple() {
    System.out.println("### useQdrantToStoreEmbeddingsSimple");

    // tag::adocQdrantToStoreEmbeddingsConnect[]
    EmbeddingStore<TextSegment> embeddingStore = QdrantEmbeddingStore.builder()
      .collectionName("vintagestore-collection")
      .build();
    // end::adocQdrantToStoreEmbeddingsConnect[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding queryEmbedding = embeddingModel.embed("Did you ever travel abroad?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreEmbeddings() {
    System.out.println("### useQdrantToStoreEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    // tag::adocQdrantToStoreEmbeddings[]
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("Kind of Blue (1959): Widely regarded as one of the greatest jazz albums of all time, featuring musicians like John Coltrane and Bill Evans.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970): This groundbreaking album marked a significant shift in jazz, blending rock, funk, and electronic elements.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from("Sketches of Spain (1960): A unique collaboration with arranger Gil Evans, showcases Davis's exploration of Spanish folk melodies and classical influences.");
    Embedding embedding3 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding3, segment3);
    // end::adocQdrantToStoreEmbeddings[]

    Embedding queryEmbedding = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreJustEmbeddings() {
    System.out.println("### useQdrantToStoreJustEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    // tag::adocQdrantToStoreJustEmbeddings[]
    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    Response<Embedding> embedding1 = embeddingModel.embed("Kind of Blue (1959): Widely regarded as one of the greatest jazz albums of all time, featuring musicians like John Coltrane and Bill Evans.");
    embeddingStore.add(embedding1.content());

    Response<Embedding> embedding2 = embeddingModel.embed("Bitches Brew (1970): This groundbreaking album marked a significant shift in jazz, blending rock, funk, and electronic elements.");
    embeddingStore.add(embedding2.content());

    Response<Embedding> embedding3 = embeddingModel.embed("Sketches of Spain (1960): A unique collaboration with arranger Gil Evans, showcases Davis's exploration of Spanish folk melodies and classical influences.");
    embeddingStore.add(embedding3.content());
    // end::adocQdrantToStoreJustEmbeddings[]

    Embedding queryEmbedding = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  public void useQdrantToStoreJustListEmbeddings() {
    System.out.println("### useQdrantToStoreJustListEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocQdrantToStoreJustListEmbeddings[]
    List<Embedding> embeddings = new ArrayList<>();

    Response<Embedding> embedding1 = embeddingModel.embed("Kind of Blue (1959): Widely regarded as one of the greatest jazz albums of all time, featuring musicians like John Coltrane and Bill Evans.");
    embeddings.add(embedding1.content());

    Response<Embedding> embedding2 = embeddingModel.embed("Bitches Brew (1970): This groundbreaking album marked a significant shift in jazz, blending rock, funk, and electronic elements.");
    embeddings.add(embedding2.content());

    Response<Embedding> embedding3 = embeddingModel.embed("Sketches of Spain (1960): A unique collaboration with arranger Gil Evans, showcases Davis's exploration of Spanish folk melodies and classical influences.");
    embeddings.add(embedding3.content());

    embeddingStore.addAll(embeddings);
    // end::adocQdrantToStoreJustListEmbeddings[]

    Embedding queryEmbedding = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedding().dimension());
  }

  public void useQdrantToStoreListEmbeddings() {
    System.out.println("### useQdrantToStoreListEmbeddings");

    createCollection();

    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    // tag::adocQdrantToStoreListEmbeddings[]
    TextSegment segment1 = TextSegment.from("Kind of Blue (1959): Widely regarded as one of the greatest jazz albums of all time, featuring musicians like John Coltrane and Bill Evans.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("Bitches Brew (1970): This groundbreaking album marked a significant shift in jazz, blending rock, funk, and electronic elements.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    TextSegment segment3 = TextSegment.from("Sketches of Spain (1960): A unique collaboration with arranger Gil Evans, showcases Davis's exploration of Spanish folk melodies and classical influences.");
    Embedding embedding3 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding3, segment3);
    // end::adocQdrantToStoreListEmbeddings[]

    Embedding queryEmbedding = embeddingModel.embed("Which Miles Davis album uses a piano?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
  }

  public void useQdrantToStoreEmbeddingsComplex() {
    System.out.println("### useQdrantToStoreEmbeddingsSimple");

    createCollection();

    // tag::adocComplex[]
    EmbeddingStore<TextSegment> embeddingStore =
      QdrantEmbeddingStore.builder()
        .collectionName("langchain4j-collection")
        .host("localhost")
        .port(6334)
        .build();
    // end::adocComplex[]

    EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    TextSegment segment1 = TextSegment.from("I've been to France twice.");
    Embedding embedding1 = embeddingModel.embed(segment1).content();
    embeddingStore.add(embedding1, segment1);

    TextSegment segment2 = TextSegment.from("New Delhi is the capital of India.");
    Embedding embedding2 = embeddingModel.embed(segment2).content();
    embeddingStore.add(embedding2, segment2);

    Embedding queryEmbedding = embeddingModel.embed("Did you ever travel abroad?").content();
    List<EmbeddingMatch<TextSegment>> relevant = embeddingStore.findRelevant(queryEmbedding, 1);
    EmbeddingMatch<TextSegment> embeddingMatch = relevant.get(0);

    System.out.println(embeddingMatch.score());
    System.out.println(embeddingMatch.embedded().text());
    // end::adocSnippet[]
  }

  private static void createCollection() {
    QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder("localhost", 6334, false);
    QdrantClient qdrantClient = new QdrantClient(grpcClientBuilder.build());
    try {
      qdrantClient.createCollectionAsync(
        "langchain4j-collection",
        Collections.VectorParams.newBuilder()
          .setSize(384)
          .setDistance(Collections.Distance.Cosine)
          .build()
      ).get();
    } catch (Exception e) {
      System.out.println("Collection already exists, skipping creation.");
    }
  }
}
