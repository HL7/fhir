using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json;
using System.IO;
using Hl7.Fhir.Model;
using Hl7.Fhir.Serializers;
using System.Xml.Linq;
using System.Xml;
using Hl7.Fhir.Support;
using Hl7.Fhir.Parsers;
using Hl7.Fhir.Client;
using System.Diagnostics;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class SerializerTests
    {
        [TestMethod]
        public void AvoidBOMUse()
        {
            Bundle b = new Bundle();

            var data = FhirSerializer.SerializeBundleToJsonBytes(b);
            Assert.IsFalse(data[0] == Encoding.UTF8.GetPreamble()[0]);

            data = FhirSerializer.SerializeBundleToXmlBytes(b);
            Assert.IsFalse(data[0] == Encoding.UTF8.GetPreamble()[0]);

            Patient p = new Patient();

            data = FhirSerializer.SerializeResourceToJsonBytes(p);
            Assert.IsFalse(data[0] == Encoding.UTF8.GetPreamble()[0]);

            data = FhirSerializer.SerializeResourceToXmlBytes(p);
            Assert.IsFalse(data[0] == Encoding.UTF8.GetPreamble()[0]);
        }

        [TestMethod]
        public void TestMilisecondsOnInstant()
        {
            Instant i = new Instant(new DateTimeOffset(2013, 4, 19, 16, 27, 23, 233, TimeSpan.Zero));

            Assert.IsTrue(i.Value.Value.Millisecond > 0);

            string xml = FhirSerializer.SerializeElementAsXml(i, "someInstant");

            ErrorList dummy = new ErrorList();
            Instant j = (Instant)FhirParser.ParseElementFromXml(xml, dummy);

            Assert.AreEqual(0, dummy.Count);
            Assert.AreEqual(233, j.Value.Value.Millisecond);

            Instant result;
            bool succ = Instant.TryParse("2013-04-19T16:17:23Z", out result);
            Assert.IsTrue(succ);

            xml = FhirSerializer.SerializeElementAsXml(result, "someInstant");
            Assert.IsFalse(xml.Contains("16:27:23.0"));
        }

        [TestMethod]
        public void SerializeElement()
        {
            Identifier id = new Identifier
                {
                    LocalId = "3141",
                    Use = Identifier.IdentifierUse.Official,
                    Label = "SSN",
                    System = new Uri("http://hl7.org/fhir/sid/us-ssn"),
                    Key = "000111111",
                    Period = new Period() { Start = new FhirDateTime(2001, 1, 2), End = new FhirDateTime(2010, 3, 4) },
                    Assigner = new ResourceReference { Type = "Organization", Reference = "organization/@123",
                                        Display = "HL7, Inc" }
                };


            Assert.AreEqual(@"<?xml version=""1.0"" encoding=""utf-16""?>" +
                    @"<element id=""3141"" xmlns=""http://hl7.org/fhir"">" +
                        @"<use value=""official"" />" +
                        @"<label value=""SSN"" />" +
                        @"<system value=""http://hl7.org/fhir/sid/us-ssn"" />" +
                        @"<key value=""000111111"" />" +
                        @"<period><start value=""2001-01-02"" /><end value=""2010-03-04"" /></period>" +
                        @"<assigner><type value=""Organization"" /><reference value=""organization/@123"" /><display value=""HL7, Inc"" /></assigner>" +
                     @"</element>", FhirSerializer.SerializeElementAsXml(id));

            Assert.AreEqual(
                @"{""_id"":""3141"",""use"":{""value"":""official""},""label"":{""value"":""SSN""}," +
                @"""system"":{""value"":""http://hl7.org/fhir/sid/us-ssn""},""key"":{""value"":""000111111""}," +
                @"""period"":{""start"":{""value"":""2001-01-02""},""end"":{""value"":""2010-03-04""}}," +
                @"""assigner"":{""type"":{""value"":""Organization""},""reference"":{""value"":""organization/@123""}," +
                @"""display"":{""value"":""HL7, Inc""}}}", FhirSerializer.SerializeElementAsJson(id));
        }


        [TestMethod]
        public void TestNativelySerializedElements()
        {
            Integer i = new Integer(3141);
            var json = FhirSerializer.SerializeElementAsJson(i);
            Assert.AreEqual("{\"value\":3141}", json);

            FhirBoolean b = new FhirBoolean(false);
            json = FhirSerializer.SerializeElementAsJson(b);
            Assert.AreEqual("{\"value\":false}", json);
        }

        [TestMethod]
        public void PolymorphAndArraySerialization()
        {
            Extension ext = new Extension()
                {
                    Url = new Uri("http://hl7.org/fhir/profiles/@3141#test"),
                    Value = new FhirBoolean(true),
                    NestedExtension = new List<Extension>()
                        {
                            new Extension()
                            {
                                Value = new Coding() { Code = "R51", System = new Uri("http://hl7.org/fhir/sid/icd-10") } 
                            }
                        }
                };

            Assert.AreEqual(@"<?xml version=""1.0"" encoding=""utf-16""?>" +
                @"<element xmlns=""http://hl7.org/fhir""><url value=""http://hl7.org/fhir/profiles/@3141#test"" /><valueBoolean value=""true"" />" +
                @"<extension><valueCoding><system value=""http://hl7.org/fhir/sid/icd-10"" /><code value=""R51"" /></valueCoding></extension>" +
                @"</element>", FhirSerializer.SerializeElementAsXml(ext));
            Assert.AreEqual(
                @"{""url"":{""value"":""http://hl7.org/fhir/profiles/@3141#test""},""valueBoolean"":{""value"":true}," +
                @"""extension"":[{""valueCoding"":{""system"":{""value"":""http://hl7.org/fhir/sid/icd-10""},""code"":{""value"":""R51""}}}]}",
                FhirSerializer.SerializeElementAsJson(ext));
        }



        [TestMethod]
        public void LetsDoJson()
        {
            string xmlString =
               @"<Patient xmlns='http://hl7.org/fhir'>
                    <details>
                        <name>
                          <use value='official' />  
                          <given value='Regina' />
                          <prefix value='Dr.'>
                            <extension>
                                <url value='http://hl7.org/fhir/profile/@iso-20190' />
                                <valueCoding>
                                    <system value='urn:oid:2.16.840.1.113883.5.1122' />       
                                    <code value='AC' />
                                </valueCoding>
                            </extension>
                          </prefix>
                        </name>
                    </details>
                    <text>
                        <status value='generated' />
                        <div xmlns='http://www.w3.org/1999/xhtml'>Whatever</div>
                    </text>
                </Patient>";

            ErrorList list = new ErrorList();
            Patient p = (Patient)FhirParser.ParseResourceFromXml(xmlString, list);
            p.Details.Name[0].Given[0].Value = "Rex";
            string json = FhirSerializer.SerializeResourceToJson(p);

            Debug.WriteLine(json);
        }



        [TestMethod]
        public void ResourceWithExtensionAndNarrative()
        {
            Patient p = new Patient()
            {
                LocalId = "Ab4",
                Identifier = new List<Identifier> { new Identifier() { Key = "3141" } },
                Details = new Demographics()
                {
                    BirthDate = new FhirDateTime(1972, 11, 30),
                    Name = new List<HumanName> {
                        new HumanName() { Given = new List<FhirString>() { "Wouter", "Gert" },
                                   Family = new List<FhirString>() { new FhirString() { Value = "van der", 
                                        Extension = new List<Extension> { new Extension 
                                                        { Url= new Uri("http://hl7.org/fhir/profile/@iso-21090#name-qualifier"),
                                                            Value = new Code("VV") } } }, "Vlies" } } }
                },

                Text = new Narrative()
                 {
                     Status = Narrative.NarrativeStatus.Generated,
                     Div = "<div xmlns='http://www.w3.org/1999/xhtml'>Patient 3141 - Wouter Gert, nov. 30th, 1972</div>"
                 },

                 Contained = new List<Resource>() { new List() { Mode = List.ListMode.Snapshot } }
            };


            Assert.AreEqual(@"<?xml version=""1.0"" encoding=""utf-16""?>" +
                @"<Patient id=""Ab4"" xmlns=""http://hl7.org/fhir"">" +
                @"<text><status value=""generated"" /><div xmlns='http://www.w3.org/1999/xhtml'>Patient 3141 - Wouter Gert, nov. 30th, 1972</div></text>" +
                @"<contained><List><mode value=""snapshot"" /></List></contained>" +
                @"<identifier><key value=""3141"" /></identifier>" +
                @"<details><name>" +
                    @"<family value=""van der"">" +
                        @"<extension><url value=""http://hl7.org/fhir/profile/@iso-21090#name-qualifier"" /><valueCode value=""VV"" /></extension>" +
                    @"</family><family value=""Vlies"" /><given value=""Wouter"" /><given value=""Gert"" /></name>" +
                    @"<birthDate value=""1972-11-30"" /></details>" +
                @"</Patient>", FhirSerializer.SerializeResourceToXml(p));

            Assert.AreEqual(@"{""Patient"":{""_id"":""Ab4""," +
                 @"""text"":{""status"":{""value"":""generated""},""div"":""<div xmlns='http://www.w3.org/1999/xhtml'>" +
                    @"Patient 3141 - Wouter Gert, nov. 30th, 1972</div>""},"+
                 @"""contained"":[{""List"":{""mode"":{""value"":""snapshot""}}}],"+
                @"""identifier"":[{""key"":{""value"":""3141""}}]," +
                @"""details"":{""name"":[{""family"":[{""value"":""van der""," +
                    @"""extension"":[{""url"":{""value"":""http://hl7.org/fhir/profile/@iso-21090#name-qualifier""},""valueCode"":{""value"":""VV""}}]}," +
                    @"{""value"":""Vlies""}],""given"":[{""value"":""Wouter""},{""value"":""Gert""}]}],""birthDate"":{""value"":""1972-11-30""}}" +
                @"}}", FhirSerializer.SerializeResourceToJson(p));
        }
    }
}
