package org.agoncal.fascicle.langchain4j.processdoc.parser;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.document.parser.apache.poi.ApachePoiDocumentParser;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.MalformedURLException;

public class DocumentParserExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentParserExamples.class);

  public static void main(String[] args) throws MalformedURLException {
    parseWithTextDocumentParser();
    parseWithPDFBox();
    parseWithTika();
    parseWithPoi();
  }

  private static void parseWithTextDocumentParser() {
    // tag::adocParseWithTextDocumentParser[]
    InputStream documentStream = toStream("data/bio-ella-fitzgerald.txt");

    Document document = new TextDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 50));
    // end::adocParseWithTextDocumentParser[]
  }

  private static void parseWithPDFBox() {
    // tag::adocParseWithApachePdfBoxDocumentParser[]
    InputStream documentStream = toStream("data/history-of-audiotapes.pdf");

    Document document = new ApachePdfBoxDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 100));
    // end::adocParseWithApachePdfBoxDocumentParser[]
  }

  private static void parseWithTika() {
    // tag::adocParseWithApacheTikaDocumentParser[]
    InputStream documentStream = toStream("data/history-of-audiotapes.pdf");

    Document document = new ApacheTikaDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 100));
    // end::adocParseWithApacheTikaDocumentParser[]
  }

  private static void parseWithPoi() {
    // tag::adocParseWithApachePoiDocumentParser[]
    InputStream documentStream = toStream("data/history-of-videotapes.docx");

    Document document = new ApachePoiDocumentParser().parse(documentStream);

    log.info(document.text().trim().substring(0, 100));
    // end::adocParseWithApachePoiDocumentParser[]
  }

  private static InputStream toStream(String fileName) {
    InputStream fileStream = DocumentParserExamples.class.getClassLoader().getResourceAsStream(fileName);
    return fileStream;
  }
}
