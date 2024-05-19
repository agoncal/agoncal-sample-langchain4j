package org.agoncal.fascicle.langchain4j.processdoc.loader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.MalformedURLException;

public class DocumentParserExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentParserExamples.class);

  public static void main(String[] args) throws MalformedURLException {
    parseWithTextDocumentParser();
  }

  private static void parseWithTextDocumentParser() {
    // tag::adocParseWithTextDocumentParser[]
    InputStream documentStream = toStream("data/bio-ella-fitzgerald.txt");

    Document document = new TextDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 50));
    // end::adocParseWithTextDocumentParser[]
  }

  private static InputStream toStream(String fileName) {
    InputStream fileStream = DocumentParserExamples.class.getClassLoader().getResourceAsStream(fileName);
    return fileStream;
  }
}
