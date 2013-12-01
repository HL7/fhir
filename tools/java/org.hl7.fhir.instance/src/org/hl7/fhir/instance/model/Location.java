package org.hl7.fhir.instance.model;

/*
  Copyright (c) 2011-2013, HL7, Inc.
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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

import java.math.*;
/**
 * Details and position information for a physical place where services are provided  and resources and participants may be stored, found, contained or accommodated.
 */
public class Location extends Resource {

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

      public LocationPositionComponent copy(Location e) {
        LocationPositionComponent dst = new LocationPositionComponent();
        dst.longitude = longitude == null ? null : longitude.copy();
        dst.latitude = latitude == null ? null : latitude.copy();
        dst.altitude = altitude == null ? null : altitude.copy();
        return dst;
      }

  }

    /**
     * Name of the location which identifies it to its users.
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
     * The contact details of the main communication devices present at the location.
     */
    protected Contact telecom;

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
     * Whether the location is still used to provide services.
     */
    protected Boolean active;

    /**
     * Another Location which this Location is physically part of.
     */
    protected ResourceReference partOf;

    /**
     * Indicates whether a resource instance represents a specific location or a class of locations.
     */
    protected Enumeration<LocationMode> mode;

    public Location() {
      super();
    }

    public Location(String_ name) {
      super();
      this.name = name;
    }

    /**
     * @return {@link #name} (Name of the location which identifies it to its users.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (Name of the location which identifies it to its users.)
     */
    public Location setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return Name of the location which identifies it to its users.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value Name of the location which identifies it to its users.
     */
    public Location setNameSimple(String value) { 
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
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
     * @return {@link #telecom} (The contact details of the main communication devices present at the location.)
     */
    public Contact getTelecom() { 
      return this.telecom;
    }

    /**
     * @param value {@link #telecom} (The contact details of the main communication devices present at the location.)
     */
    public Location setTelecom(Contact value) { 
      this.telecom = value;
      return this;
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
     * @return {@link #active} (Whether the location is still used to provide services.)
     */
    public Boolean getActive() { 
      return this.active;
    }

    /**
     * @param value {@link #active} (Whether the location is still used to provide services.)
     */
    public Location setActive(Boolean value) { 
      this.active = value;
      return this;
    }

    /**
     * @return Whether the location is still used to provide services.
     */
    public boolean getActiveSimple() { 
      return this.active == null ? null : this.active.getValue();
    }

    /**
     * @param value Whether the location is still used to provide services.
     */
    public Location setActiveSimple(boolean value) { 
      if (value == false)
        this.active = null;
      else {
        if (this.active == null)
          this.active = new Boolean();
        this.active.setValue(value);
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
        childrenList.add(new Property("name", "string", "Name of the location which identifies it to its users.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("description", "string", "Description of the Location, which helps in finding or referencing the place.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("type", "CodeableConcept", "Indicates the type of function performed at the location.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("telecom", "Contact", "The contact details of the main communication devices present at the location.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("address", "Address", "Physical location.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("physicalType", "CodeableConcept", "Physical form of the location, e.g. building, room, vehicle, road.", 0, java.lang.Integer.MAX_VALUE, physicalType));
        childrenList.add(new Property("position", "", "The absolute geographic location of the Location, expressed in a KML compatible manner (see notes below for KML).", 0, java.lang.Integer.MAX_VALUE, position));
        childrenList.add(new Property("managingOrganization", "Resource(Organization)", "The organization that is responsible for the provisioning and upkeep of the location.", 0, java.lang.Integer.MAX_VALUE, managingOrganization));
        childrenList.add(new Property("active", "boolean", "Whether the location is still used to provide services.", 0, java.lang.Integer.MAX_VALUE, active));
        childrenList.add(new Property("partOf", "Resource(Location)", "Another Location which this Location is physically part of.", 0, java.lang.Integer.MAX_VALUE, partOf));
        childrenList.add(new Property("mode", "code", "Indicates whether a resource instance represents a specific location or a class of locations.", 0, java.lang.Integer.MAX_VALUE, mode));
      }

      public Location copy() {
        Location dst = new Location();
        dst.name = name == null ? null : name.copy();
        dst.description = description == null ? null : description.copy();
        dst.type = type == null ? null : type.copy();
        dst.telecom = telecom == null ? null : telecom.copy();
        dst.address = address == null ? null : address.copy();
        dst.physicalType = physicalType == null ? null : physicalType.copy();
        dst.position = position == null ? null : position.copy(dst);
        dst.managingOrganization = managingOrganization == null ? null : managingOrganization.copy();
        dst.active = active == null ? null : active.copy();
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

