package org.hl7.fhir.definitions.model;

/*-
 * #%L
 * org.hl7.fhir.publisher.core
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class MappingSpace {

  private String columnName; // in the spreadsheets
  private String title; // in the specification
  private XhtmlNode preamble; // html to go in spec
  private String id; // internal page reference
  private int sortOrder; 
  private boolean publish;
  private boolean sparse;
  private boolean pattern;
  private String link;
  
  public MappingSpace(String columnName, String title, String id, int sortOrder, boolean publish, boolean sparse, boolean pattern, String link) {
    super();
    this.columnName = columnName;
    this.title = title;
    this.id = id;
    this.sortOrder = sortOrder;
    this.preamble = null;
    this.publish = publish;
    this.sparse = sparse;
    this.pattern = pattern;
    this.link = link;
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public XhtmlNode getPreamble() {
    return preamble;
  }

  public void setPreamble(XhtmlNode preamble) {
    this.preamble = preamble;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }

  public boolean isPublish() {
    return publish;
  }

  public void setPublish(boolean publish) {
    this.publish = publish;
  }

  public boolean isSparse() {
    return sparse;
  }

  public boolean isPattern() {
    return pattern;
  }   
  
  public String getLink() {
    return link;
  }

  public boolean hasLink() {
    return !Utilities.noString(link);
  }
}
