package org.agoncal.fascicle.langchain4j.goingfurther;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DocumentIngestor {

  private static final Logger log = LoggerFactory.getLogger(DocumentIngestor.class);
  private static final String INDEX_NAME = "VintageStoreIndex";
  private static final String QDRANT_URL = "http://localhost:6334";

  private static EmbeddingModel embeddingModel;
  private static EmbeddingStore<TextSegment> embeddingStore;

  public static void main(String[] args) throws Exception {

    embeddingStore = embeddingStore();
    embeddingModel = new AllMiniLmL6V2EmbeddingModel();

    List<Path> pdfFiles = getPdfFiles();
    for (Path path : pdfFiles) {
      ingest(path);
    }
  }

  public static void ingest(Path pdfFile) throws Exception {

    log.info("### Load file {}", pdfFile.getFileName());
    ApachePdfBoxDocumentParser pdfParser = new ApachePdfBoxDocumentParser();
    Document document = pdfParser.parse(Files.newInputStream(pdfFile));
    log.debug("# PDF size: {}", document.text().length());

    log.info("### Split document into segments 100 tokens each");
    DocumentSplitter splitter = DocumentSplitters.recursive(2000, 200);
    List<TextSegment> segments = splitter.split(document);
    for (TextSegment segment : segments) {
      log.debug("# Segment size: {}", segment.text().length());
      segment.metadata().add("filename", pdfFile.getFileName());
    }
    log.debug("# Number of segments: {}", segments.size());

    log.info("### Embed segments (convert them into vectors that represent the meaning) using embedding model");
    List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
    log.debug("# Number of embeddings: {}", embeddings.size());
    log.debug("# Vector length: {}", embeddings.get(0).vector().length);

    log.info("### Store embeddings into Qdrant store for further search / retrieval");
    embeddingStore.addAll(embeddings, segments);
  }

  private static EmbeddingStore<TextSegment> embeddingStore() throws Exception {
    String qdrantHostname = new URI(QDRANT_URL).getHost();
    int qdrantPort = new URI(QDRANT_URL).getPort();

    QdrantGrpcClient.Builder grpcClientBuilder = QdrantGrpcClient.newBuilder(qdrantHostname, qdrantPort, false);
    QdrantClient qdrantClient = new QdrantClient(grpcClientBuilder.build());
    try {
      qdrantClient.createCollectionAsync(
        INDEX_NAME,
        Collections.VectorParams.newBuilder()
          .setSize(384)
          .setDistance(Collections.Distance.Cosine)
          .build()
      ).get();
    } catch (Exception e) {
      log.info("Collection already exists, skipping creation. Error: {}", e.getMessage());
    }

    return QdrantEmbeddingStore.builder()
      .client(qdrantClient)
      .collectionName(INDEX_NAME)
      .build();
  }

  private static List<Path> getPdfFiles() throws IOException {
    List<Path> pdfFiles = new ArrayList<>();
    Path rootPath = Paths.get("").toAbsolutePath();

    try (Stream<Path> paths = Files.walk(rootPath)) {
      paths.filter(Files::isRegularFile)
        .filter(path -> path.toString().endsWith(".pdf"))
        .forEach(pdfFiles::add);
    }

    return pdfFiles;
  }
}
