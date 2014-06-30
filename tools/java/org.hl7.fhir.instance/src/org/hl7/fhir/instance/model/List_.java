package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2014, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A set of information summarized from a list of other resources.
 */
public class List_ extends Resource {

    public enum ListMode {
        working, // This list is the master list, maintained in an ongoing fashion with regular updates as the real world list it is tracking changes.
        snapshot, // This list was prepared as a snapshot. It should not be assumed to be current.
        changes, // The list is prepared as a statement of changes that have been made or recommended.
        Null; // added to help the parsers
        public static ListMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("working".equals(codeString))
          return working;
        if ("snapshot".equals(codeString))
          return snapshot;
        if ("changes".equals(codeString))
          return changes;
        throw new Exception("Unknown ListMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case working: return "working";
            case snapshot: return "snapshot";
            case changes: return "changes";
            default: return "?";
          }
        }
    }

  public static class ListModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("working".equals(codeString))
          return ListMode.working;
        if ("snapshot".equals(codeString))
          return ListMode.snapshot;
        if ("changes".equals(codeString))
          return ListMode.changes;
        throw new Exception("Unknown ListMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ListMode.working)
        return "working";
      if (code == ListMode.snapshot)
        return "snapshot";
      if (code == ListMode.changes)
        return "changes";
      return "?";
      }
    }

    public static class ListEntryComponent extends BackboneElement {
        /**
         * The flag allows the system constructing the list to make one or more statements about the role and significance of the item in the list.
         */
        protected List<CodeableConcept> flag = new ArrayList<CodeableConcept>();

        /**
         * True if this item is marked as deleted in the list.
         */
        protected Boolean deleted;

        /**
         * When this item was added to the list.
         */
        protected DateTime date;

        /**
         * A reference to the actual resource from which data was derived.
         */
        protected ResourceReference item;

        /**
         * The actual object that is the target of the reference (A reference to the actual resource from which data was derived.)
         */
        protected Resource itemTarget;

        private static final long serialVersionUID = 2031329135L;

      public ListEntryComponent() {
        super();
      }

      public ListEntryComponent(ResourceReference item) {
        super();
        this.item = item;
      }

        /**
         * @return {@link #flag} (The flag allows the system constructing the list to make one or more statements about the role and significance of the item in the list.)
         */
        public List<CodeableConcept> getFlag() { 
          return this.flag;
        }

    // syntactic sugar
        /**
         * @return {@link #flag} (The flag allows the system constructing the list to make one or more statements about the role and significance of the item in the list.)
         */
        public CodeableConcept addFlag() { 
          CodeableConcept t = new CodeableConcept();
          this.flag.add(t);
          return t;
        }

        /**
         * @return {@link #deleted} (True if this item is marked as deleted in the list.)
         */
        public Boolean getDeleted() { 
          return this.deleted;
        }

        /**
         * @param value {@link #deleted} (True if this item is marked as deleted in the list.)
         */
        public ListEntryComponent setDeleted(Boolean value) { 
          this.deleted = value;
          return this;
        }

        /**
         * @return True if this item is marked as deleted in the list.
         */
        public boolean getDeletedSimple() { 
          return this.deleted == null ? false : this.deleted.getValue();
        }

        /**
         * @param value True if this item is marked as deleted in the list.
         */
        public ListEntryComponent setDeletedSimple(boolean value) { 
          if (value == false)
            this.deleted = null;
          else {
            if (this.deleted == null)
              this.deleted = new Boolean();
            this.deleted.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #date} (When this item was added to the list.)
         */
        public DateTime getDate() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (When this item was added to the list.)
         */
        public ListEntryComponent setDate(DateTime value) { 
          this.date = value;
          return this;
        }

        /**
         * @return When this item was added to the list.
         */
        public DateAndTime getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value When this item was added to the list.
         */
        public ListEntryComponent setDateSimple(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #item} (A reference to the actual resource from which data was derived.)
         */
        public ResourceReference getItem() { 
          return this.item;
        }

        /**
         * @param value {@link #item} (A reference to the actual resource from which data was derived.)
         */
        public ListEntryComponent setItem(ResourceReference value) { 
          this.item = value;
          return this;
        }

        /**
         * @return {@link #item} (The actual object that is the target of the reference. A reference to the actual resource from which data was derived.)
         */
        public Resource getItemTarget() { 
          return this.itemTarget;
        }

        /**
         * @param value {@link #item} (The actual object that is the target of the reference. A reference to the actual resource from which data was derived.)
         */
        public ListEntryComponent setItemTarget(Resource value) { 
          this.itemTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("flag", "CodeableConcept", "The flag allows the system constructing the list to make one or more statements about the role and significance of the item in the list.", 0, java.lang.Integer.MAX_VALUE, flag));
          childrenList.add(new Property("deleted", "boolean", "True if this item is marked as deleted in the list.", 0, java.lang.Integer.MAX_VALUE, deleted));
          childrenList.add(new Property("date", "dateTime", "When this item was added to the list.", 0, java.lang.Integer.MAX_VALUE, date));
          childrenList.add(new Property("item", "Resource(Any)", "A reference to the actual resource from which data was derived.", 0, java.lang.Integer.MAX_VALUE, item));
        }

      public ListEntryComponent copy() {
        ListEntryComponent dst = new ListEntryComponent();
        dst.flag = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : flag)
          dst.flag.add(i.copy());
        dst.deleted = deleted == null ? null : deleted.copy();
        dst.date = date == null ? null : date.copy();
        dst.item = item == null ? null : item.copy();
        return dst;
      }

  }

    /**
     * Identifier for the List assigned for business purposes outside the context of FHIR.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * This code defines the purpose of the list - why it was created.
     */
    protected CodeableConcept code;

    /**
     * The common subject (or patient) of the resources that are in the list, if there is one.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The common subject (or patient) of the resources that are in the list, if there is one.)
     */
    protected Resource subjectTarget;

    /**
     * The entity responsible for deciding what the contents of the list were.
     */
    protected ResourceReference source;

    /**
     * The actual object that is the target of the reference (The entity responsible for deciding what the contents of the list were.)
     */
    protected Resource sourceTarget;

    /**
     * The date that the list was prepared.
     */
    protected DateTime date;

    /**
     * Whether items in the list have a meaningful order.
     */
    protected Boolean ordered;

    /**
     * How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.
     */
    protected Enumeration<ListMode> mode;

    /**
     * Entries in this list.
     */
    protected List<ListEntryComponent> entry = new ArrayList<ListEntryComponent>();

    /**
     * If the list is empty, why the list is empty.
     */
    protected CodeableConcept emptyReason;

    private static final long serialVersionUID = -483434446L;

    public List_() {
      super();
    }

    public List_(Enumeration<ListMode> mode) {
      super();
      this.mode = mode;
    }

    /**
     * @return {@link #identifier} (Identifier for the List assigned for business purposes outside the context of FHIR.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifier for the List assigned for business purposes outside the context of FHIR.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #code} (This code defines the purpose of the list - why it was created.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (This code defines the purpose of the list - why it was created.)
     */
    public List_ setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #subject} (The common subject (or patient) of the resources that are in the list, if there is one.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The common subject (or patient) of the resources that are in the list, if there is one.)
     */
    public List_ setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The common subject (or patient) of the resources that are in the list, if there is one.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The common subject (or patient) of the resources that are in the list, if there is one.)
     */
    public List_ setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #source} (The entity responsible for deciding what the contents of the list were.)
     */
    public ResourceReference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (The entity responsible for deciding what the contents of the list were.)
     */
    public List_ setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #source} (The actual object that is the target of the reference. The entity responsible for deciding what the contents of the list were.)
     */
    public Resource getSourceTarget() { 
      return this.sourceTarget;
    }

    /**
     * @param value {@link #source} (The actual object that is the target of the reference. The entity responsible for deciding what the contents of the list were.)
     */
    public List_ setSourceTarget(Resource value) { 
      this.sourceTarget = value;
      return this;
    }

    /**
     * @return {@link #date} (The date that the list was prepared.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the list was prepared.)
     */
    public List_ setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the list was prepared.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the list was prepared.
     */
    public List_ setDateSimple(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #ordered} (Whether items in the list have a meaningful order.)
     */
    public Boolean getOrdered() { 
      return this.ordered;
    }

    /**
     * @param value {@link #ordered} (Whether items in the list have a meaningful order.)
     */
    public List_ setOrdered(Boolean value) { 
      this.ordered = value;
      return this;
    }

    /**
     * @return Whether items in the list have a meaningful order.
     */
    public boolean getOrderedSimple() { 
      return this.ordered == null ? false : this.ordered.getValue();
    }

    /**
     * @param value Whether items in the list have a meaningful order.
     */
    public List_ setOrderedSimple(boolean value) { 
      if (value == false)
        this.ordered = null;
      else {
        if (this.ordered == null)
          this.ordered = new Boolean();
        this.ordered.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #mode} (How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.)
     */
    public Enumeration<ListMode> getMode() { 
      return this.mode;
    }

    /**
     * @param value {@link #mode} (How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.)
     */
    public List_ setMode(Enumeration<ListMode> value) { 
      this.mode = value;
      return this;
    }

    /**
     * @return How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.
     */
    public ListMode getModeSimple() { 
      return this.mode == null ? null : this.mode.getValue();
    }

    /**
     * @param value How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.
     */
    public List_ setModeSimple(ListMode value) { 
        if (this.mode == null)
          this.mode = new Enumeration<ListMode>();
        this.mode.setValue(value);
      return this;
    }

    /**
     * @return {@link #entry} (Entries in this list.)
     */
    public List<ListEntryComponent> getEntry() { 
      return this.entry;
    }

    // syntactic sugar
    /**
     * @return {@link #entry} (Entries in this list.)
     */
    public ListEntryComponent addEntry() { 
      ListEntryComponent t = new ListEntryComponent();
      this.entry.add(t);
      return t;
    }

    /**
     * @return {@link #emptyReason} (If the list is empty, why the list is empty.)
     */
    public CodeableConcept getEmptyReason() { 
      return this.emptyReason;
    }

    /**
     * @param value {@link #emptyReason} (If the list is empty, why the list is empty.)
     */
    public List_ setEmptyReason(CodeableConcept value) { 
      this.emptyReason = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier for the List assigned for business purposes outside the context of FHIR.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("code", "CodeableConcept", "This code defines the purpose of the list - why it was created.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("subject", "Resource(Patient|Group|Device|Location)", "The common subject (or patient) of the resources that are in the list, if there is one.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("source", "Resource(Practitioner|Patient|Device)", "The entity responsible for deciding what the contents of the list were.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("date", "dateTime", "The date that the list was prepared.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("ordered", "boolean", "Whether items in the list have a meaningful order.", 0, java.lang.Integer.MAX_VALUE, ordered));
        childrenList.add(new Property("mode", "code", "How this list was prepared - whether it is a working list that is suitable for being maintained on an ongoing basis, or if it represents a snapshot of a list of items from another source, or whether it is a prepared list where items may be marked as added, modified or deleted.", 0, java.lang.Integer.MAX_VALUE, mode));
        childrenList.add(new Property("entry", "", "Entries in this list.", 0, java.lang.Integer.MAX_VALUE, entry));
        childrenList.add(new Property("emptyReason", "CodeableConcept", "If the list is empty, why the list is empty.", 0, java.lang.Integer.MAX_VALUE, emptyReason));
      }

      public List_ copy() {
        List_ dst = new List_();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.code = code == null ? null : code.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.source = source == null ? null : source.copy();
        dst.date = date == null ? null : date.copy();
        dst.ordered = ordered == null ? null : ordered.copy();
        dst.mode = mode == null ? null : mode.copy();
        dst.entry = new ArrayList<ListEntryComponent>();
        for (ListEntryComponent i : entry)
          dst.entry.add(i.copy());
        dst.emptyReason = emptyReason == null ? null : emptyReason.copy();
        return dst;
      }

      protected List_ typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.List;
   }


}

