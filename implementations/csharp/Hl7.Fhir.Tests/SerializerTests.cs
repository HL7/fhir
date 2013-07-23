﻿using System;
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
        public void MilisecondsOnInstant()
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
                    Period = new Period() { StartElement = new FhirDateTime(2001, 1, 2), 
                        EndElement = new FhirDateTime(2010, 3, 4) },
                    Assigner = new ResourceReference { Type = "Organization", 
                                    Reference = "organization/@123",
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
        public void NativelySerializedElements()
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
                    Extension = new List<Extension>()
                        {
                            new Extension()
                            {
                                Value = new Coding() { Code = "R51", System = new Uri("http://hl7.org/fhir/sid/icd-10") } 
                            }
                        }
                };

            Assert.AreEqual(@"<?xml version=""1.0"" encoding=""utf-16""?>" +
                @"<element xmlns=""http://hl7.org/fhir"">" +
                @"<extension><valueCoding><system value=""http://hl7.org/fhir/sid/icd-10"" /><code value=""R51"" /></valueCoding></extension>" +                
                @"<url value=""http://hl7.org/fhir/profiles/@3141#test"" />" +
                @"<valueBoolean value=""true"" />" +
                @"</element>", FhirSerializer.SerializeElementAsXml(ext));
            Assert.AreEqual(
                @"{" +
                @"""extension"":[{""valueCoding"":{""system"":{""value"":""http://hl7.org/fhir/sid/icd-10""},""code"":{""value"":""R51""}}}]," +
                @"""url"":{""value"":""http://hl7.org/fhir/profiles/@3141#test""}," +  
                @"""valueBoolean"":{""value"":true}" +
                @"}", FhirSerializer.SerializeElementAsJson(ext));
        }



        [TestMethod]
        public void LetsDoJson()
        {
            string xmlString =
               @"<Patient xmlns='http://hl7.org/fhir'>
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
                    <text>
                        <status value='generated' />
                        <div xmlns='http://www.w3.org/1999/xhtml'>Whatever</div>
                    </text>
                </Patient>";

            ErrorList list = new ErrorList();
            Patient p = (Patient)FhirParser.ParseResourceFromXml(xmlString, list);
            p.Name[0].GivenElement[0].Value = "Rex";
            string json = FhirSerializer.SerializeResourceToJson(p);

            Debug.WriteLine(json);
        }

        [TestMethod]
        public void SummaryResource()
        {
            Patient p = new Patient();

            p.Gender = new CodeableConcept("http://system.com/gender", "M");
            p.Communication = new List<CodeableConcept> { 
                            new CodeableConcept("http://system.com/language", "fr") };

            string xml = FhirSerializer.SerializeResourceToXml(p, summary: true);

            var errors = new ErrorList();
            Patient p2 = (Patient)FhirParser.ParseResourceFromXml(xml, errors);

            Assert.AreEqual(0, errors.Count);

            Assert.IsNotNull(p2.Gender);
            Assert.IsNull(p2.Communication);
        }


        [TestMethod]
        public void ResourceWithExtensionAndNarrative()
        {
            HumanName name = new HumanName().WithGiven("Wouter").WithGiven("Gert")
                .AndFamily("van der").AndFamily("Vlies");

            name.FamilyElement[0].AddExtension(new Uri("http://hl7.org/fhir/profile/@iso-21090#name-qualifier"),
                        new Code("VV"));

            Patient p = new Patient()
            {
                LocalId = "Ab4",
                Identifier = new List<Identifier> { new Identifier { Key = "3141" } },
                BirthDateElement = new FhirDateTime(1972, 11, 30),
                Name = new List<HumanName> { name },
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
                @"<name>" +
                    @"<family value=""van der"">" +
                        @"<extension><url value=""http://hl7.org/fhir/profile/@iso-21090#name-qualifier"" /><valueCode value=""VV"" /></extension>" +
                    @"</family><family value=""Vlies"" /><given value=""Wouter"" /><given value=""Gert"" /></name>" +
                    @"<birthDate value=""1972-11-30"" />" +
                @"</Patient>", FhirSerializer.SerializeResourceToXml(p));

            Assert.AreEqual(@"{""Patient"":{""_id"":""Ab4""," +
                 @"""text"":{""status"":{""value"":""generated""},""div"":""<div xmlns='http://www.w3.org/1999/xhtml'>" +
                    @"Patient 3141 - Wouter Gert, nov. 30th, 1972</div>""},"+
                 @"""contained"":[{""List"":{""mode"":{""value"":""snapshot""}}}],"+
                @"""identifier"":[{""key"":{""value"":""3141""}}]," +
                @"""name"":[{""family"":[{""value"":""van der""," +
                    @"""extension"":[{""url"":{""value"":""http://hl7.org/fhir/profile/@iso-21090#name-qualifier""},""valueCode"":{""value"":""VV""}}]}," +
                    @"{""value"":""Vlies""}],""given"":[{""value"":""Wouter""},{""value"":""Gert""}]}],""birthDate"":{""value"":""1972-11-30""}" +
                @"}}", FhirSerializer.SerializeResourceToJson(p));
        }


        [TestMethod]
        public void SerializeAndDeserializeTagList()
        {
            IList<Tag> tl = new List<Tag>();

            tl.Add(new Tag { Label = "No!", Uri = new Uri("http://www.nu.nl/tags") });
            tl.Add(new Tag { Label = "Maybe", Uri = new Uri("http://www.furore.com/tags") });

            string json = FhirSerializer.SerializeTagListToJson(tl);
            Assert.AreEqual(jsonTagList, json);

            string xml = FhirSerializer.SerializeTagListToXml(tl);
            Assert.AreEqual(xmlTagList, xml);

            ErrorList errors = new ErrorList();
            tl = FhirParser.ParseTagListFromXml(xml,errors);
            Assert.IsTrue(errors.Count == 0, errors.ToString());
            verifyTagList(tl);

            tl = FhirParser.ParseTagListFromJson(json, errors);
            Assert.IsTrue(errors.Count == 0, errors.ToString());
            verifyTagList(tl);
        }

        [TestMethod]
        public void CatchTagListParseErrors()
        {
            ErrorList errors = new ErrorList();
            var tl = FhirParser.ParseTagListFromXml(xmlTagListE1, errors);
            Assert.IsTrue(errors.Count != 0, errors.ToString());
            errors.Clear();
            tl = FhirParser.ParseTagListFromXml(xmlTagListE2, errors);
            Assert.IsTrue(errors.Count != 0, errors.ToString());
            errors.Clear();

            tl = FhirParser.ParseTagListFromJson(jsonTagListE1, errors);
            Assert.IsTrue(errors.Count != 0, errors.ToString());
            errors.Clear();
            tl = FhirParser.ParseTagListFromJson(jsonTagListE2, errors);
            Assert.IsTrue(errors.Count != 0, errors.ToString());
            errors.Clear();

        }


        private static void verifyTagList(IList<Tag> tl)
        {
            Assert.AreEqual(2, tl.Count);
            Assert.AreEqual("No!", tl[0].Label);
            Assert.AreEqual("http://www.nu.nl/tags", tl[0].Uri.ToString());
            Assert.AreEqual("Maybe", tl[1].Label);
            Assert.AreEqual("http://www.furore.com/tags", tl[1].Uri.ToString());
        }

        private string jsonTagList = @"{""taglist"":{""category"":[" +
            @"{""term"":""http://www.nu.nl/tags"",""label"":""No!"",""scheme"":""http://hl7.org/fhir/tag""}," +
            @"{""term"":""http://www.furore.com/tags"",""label"":""Maybe"",""scheme"":""http://hl7.org/fhir/tag""}]}}";

        private string jsonTagListE1 = @"{""Xtaglist"":{""category"":[" +
            @"{""term"":""http://www.nu.nl/tags"",""label"":""No!"",""scheme"":""http://hl7.org/fhir/tag""}," +
            @"{""term"":""http://www.furore.com/tags"",""label"":""Maybe"",""scheme"":""http://hl7.org/fhir/tag""}]}}";
        private string jsonTagListE2 = @"{""taglist"":{""Xcategory"":[" +
            @"{""term"":""http://www.nu.nl/tags"",""label"":""No!"",""scheme"":""http://hl7.org/fhir/tag""}," +
            @"{""term"":""http://www.furore.com/tags"",""label"":""Maybe"",""scheme"":""http://hl7.org/fhir/tag""}]}}";


        private string xmlTagList = @"<?xml version=""1.0"" encoding=""utf-16""?><taglist xmlns=""http://hl7.org/fhir"">" +
            @"<category term=""http://www.nu.nl/tags"" label=""No!"" scheme=""http://hl7.org/fhir/tag"" />" +
            @"<category term=""http://www.furore.com/tags"" label=""Maybe"" scheme=""http://hl7.org/fhir/tag"" /></taglist>";
        private string xmlTagListE1 = @"<?xml version=""1.0"" encoding=""utf-16""?><Xtaglist xmlns=""http://hl7.org/fhir"">" +
            @"<category term=""http://www.nu.nl/tags"" label=""No!"" scheme=""http://hl7.org/fhir/tag"" />" +
            @"<category term=""http://www.furore.com/tags"" label=""Maybe"" scheme=""http://hl7.org/fhir/tag"" /></taglist>";
        private string xmlTagListE2 = @"<?xml version=""1.0"" encoding=""utf-16""?><taglist xmlns=""http://hl7.org/fhir"">" +
            @"<category term=""http://www.nu.nl/tags"" label=""No!"" scheme=""http://hl7.org/fhir/tag"" />" +
            @"<categoryX term=""http://www.furore.com/tags"" label=""Maybe"" scheme=""http://hl7.org/fhir/tag"" /></taglist>";

    }
}
