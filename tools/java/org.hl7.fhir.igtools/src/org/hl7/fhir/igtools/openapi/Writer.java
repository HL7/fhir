package org.hl7.fhir.igtools.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.hl7.fhir.utilities.TextFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Writer extends BaseWriter {

  private OutputStream stream;
  
  public Writer(OutputStream stream) {
    super( new JsonObject());
    this.stream = stream;
    object.addProperty("openapi", "3.0.1");
  }
  
  public Writer(OutputStream stream, InputStream template) throws JsonSyntaxException, IOException {
    super(parse(template));
    this.stream = stream;
    object.addProperty("openapi", "3.0.1");
  }
  
  private static JsonObject parse(InputStream template) throws JsonSyntaxException, IOException {
    JsonParser parser = new com.google.gson.JsonParser();
    return parser.parse(TextFile.streamToString(template)).getAsJsonObject();
  }

  public void commit() throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(object);
    OutputStreamWriter sw = new OutputStreamWriter(stream, "UTF-8");
    sw.write('\ufeff');  // Unicode BOM, translates to UTF-8 with the configured outputstreamwriter
    sw.write(json);
    sw.flush();
    sw.close();
  }  

  public InfoWriter info() {
    return new InfoWriter(ensureObject("info"));
  }

  public PathItemWriter path(String path) {
    return new PathItemWriter(ensureMapObject("paths", path));
  }

  public Writer pathRef(String path, String url) {
    ensureMapObject("paths", path).addProperty("$ref", url);
    return this;
  }


  public ServerWriter server(String url) {
    return new ServerWriter(ensureArrayObject("servers", "url", url)); 
  }
   

  public TagWriter tag(String name) {
    return new TagWriter(ensureArrayObject("tags", "name", name)); 
  }
   
  public ExternalDocsWriter externalDocs() {
    return new ExternalDocsWriter(ensureObject("externalDocs"));            
  }


  public ComponentsWriter components() {
    return new ComponentsWriter(ensureObject("components"));
  }

}
