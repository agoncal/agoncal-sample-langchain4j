package org.agoncal.fascicle.langchain4j.processdoc.splitter;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.source.FileSystemSource;
import dev.langchain4j.data.document.splitter.DocumentBySentenceSplitter;
import dev.langchain4j.data.segment.TextSegment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DocumentSplitterExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentSplitterExamples.class);

  public static void main(String[] args) throws MalformedURLException {
    splitWithDocumentBySentenceSplitter();
  }

  private static void splitWithDocumentBySentenceSplitter() {
    // tag::adocSplitWithDocumentBySentenceSplitter[]
    Path documentPath = toPath("data/biography-of-isaac-asimov");

    Document document = DocumentLoader.load(new FileSystemSource(documentPath), new TextDocumentParser());

    DocumentSplitter splitter = new DocumentBySentenceSplitter(1000, 50);

    List<TextSegment> segments = splitter.split(document);

    segments.forEach(segment -> System.out.println(segment.text().length()));

    // end::adocSplitWithDocumentBySentenceSplitter[]
  }

  private static Path toPath(String fileName) {
    try {
      URL fileUrl = DocumentSplitterExamples.class.getClassLoader().getResource(fileName);
      return Paths.get(fileUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
