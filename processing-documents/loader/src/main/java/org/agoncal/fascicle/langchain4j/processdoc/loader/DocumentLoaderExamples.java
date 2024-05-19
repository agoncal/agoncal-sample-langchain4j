package org.agoncal.fascicle.langchain4j.processdoc.loader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.loader.UrlDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.source.FileSystemSource;
import dev.langchain4j.data.document.source.UrlSource;
import dev.langchain4j.data.document.source.azure.storage.blob.AzureBlobStorageSource;
import dev.langchain4j.data.document.loader.azure.storage.blob.AzureBlobStorageDocumentLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentLoaderExamples {

  private static final Logger log = LoggerFactory.getLogger(DocumentLoaderExamples.class);

  public static void main(String[] args) {
    loadFromDocumentLoaderFile();
    loadFromFileSystemDocumentLoader();
//    loadMultipleDocuments();
//    loadMultipleDocumentsWithGlob();
//    loadMultipleDocumentsRecursively();
//    loadUsingParserFromSPI();
  }

  private static void loadFromDocumentLoaderFile() {
    // tag::adocLoadFromDocumentLoaderFile[]
    Path documentPath = toPath("data/bio-ella-fitzgerald.txt");
    Document document = DocumentLoader.load(new FileSystemSource(documentPath), new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::adocLoadFromDocumentLoaderFile[]
  }

  private static void loadFromDocumentLoaderURL() throws MalformedURLException {
    // tag::adocLoadFromDocumentLoaderURL[]
    URL documentUrl = new URL("https://en.wikipedia.org/wiki/Ella_Fitzgerald");
    Document document = DocumentLoader.load(new UrlSource(documentUrl), new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::adocLoadFromDocumentLoaderURL[]
  }

  private static void loadFromDocumentLoaderAzure() {
    // tag::adocLoadFromDocumentLoaderAzure[]
    Document document = DocumentLoader.load(new AzureBlobStorageSource(null, null, null, null, null), new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::adocLoadFromDocumentLoaderAzure[]
  }

  private static void loadFromFileSystemDocumentLoader() {
    // tag::adocLoadFromFileSystemDocumentLoader[]
    Path documentPath = toPath("data/bio-ella-fitzgerald.txt");
    Document document = FileSystemDocumentLoader.loadDocument(documentPath, new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::adocLoadFromFileSystemDocumentLoader[]
  }

  private static void loadFromUrlDocumentLoader() throws MalformedURLException {
    // tag::loadFromUrlDocumentLoader[]
    URL documentUrl = new URL("https://en.wikipedia.org/wiki/Ella_Fitzgerald");
    Document document = UrlDocumentLoader.load(documentUrl, new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::loadFromUrlDocumentLoader[]
  }

  private static void loadFromAzureDocumentLoader() throws MalformedURLException {
    // tag::loadFromAzureDocumentLoader[]
    URL documentUrl = new URL("https://en.wikipedia.org/wiki/Ella_Fitzgerald");
    Document document = new AzureBlobStorageDocumentLoader(null).loadDocument(null, null, new ApacheTikaDocumentParser());

    log.info(document.metadata(Document.FILE_NAME));
    log.info(document.metadata(Document.ABSOLUTE_DIRECTORY_PATH));
    log.info(document.text().trim().substring(0, 50));
    // end::loadFromAzureDocumentLoader[]
  }

  private static Path toPath(String fileName) {
    try {
      URL fileUrl = DocumentLoaderExamples.class.getClassLoader().getResource(fileName);
      return Paths.get(fileUrl.toURI());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
