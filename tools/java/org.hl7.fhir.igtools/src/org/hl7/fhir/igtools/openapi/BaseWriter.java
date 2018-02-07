package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BaseWriter {

  protected JsonObject object;

  public BaseWriter(JsonObject object) {
    super();
    this.object = object;
  }

  protected JsonObject ensureObject(String name) {
    JsonObject child = object.getAsJsonObject(name);
    if (child == null) {
      child = new JsonObject();
      object.add(name, child);
    }
    return child;
  }
  
  protected JsonObject ensureArrayObject(String arrayName, String propertyName, String value) {
    JsonArray arr = forceArray(arrayName);
    for (JsonElement e : arr) {
      String s = e.getAsJsonObject().get(propertyName).getAsString();
      if (value.equals(s))
        return e.getAsJsonObject();
    }
    JsonObject e = new JsonObject();
    arr.add(e);
    e.addProperty(propertyName, value);
    return e;
  }

  protected JsonArray forceArray(String arrayName) {
    JsonArray arr = object.get(arrayName).getAsJsonArray();
    if (arr == null) {
      arr = new JsonArray();
      object.add(arrayName, arr);
    }
    return arr;
  }
  

  protected JsonObject ensureMapObject(String mapName, String value) {
    JsonObject map = object.getAsJsonObject(mapName);
    if (map == null) {
      map = new JsonObject();
      object.add(mapName, map);
    }
    if (map.has(value))
      return map.getAsJsonObject(value);
    JsonObject e = new JsonObject();
    map.add(value, e);
    return e;
  }
  

  protected JsonObject ensureMapObject(String value) {
    if (object.has(value))
      return object.getAsJsonObject(value);
    JsonObject e = new JsonObject();
    object.add(value, e);
    return e;
  }
  
}
