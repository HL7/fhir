using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.Xml;
using Hl7.Fhir.Client;
using Hl7.Fhir.Model;
using System.Net;
using Hl7.Fhir.Support;
using Hl7.Fhir.Serializers;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class FhirClientTests
    {
        //Uri testEndpoint = new Uri("http://fhir.furore.com/fhir");
        Uri testEndpoint = new Uri("http://hl7connect.healthintersections.com.au/svc/fhir");


        [TestMethod]
        public void TestConformance()
        {
            FhirClient client = new FhirClient(testEndpoint);

            Conformance c = client.Conformance().Content;

            Assert.IsNotNull(c);
            Assert.AreEqual("HL7Connect", c.Software.Name);
            Assert.AreEqual(Conformance.RestfulConformanceMode.Server, c.Rest[0].Mode.Value);
            Assert.AreEqual(ContentType.XML_CONTENT_HEADER, client.LastResponseDetails.ContentType.ToLower());
            Assert.AreEqual(HttpStatusCode.OK, client.LastResponseDetails.Result);
        }


        [TestMethod]
        public void TestRead()
        {
            FhirClient client = new FhirClient(testEndpoint);

            var loc = client.Read<Location>("1");
            Assert.IsNotNull(loc);
            Assert.AreEqual("Utrecht", loc.Content.Address.City);

            string version = new ResourceLocation(loc.SelfLink).VersionId;               
            Assert.AreEqual("1", version);

            string id = new ResourceLocation(loc.Id).Id;
            Assert.AreEqual("1", id);

            try
            {
                var random = client.Read<Location>("45qq54");
                Assert.Fail();
            }
            catch (FhirOperationException)
            {
                Assert.IsTrue(client.LastResponseDetails.Result == HttpStatusCode.NotFound);
            }

            var loc2 = client.VRead<Location>("1", version);
            Assert.IsNotNull(loc2);
            Assert.AreEqual(FhirSerializer.SerializeBundleEntryToJson(loc),
                            FhirSerializer.SerializeBundleEntryToJson(loc2));
        }


        [TestMethod]
        public void TestSearch()
        {
            FhirClient client = new FhirClient(testEndpoint);
            Bundle result;

            result = client.SearchAll<Patient>();
            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries.Count > 0);
            Assert.IsTrue(result.Entries[0].Id.ToString().EndsWith("@1"));

            result = client.SearchAll<Patient>(10);
            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries.Count <= 10);
            Assert.IsTrue(result.Entries[0].Id.ToString().EndsWith("@1"));

            result = client.SearchById<DiagnosticReport>("101", "DiagnosticReport/subject");
            Assert.IsNotNull(result);

            Assert.AreEqual(1,
                    result.Entries.Where(entry => entry.Links.SelfLink.ToString()
                        .Contains("diagnosticreport")).Count());

            Assert.IsTrue(result.Entries.Any(entry =>
                    entry.Links.SelfLink.ToString().Contains("patient/@pat2")));

            result = client.Search<Patient>( new string[] { "name", "Everywoman",   "name", "Eve" } );
            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries[0].Links.SelfLink.ToString().Contains("patient/@1"));
        }

       

        private string lastNewId;

        [TestMethod]
        public void TestCreateEditDelete()
        {
            //Patient ewout = new Patient
            //{
            //    Name = new List<HumanName> { HumanName.ForFamily("Kramer").WithGiven("Wouter").WithGiven("Gert") },
            //    BirthDateElement = new FhirDateTime(1972, 11, 30),
            //    Identifier = new List<Identifier> {
            //        new Identifier() { System = new Uri("http://hl7.org/test/1"), Key = "3141" } }
            //};

            //FhirClient client = new FhirClient(testEndpoint);
            //string newId;
            //ewout = client.Create<Patient>(ewout, out newId);

            //Assert.IsNotNull(ewout);
            //Assert.IsNotNull(newId);

            //ewout.Name.Add(HumanName.ForFamily("Kramer").WithGiven("Ewout"));

            //ewout = client.Update<Patient>(ewout, newId);

            //Assert.IsNotNull(ewout);

            //var result = client.Delete<Patient>(newId);
            //Assert.IsTrue(result);

            //ewout = client.Read<Patient>(newId);
            //Assert.IsNull(ewout);
            //Assert.AreEqual(HttpStatusCode.Gone, client.LastResponseDetails.Result);

            //lastNewId = newId;
        }


        [TestMethod]
        public void TestHistory()
        {
            TestCreateEditDelete();
            DateTimeOffset now = DateTimeOffset.Now;

            FhirClient client = new FhirClient(testEndpoint);
            Bundle history = client.History<Patient>(lastNewId);
            Assert.IsNotNull(history);
            Assert.AreEqual(3, history.Entries.Count());
            Assert.AreEqual(2, history.Entries.Where(entry => entry is ResourceEntry).Count());
            Assert.AreEqual(1, history.Entries.Where(entry => entry is DeletedEntry).Count());

            // Now, assume no one is quick enough to insert something between now and the next
            // tests....

            history = client.History<Patient>(now);
            Assert.IsNotNull(history);
            Assert.AreEqual(3, history.Entries.Count());
            Assert.AreEqual(2, history.Entries.Where(entry => entry is ResourceEntry).Count());
            Assert.AreEqual(1, history.Entries.Where(entry => entry is DeletedEntry).Count());

            history = client.History(now);
            Assert.IsNotNull(history);
            Assert.AreEqual(3, history.Entries.Count());
            Assert.AreEqual(2, history.Entries.Where(entry => entry is ResourceEntry).Count());
            Assert.AreEqual(1, history.Entries.Where(entry => entry is DeletedEntry).Count());

        }
    }
}
