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
        Uri testEndpoint = new Uri("http://fhir.furore.com/fhir");


        [TestMethod]
        public void TestConformance()
        {
            FhirClient client = new FhirClient(testEndpoint);

            Conformance c = client.Conformance();

            Assert.IsNotNull(c);
            Assert.AreEqual("HL7Connect", c.Software.Name.Contents);
            Assert.AreEqual(Conformance.RestfulConformanceMode.Server, c.Rest[0].Mode.Contents);
            Assert.AreEqual("text/xml+fhir", client.LastResponseDetails.ContentType.ToLower());
            Assert.AreEqual(HttpStatusCode.OK, client.LastResponseDetails.Result);
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

        [TestMethod]
        public void TestRead()
        {
            FhirClient client = new FhirClient(testEndpoint);

            Patient eve = client.Read<Patient>("1");
            Assert.IsNotNull(eve);
            Assert.AreEqual("Eve", eve.Details.Name[0].Given[0].Contents);

            string version = new ResourceLocation(client.LastResponseDetails.ContentLocation).VersionId;               
            Assert.AreEqual("1", version);

            Patient eve2 = client.VRead<Patient>("1", version);
            Assert.IsNotNull(eve2);
            Assert.AreEqual(FhirSerializer.SerializeResourceAsJson(eve),
                            FhirSerializer.SerializeResourceAsJson(eve2));
        }

        private string lastNewId;

        [TestMethod]
        public void TestCreateEditDelete()
        {
            Patient ewout = new Patient
            {
                Details =
                    Demographics.ForName(HumanName.ForFamily("Kramer").
                                WithGiven("Wouter").WithGiven("Gert"))
                                    .WithBirthDate(new FhirDateTime(1972, 11, 30)),
                Identifier = new List<Identifier> {
                    new Identifier() { System = new Uri("http://hl7.org/test/1"), Key = "3141" } }
            };

            FhirClient client = new FhirClient(testEndpoint);
            string newId;
            ewout = client.Create<Patient>(ewout, out newId);

            Assert.IsNotNull(ewout);
            Assert.IsNotNull(newId);

            ewout.Details.Name.Add(HumanName.ForFamily("Kramer").WithGiven("Ewout"));

            ewout = client.Update<Patient>(ewout, newId);

            Assert.IsNotNull(ewout);

            var result = client.Delete<Patient>(newId);
            Assert.IsTrue(result);

            ewout = client.Read<Patient>(newId);
            Assert.IsNull(ewout);
            Assert.AreEqual(HttpStatusCode.Gone, client.LastResponseDetails.Result);

            lastNewId = newId;
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
