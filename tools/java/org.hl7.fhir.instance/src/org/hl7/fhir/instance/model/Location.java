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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

import java.math.*;
/**
 * Details and position information for a physical place where services are provided  and resources and participants may be stored, found, contained or accommodated.
 */
public class Location extends Resource {

    public enum LocationStatus {
        active, // The location is operational.
        suspended, // The location is temporarily closed.
        inactive, // The location is no longer used.
        Null; // added to help the parsers
        public static LocationStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return active;
        if ("suspended".equals(codeString))
          return suspended;
        if ("inactive".equals(codeString))
          return inactive;
        throw new Exception("Unknown LocationStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case suspended: return "suspended";
            case inactive: return "inactive";
            default: return "?";
          }
        }
    }

  public static class LocationStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return LocationStatus.active;
        if ("suspended".equals(codeString))
          return LocationStatus.suspended;
        if ("inactive".equals(codeString))
          return LocationStatus.inactive;
        throw new Exception("Unknown LocationStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == LocationStatus.active)
        return "active";
      if (code == LocationStatus.suspended)
        return "suspended";
      if (code == LocationStatus.inactive)
        return "inactive";
      return "?";
      }
    }

    public enum LocationMode {
        instance, // The Location resource represents a specific instance of a Location.
        kind, // The Location represents a class of Locations.
        Null; // added to help the parsers
        public static LocationMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("instance".equals(codeString))
          return instance;
        if ("kind".equals(codeString))
          return kind;
        throw new Exception("Unknown LocationMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case instance: return "instance";
            case kind: return "kind";
            default: return "?";
          }
        }
    }

  public static class LocationModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("instance".equals(codeString))
          return LocationMode.instance;
        if ("kind".equals(codeString))
          return LocationMode.kind;
        throw new Exception("Unknown LocationMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == LocationMode.instance)
        return "instance";
      if (code == LocationMode.kind)
        return "kind";
      return "?";
      }
    }

    public static class LocationPositionComponent extends BackboneElement {
        /**
         * Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).
         */
        protected Decimal longitude;

        /**
         * Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).
         */
        protected Decimal latitude;

        /**
         * Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).
         */
        protected Decimal altitude;

        private static final long serialVersionUID = -1232709324L;

      public LocationPositionComponent() {
        super();
      }

      public LocationPositionComponent(Decimal longitude, Decimal latitude) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
      }

        /**
         * @return {@link #longitude} (Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).)
         */
        public Decimal getLongitude() { 
          return this.longitude;
        }

        /**
         * @param value {@link #longitude} (Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).)
         */
        public LocationPositionComponent setLongitude(Decimal value) { 
          this.longitude = value;
          return this;
        }

        /**
         * @return Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).
         */
        public BigDecimal getLongitudeSimple() { 
          return this.longitude == null ? null : this.longitude.getValue();
        }

        /**
         * @param value Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).
         */
        public LocationPositionComponent setLongitudeSimple(BigDecimal value) { 
            if (this.longitude == null)
              this.longitude = new Decimal();
            this.longitude.setValue(value);
          return this;
        }

        /**
         * @return {@link #latitude} (Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).)
         */
        public Decimal getLatitude() { 
          return this.latitude;
        }

        /**
         * @param value {@link #latitude} (Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).)
         */
        public LocationPositionComponent setLatitude(Decimal value) { 
          this.latitude = value;
          return this;
        }

        /**
         * @return Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).
         */
        public BigDecimal getLatitudeSimple() { 
          return this.latitude == null ? null : this.latitude.getValue();
        }

        /**
         * @param value Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).
         */
        public LocationPositionComponent setLatitudeSimple(BigDecimal value) { 
            if (this.latitude == null)
              this.latitude = new Decimal();
            this.latitude.setValue(value);
          return this;
        }

        /**
         * @return {@link #altitude} (Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).)
         */
        public Decimal getAltitude() { 
          return this.altitude;
        }

        /**
         * @param value {@link #altitude} (Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).)
         */
        public LocationPositionComponent setAltitude(Decimal value) { 
          this.altitude = value;
          return this;
        }

        /**
         * @return Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).
         */
        public BigDecimal getAltitudeSimple() { 
          return this.altitude == null ? null : this.altitude.getValue();
        }

        /**
         * @param value Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).
         */
        public LocationPositionComponent setAltitudeSimple(BigDecimal value) { 
          if (value == null)
            this.altitude = null;
          else {
            if (this.altitude == null)
              this.altitude = new Decimal();
            this.altitude.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("longitude", "decimal", "Longitude. The value domain and the interpretation are the same as for the text of the longitude element in KML (see notes below).", 0, java.lang.Integer.MAX_VALUE, longitude));
          childrenList.add(new Property("latitude", "decimal", "Latitude. The value domain and the interpretation are the same as for the text of the latitude element in KML (see notes below).", 0, java.lang.Integer.MAX_VALUE, latitude));
          childrenList.add(new Property("altitude", "decimal", "Altitude. The value domain and the interpretation are the same as for the text of the altitude element in KML (see notes below).", 0, java.lang.Integer.MAX_VALUE, altitude));
        }

      public LocationPositionComponent copy() {
        LocationPositionComponent dst = new LocationPositionComponent();
        dst.longitude = longitude == null ? null : longitude.copy();
        dst.latitude = latitude == null ? null : latitude.copy();
        dst.altitude = altitude == null ? null : altitude.copy();
        return dst;
      }

  }

    /**
     * Unique code or number identifying the location to its users.
     */
    protected Identifier identifier;

    /**
     * Name of the location as used by humans. Does not need to be unique.
     */
    protected String_ name;

    /**
     * Description of the Location, which helps in finding or referencing the place.
     */
    protected String_ description;

    /**
     * Indicates the type of function performed at the location.
     */
    protected CodeableConcept type;

    /**
     * The contact details of communication devices available at the location. This can include phone numbers, fax numbers, mobile numbers, email addresses and web sites.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Physical location.
     */
    protected Address address;

    /**
     * Physical form of the location, e.g. building, room, vehicle, road.
     */
    protected CodeableConcept physicalType;

    /**
     * The absolute geographic location of the Location, expressed in a KML compatible manner (see notes below for KML).
     */
    protected LocationPositionComponent position;

    /**
     * The organization that is responsible for the provisioning and upkeep of the location.
     */
    protected ResourceReference managingOrganization;

    /**
     * The actual object that is the target of the reference (The organization that is responsible for the provisioning and upkeep of the location.)
     */
    protected Organization managingOrganizationTarget;

    /**
     * active | suspended | inactive.
     */
    protected Enumeration<LocationStatus> status;

    /**
     * Another Location which this Location is physically part of.
     */
    protected ResourceReference partOf;

    /**
     * The actual object that is the target of the reference (Another Location which this Location is physically part of.)
     */
    protected Location partOfTarget;

    /**
     * Indicates whether a resource instance represents a specific location or a class of locations.
     */
    protected Enumeration<LocationMode> mode;

    private static final long serialVersionUID = -282813644L;

    public Location() {
      super();
    }

    /**
     * @return {@link #identifier} (Unique code or number identifying the location to its users.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Unique code or number identifying the location to its users.)
     */
    public Location setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #name} (Name of the location as used by humans. Does not need to be unique.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (Name of the location as used by humans. Does not need to be unique.)
     */
    public Location setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return Name of the location as used by humans. Does not need to be unique.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value Name of the location as used by humans. Does not need to be unique.
     */
    public Location setNameSimple(String value) { 
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #description} (Description of the Location, which helps in finding or referencing the place.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (Description of the Location, which helps in finding or referencing the place.)
     */
    public Location setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Description of the Location, which helps in finding or referencing the place.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Description of the Location, which helps in finding or referencing the place.
     */
    public Location setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #type} (Indicates the type of function performed at the location.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Indicates the type of function performed at the location.)
     */
    public Location setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #telecom} (The contact details of communication devices available at the location. This can include phone numbers, fax numbers, mobile numbers, email addresses and web sites.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (The contact details of communication devices available at the location. This can include phone numbers, fax numbers, mobile numbers, email addresses and web sites.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #address} (Physical location.)
     */
    public Address getAddress() { 
      return this.address;
    }

    /**
     * @param value {@link #address} (Physical location.)
     */
    public Location setAddress(Address value) { 
      this.address = value;
      return this;
    }

    /**
     * @return {@link #physicalType} (Physical form of the location, e.g. building, room, vehicle, road.)
     */
    public CodeableConcept getPhysicalType() { 
      return this.physicalType;
    }

    /**
     * @param value {@link #physicalType} (Physical form of the location, e.g. building, room, vehicle, road.)
     */
    public Location setPhysicalType(CodeableConcept value) { 
      this.physicalType = value;
      return this;
    }

    /**
     * @return {@link #position} (The absolute geographic location of the Location, expressed in a KML compatible manner (see notes below for KML).)
     */
    public LocationPositionComponent getPosition() { 
      return this.position;
    }

    /**
     * @param value {@link #position} (The absolute geographic location of the Location, expressed in a KML compatible manner (see notes below for KML).)
     */
    public Location setPosition(LocationPositionComponent value) { 
      this.position = value;
      return this;
    }

    /**
     * @return {@link #managingOrganization} (The organization that is responsible for the provisioning and upkeep of the location.)
     */
    public ResourceReference getManagingOrganization() { 
      return this.managingOrganization;
    }

    /**
     * @param value {@link #managingOrganization} (The organization that is responsible for the provisioning and upkeep of the location.)
     */
    public Location setManagingOrganization(ResourceReference value) { 
      this.managingOrganization = value;
      return this;
    }

    /**
     * @return {@link #managingOrganization} (The actual object that is the target of the reference. The organization that is responsible for the provisioning and upkeep of the location.)
     */
    public Organization getManagingOrganizationTarget() { 
      return this.managingOrganizationTarget;
    }

    /**
     * @param value {@link #managingOrganization} (The actual object that is the target of the reference. The organization that is responsible for the provisioning and upkeep of the location.)
     */
    public Location setManagingOrganizationTarget(Organization value) { 
      this.managingOrganizationTarget = value;
      return this;
    }

    /**
     * @return {@link #status} (active | suspended | inactive.)
     */
    public Enumeration<LocationStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (active | suspended | inactive.)
     */
    public Location setStatus(Enumeration<LocationStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return active | suspended | inactive.
     */
    public LocationStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value active | suspended | inactive.
     */
    public Location setStatusSimple(LocationStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<LocationStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #partOf} (Another Location which this Location is physically part of.)
     */
    public ResourceReference getPartOf() { 
      return this.partOf;
    }

    /**
     * @param value {@link #partOf} (Another Location which this Location is physically part of.)
     */
    public Location setPartOf(ResourceReference value) { 
      this.partOf = value;
      return this;
    }

    /**
     * @return {@link #partOf} (The actual object that is the target of the reference. Another Location which this Location is physically part of.)
     */
    public Location getPartOfTarget() { 
      return this.partOfTarget;
    }

    /**
     * @param value {@link #partOf} (The actual object that is the target of the reference. Another Location which this Location is physically part of.)
     */
    public Location setPartOfTarget(Location value) { 
      this.partOfTarget = value;
      return this;
    }

    /**
     * @return {@link #mode} (Indicates whether a resource instance represents a specific location or a class of locations.)
     */
    public Enumeration<LocationMode> getMode() { 
      return this.mode;
    }

    /**
     * @param value {@link #mode} (Indicates whether a resource instance represents a specific location or a class of locations.)
     */
    public Location setMode(Enumeration<LocationMode> value) { 
      this.mode = value;
      return this;
    }

    /**
     * @return Indicates whether a resource instance represents a specific location or a class of locations.
     */
    public LocationMode getModeSimple() { 
      return this.mode == null ? null : this.mode.getValue();
    }

    /**
     * @param value Indicates whether a resource instance represents a specific location or a class of locations.
     */
    public Location setModeSimple(LocationMode value) { 
      if (value == null)
        this.mode = null;
      else {
        if (this.mode == null)
          this.mode = new Enumeration<LocationMode>();
        this.mode.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Unique code or number identifying the location to its users.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "string", "Name of the location as used by humans. Does not need to be unique.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("description", "string", "Description of the Location, which helps in finding or referencing the place.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("type", "CodeableConcept", "Indicates the type of function performed at the location.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("telecom", "Contact", "The contact details of communication devices available at the location. This can include phone numbers, fax numbers, mobile numbers, email addresses and web sites.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("address", "Address", "Physical location.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("physicalType", "CodeableConcept", "Physical form of the location, e.g. building, room, vehicle, road.", 0, java.lang.Integer.MAX_VALUE, physicalType));
        childrenList.add(new Property("position", "", "The absolute geographic location of the Location, expressed in a KML compatible manner (see notes below for KML).", 0, java.lang.Integer.MAX_VALUE, position));
        childrenList.add(new Property("managingOrganization", "Resource(Organization)", "The organization that is responsible for the provisioning and upkeep of the location.", 0, java.lang.Integer.MAX_VALUE, managingOrganization));
        childrenList.add(new Property("status", "code", "active | suspended | inactive.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("partOf", "Resource(Location)", "Another Location which this Location is physically part of.", 0, java.lang.Integer.MAX_VALUE, partOf));
        childrenList.add(new Property("mode", "code", "Indicates whether a resource instance represents a specific location or a class of locations.", 0, java.lang.Integer.MAX_VALUE, mode));
      }

      public Location copy() {
        Location dst = new Location();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.name = name == null ? null : name.copy();
        dst.description = description == null ? null : description.copy();
        dst.type = type == null ? null : type.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.address = address == null ? null : address.copy();
        dst.physicalType = physicalType == null ? null : physicalType.copy();
        dst.position = position == null ? null : position.copy();
        dst.managingOrganization = managingOrganization == null ? null : managingOrganization.copy();
        dst.status = status == null ? null : status.copy();
        dst.partOf = partOf == null ? null : partOf.copy();
        dst.mode = mode == null ? null : mode.copy();
        return dst;
      }

      protected Location typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Location;
   }


}

