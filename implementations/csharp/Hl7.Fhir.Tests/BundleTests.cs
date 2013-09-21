﻿using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System.IO;
using Hl7.Fhir.Support;
using Hl7.Fhir.Serializers;
using System.Text.RegularExpressions;
using System.Xml;
using Hl7.Fhir.Model;
using Hl7.Fhir.Parsers;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class BundleTests
    {
        [TestMethod]
        public void SerializeBundleXml()
        {
            Bundle b = createTestBundle();
            var actual = FhirSerializer.SerializeBundleToXml(b);

            File.WriteAllText("c:\\temp\\bundleE.xml", testBundleAsXml);
            File.WriteAllText("c:\\temp\\bundleA.xml", actual);

            Assert.AreEqual(testBundleAsXml, actual);
        }

        [TestMethod]
        public void SerializeResourceEntryXml()
        {
            var re = createTestResourceEntry();
            var actual = FhirSerializer.SerializeBundleEntryToXml(re);
            actual = cleanupXml(actual);

            File.WriteAllText("c:\\temp\\rentryE.xml", testResourceEntryAsXml);
            File.WriteAllText("c:\\temp\\rentryA.xml", actual);

            Assert.AreEqual(testResourceEntryAsXml, actual);
        }

        [TestMethod]
        public void SerializeDeletedEntryXml()
        {
            var re = createTestDeletedEntry();
            var actual = FhirSerializer.SerializeBundleEntryToXml(re);
            actual = cleanupXml(actual);

            File.WriteAllText("c:\\temp\\dentryE.xml", testDeletedEntryAsXml);
            File.WriteAllText("c:\\temp\\dentryA.xml", actual);

            Assert.AreEqual(testDeletedEntryAsXml, actual);
        }

        [TestMethod]
        public void SerializeBinaryEntryXml()
        {
            var re = createTestBinaryEntry();
            var actual = FhirSerializer.SerializeBundleEntryToXml(re);
            actual = cleanupXml(actual);

            File.WriteAllText("c:\\temp\\bentryE.xml", testBinaryEntryAsXml);
            File.WriteAllText("c:\\temp\\bentryA.xml", actual);

            Assert.AreEqual(testBinaryEntryAsXml, actual);
        }

        [TestMethod]
        public void SerializeResourceEntryJson()
        {
            var re = createTestResourceEntry();
            var actual = FhirSerializer.SerializeBundleEntryToJson(re);

            File.WriteAllText("c:\\temp\\rentryE.json", testResourceEntryAsJson);
            File.WriteAllText("c:\\temp\\rentryA.json", actual);

            Assert.AreEqual(testResourceEntryAsJson, actual);
        }

        [TestMethod]
        public void SerializeDeletedEntryJson()
        {
            var re = createTestDeletedEntry();
            var actual = FhirSerializer.SerializeBundleEntryToJson(re);

            File.WriteAllText("c:\\temp\\dentryE.json", testDeletedEntryAsJson);
            File.WriteAllText("c:\\temp\\dentryA.json", actual);

            Assert.AreEqual(testDeletedEntryAsJson, actual);
        }

        [TestMethod]
        public void SerializeBinaryEntryJson()
        {
            var re = createTestBinaryEntry();
            var actual = FhirSerializer.SerializeBundleEntryToJson(re);
 
            File.WriteAllText("c:\\temp\\bentryE.json", testBinaryEntryAsJson);
            File.WriteAllText("c:\\temp\\bentryA.json", actual);

            Assert.AreEqual(testBinaryEntryAsJson, actual);
        }

        private string cleanupXml(string xml)
        {
            xml = xml.Replace("<?xml version=\"1.0\" encoding=\"utf-16\"?>", "");
            xml = xml.Replace("entry xmlns=\"http://www.w3.org/2005/Atom\"", "entry");
            return xml;
        }

        [TestMethod]
        public void SerializeBundleJson()
        {
            Bundle b = createTestBundle();
            var actual = FhirSerializer.SerializeBundleToJson(b);

            File.WriteAllText("c:\\temp\\bundleE.json", testBundleAsJson);
            File.WriteAllText("c:\\temp\\bundleA.json", actual);

            Assert.AreEqual(testBundleAsJson, actual);
        }


        [TestMethod]
        public void HandleCrapInBundle()
        {
            var errors = new ErrorList();
            FhirParser.ParseBundleFromJson("Crap!", errors);
            Assert.IsTrue(errors.Count > 0);

            errors.Clear();
            FhirParser.ParseBundleFromJson("{ \" Crap!", errors);
            Assert.IsTrue(errors.Count > 0);

            errors.Clear();
            FhirParser.ParseBundleFromJson("Crap", errors);
            Assert.IsTrue(errors.Count > 0);

            errors.Clear();
            FhirParser.ParseBundleFromJson("<Crap><cra", errors);
            Assert.IsTrue(errors.Count > 0);
        }


        private static void checkSerDeser(BundleEntry be)
        {
            var errs = new ErrorList();

            FhirParser.ParseBundleEntryFromJson(FhirSerializer.SerializeBundleEntryToJson(be), errs);
            Assert.IsTrue(errs.Count == 0);

            FhirParser.ParseBundleEntryFromXml(FhirSerializer.SerializeBundleEntryToXml(be), errs);
            Assert.IsTrue(errs.Count == 0);
        }

        [TestMethod]
        public void ParseBundleXml()
        {
            ErrorList errors = new ErrorList();

            Bundle result = FhirParser.ParseBundle(Util.XmlReaderFromString(testBundleAsXml), errors);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            var actual = FhirSerializer.SerializeBundleToXml(result);        

            Assert.AreEqual(testBundleAsXml, actual);
        }


        [TestMethod]
        public void ParseBundleResourceEntryXml()
        {
            ErrorList errors = new ErrorList();

            var input = markupXml(testResourceEntryAsXml);
            var result = FhirParser.ParseBundleEntry(Util.XmlReaderFromString(input), errors);

            Assert.IsTrue(result is ResourceEntry<Patient>);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);
           
            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToXml(result));
        }

        [TestMethod]
        public void ParseBundleDeletedEntryXml()
        {
            ErrorList errors = new ErrorList();

            var input = markupXml(testDeletedEntryAsXml);
            var result = FhirParser.ParseBundleEntry(Util.XmlReaderFromString(input), errors);

            Assert.IsTrue(result is DeletedEntry);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToXml(result));
        }

        [TestMethod]
        public void ParseBundleBinaryEntryXml()
        {
            ErrorList errors = new ErrorList();

            var input = markupXml(testBinaryEntryAsXml);
            var result = FhirParser.ParseBundleEntry(Util.XmlReaderFromString(input), errors);

            Assert.IsTrue(result is ResourceEntry<Binary>);


            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToXml(result));
        }

        [TestMethod]
        public void ParseBundleResourceEntryJson()
        {
            ErrorList errors = new ErrorList();

            var input = testResourceEntryAsJson;
            var result = FhirParser.ParseBundleEntry(Util.JsonReaderFromString(input), errors);

            Assert.IsTrue(result is ResourceEntry<Patient>);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToJson(result));
        }

        [TestMethod]
        public void ParseBundleDeletedEntryJson()
        {
            ErrorList errors = new ErrorList();

            var input = testDeletedEntryAsJson;
            var result = FhirParser.ParseBundleEntry(Util.JsonReaderFromString(input), errors);
            Assert.IsTrue(result is DeletedEntry);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToJson(result));
        }

        [TestMethod]
        public void ParseBundleBinaryEntryJson()
        {
            ErrorList errors = new ErrorList();

            var input = testBinaryEntryAsJson;
            var result = FhirParser.ParseBundleEntry(Util.JsonReaderFromString(input), errors);
            Assert.IsTrue(result is ResourceEntry<Binary>);

            Assert.IsNotNull(result);
            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            Assert.AreEqual(input, FhirSerializer.SerializeBundleEntryToJson(result));
        }

        private static string markupXml(string xml)
        {
            xml = "<?xml version=\"1.0\" encoding=\"utf-16\"?>" + xml;
            xml = xml.Replace("<entry", "<entry xmlns=\"http://www.w3.org/2005/Atom\"");
            return xml;
        }


        [TestMethod]
        public void ParseBundleJson()
        {
            ErrorList errors = new ErrorList();

            Bundle result = FhirParser.ParseBundle(new JsonTextReader(new StringReader(testBundleAsJson)), errors);

            Assert.AreEqual(0, errors.Count, errors.Count > 0 ? errors.ToString() : null);

            var actual = FhirSerializer.SerializeBundleToJson(result);

            Assert.AreEqual(testBundleAsJson, actual);
        }


        private static string testResourceEntryAsXml =
           @"<entry><title type=""text"">Resource 233 Version 1</title>" +
           @"<id>http://test.com/fhir/patient/@233</id><updated>2012-11-01T13:04:14Z</updated><published>2012-11-02T14:17:21Z</published>" +
           @"<author><name>110.143.187.242</name></author>" +
           @"<link rel=""self"" href=""http://test.com/fhir/patient/@233/history/@1"" />" +
           @"<link rel=""search"" href=""http://test.com/fhir/patient/search?name=Kramer"" />" +
           @"<category term=""http://test.com/tag/test"" label=""YES"" scheme=""http://hl7.org/fhir/tag"" />" +
           @"<content type=""text/xml"">" +
           @"<Patient xmlns=""http://hl7.org/fhir""><text><status value=""generated"" /><div xmlns=""http://www.w3.org/1999/xhtml"">summary here</div>" +
           @"</text></Patient></content><summary type=""xhtml""><div xmlns=""http://www.w3.org/1999/xhtml"">summary here</div></summary></entry>";

        private static string testDeletedEntryAsXml =
           @"<deleted-entry ref=""http://test.com/fhir/patient/@233"" when=""2012-11-01T13:15:30Z"" xmlns=""http://purl.org/atompub/tombstones/1.0"">" +
           @"<link rel=""self"" href=""http://test.com/fhir/patient/@233/history/@2"" xmlns=""http://www.w3.org/2005/Atom"" /></deleted-entry>";

        private static string testBinaryEntryAsXml =
           @"<entry>" +
           @"<title type=""text"">Resource 99 Version 1</title>" +
           @"<id>http://test.com/fhir/binary/@99</id><updated>2012-10-31T13:04:14Z</updated><published>2012-11-02T14:17:21Z</published>" +
           @"<link rel=""self"" href=""http://test.com/fhir/binary/@99/history/@1"" />" +
           @"<content type=""text/xml"">" +
           @"<Binary contentType=""application/x-test"" xmlns=""http://hl7.org/fhir"">AAECAw==</Binary></content><summary type=""xhtml""><div xmlns=""http://www.w3.org/1999/xhtml"">" +
           @"Binary content (mediatype application/x-test)</div></summary></entry>";

        private static string testBundleAsXml =
           @"<?xml version=""1.0"" encoding=""utf-16""?><feed xmlns=""http://www.w3.org/2005/Atom"">" +
           @"<title type=""text"">Updates to resource 233</title>" +
           @"<id>urn:uuid:0d0dcca9-23b9-4149-8619-65002224c3</id><updated>2012-11-02T14:17:21Z</updated>" +
           @"<author><name>Ewout Kramer</name></author>" +
           @"<totalResults xmlns=""http://a9.com/-/spec/opensearch/1.1/"">20</totalResults>" +
           @"<link rel=""self"" href=""http://test.com/fhir/patient/@233/history?format=json"" />" +
           @"<link rel=""last"" href=""http://test.com/fhir/patient/@233"" />" +
           testResourceEntryAsXml +
           testDeletedEntryAsXml +
           testBinaryEntryAsXml +
           @"</feed>";


        private static string testDeletedEntryAsJson =
            @"{""deleted"":""2012-11-01T13:15:30+00:00"",""id"":""http://test.com/fhir/patient/@233""," +
            @"""link"":[{""rel"":""self"",""href"":""http://test.com/fhir/patient/@233/history/@2""}]}";

        private static string testResourceEntryAsJson =
            @"{""title"":""Resource 233 Version 1"",""id"":""http://test.com/fhir/patient/@233"",""updated"":""2012-11-01T13:04:14+00:00""," +
            @"""published"":""2012-11-02T14:17:21+00:00"",""author"":[{""name"":""110.143.187.242""}]," +
            @"""link"":[{""rel"":""self"",""href"":""http://test.com/fhir/patient/@233/history/@1""}," +
            @"{""rel"":""search"",""href"":""http://test.com/fhir/patient/search?name=Kramer""}]," +
            @"""category"":[{""term"":""http://test.com/tag/test"",""label"":""YES"",""scheme"":""http://hl7.org/fhir/tag""}]," +
            @"""content"":{""Patient"":{""text"":{""status"":{""value"":""generated""},""div"":" +
            @"""<div xmlns=\""http://www.w3.org/1999/xhtml\"">summary here</div>""}}}," +
            @"""summary"":""<div xmlns=\""http://www.w3.org/1999/xhtml\"">summary here</div>""" +
            @"}";

        private static string testBinaryEntryAsJson =
            @"{""title"":""Resource 99 Version 1"",""id"":""http://test.com/fhir/binary/@99"",""updated"":""2012-10-31T13:04:14+00:00""," +
            @"""published"":""2012-11-02T14:17:21+00:00""," +
            @"""link"":[{""rel"":""self"",""href"":""http://test.com/fhir/binary/@99/history/@1""}]," +
            @"""content"":{""Binary"":{""contentType"":""application/x-test"",""content"":""AAECAw==""}}," +
            @"""summary"":""<div xmlns='http://www.w3.org/1999/xhtml'>Binary content (mediatype application/x-test)</div>""" +
            @"}";

        private string testBundleAsJson =
            @"{""title"":""Updates to resource 233""," +
            @"""id"":""urn:uuid:0d0dcca9-23b9-4149-8619-65002224c3""," +
            @"""updated"":""2012-11-02T14:17:21+00:00""," +
            @"""author"":[{""name"":""Ewout Kramer""}]," +
            @"""totalResults"":""20""," +
            @"""link"":[{""rel"":""self"",""href"":""http://test.com/fhir/patient/@233/history?format=json""}," +
            @"{""rel"":""last"",""href"":""http://test.com/fhir/patient/@233""}]," +

            @"""entry"":[" +
            testResourceEntryAsJson + "," +           
            testDeletedEntryAsJson + "," +
            testBinaryEntryAsJson +
            @"]}";



        private static Bundle createTestBundle()
        {
            Bundle b = new Bundle();

            b.Title = "Updates to resource 233";
            b.Id = new Uri("urn:uuid:0d0dcca9-23b9-4149-8619-65002224c3");
            b.LastUpdated = new DateTimeOffset(2012, 11, 2, 14, 17, 21, TimeSpan.Zero);
            b.AuthorName = "Ewout Kramer";
            b.TotalResults = 20;
            b.Links.SelfLink = new Uri("http://test.com/fhir/patient/@233/history?format=json");
            b.Links.LastLink = new Uri("http://test.com/fhir/patient/@233");

            ResourceEntry e1 = createTestResourceEntry();
            DeletedEntry e2 = createTestDeletedEntry();
            ResourceEntry e3 = createTestBinaryEntry();

            b.Entries.Add(e1);
            b.Entries.Add(e2);
            b.Entries.Add(e3);

            return b;
        }

        private static ResourceEntry createTestBinaryEntry()
        {
            var e3 = new ResourceEntry<Binary>();
            e3.Id = new Uri("http://test.com/fhir/binary/@99");
            e3.Title = "Resource 99 Version 1";
            e3.Links.SelfLink = new Uri("http://test.com/fhir/binary/@99/history/@1");
            e3.LastUpdated = new DateTimeOffset(2012, 10, 31, 13, 04, 14, TimeSpan.Zero);
            e3.Published = new DateTimeOffset(2012, 11, 2, 14, 17, 21, TimeSpan.Zero);
            e3.Resource = new Binary()
            {
                ContentType = "application/x-test",
                Content = new byte[] { 0x00, 0x01, 0x02, 0x03 }
            };
            
            return e3;
        }

        private static DeletedEntry createTestDeletedEntry()
        {
            DeletedEntry e2 = new DeletedEntry();
            e2.Id = new Uri("http://test.com/fhir/patient/@233");
            e2.Links.SelfLink = new Uri("http://test.com/fhir/patient/@233/history/@2");
            e2.When = new DateTimeOffset(2012, 11, 01, 13, 15, 30, TimeSpan.Zero);
            return e2;
        }

        private static ResourceEntry createTestResourceEntry()
        {
            var e1 = new ResourceEntry<Patient>();
            e1.Id = new Uri("http://test.com/fhir/patient/@233");
            e1.Title = "Resource 233 Version 1";
            e1.LastUpdated = new DateTimeOffset(2012, 11, 01, 13, 04, 14, TimeSpan.Zero);
            e1.Published = new DateTimeOffset(2012, 11, 2, 14, 17, 21, TimeSpan.Zero);
            e1.AuthorName = "110.143.187.242";
            e1.Links.SelfLink = new Uri("http://test.com/fhir/patient/@233/history/@1");
            e1.Links.SearchLink = new Uri("http://test.com/fhir/patient/search?name=Kramer");
            e1.Tags = new List<Tag>() { new Tag("http://test.com/tag/test", Tag.FHIRTAGSCHEME, "YES") };

            e1.Resource = new Model.Patient()
            {
                Text =
                  new Model.Narrative()
                  {
                      Status = Model.Narrative.NarrativeStatus.Generated,
                      Div = "<div xmlns=\"http://www.w3.org/1999/xhtml\">summary here</div>"
                  }
            };

            return e1;
        }
    }
}
