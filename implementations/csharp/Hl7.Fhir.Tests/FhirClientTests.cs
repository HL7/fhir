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
using Hl7.Fhir.Support.Search;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class FhirClientTests
    {
        //Uri testEndpoint = new Uri("http://fhir.furore.com/fhir");
        Uri testEndpoint = new Uri("http://hl7connect.healthintersections.com.au/svc/fhir");


        [TestMethod]
        public void FetchConformance()
        {
            FhirClient client = new FhirClient();

            Conformance c = client.Conformance(testEndpoint).Content;

            Assert.IsNotNull(c);
            Assert.AreEqual("HL7Connect", c.Software.Name);
            Assert.AreEqual(Conformance.RestfulConformanceMode.Server, c.Rest[0].Mode.Value);
            Assert.AreEqual(ContentType.XML_CONTENT_HEADER, client.LastResponseDetails.ContentType.ToLower());
            Assert.AreEqual(HttpStatusCode.OK, client.LastResponseDetails.Result);
        }


        [TestMethod]
        public void Read()
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

            var loc2 = client.Fetch<Location>("1", version);
            Assert.IsNotNull(loc2);
            Assert.AreEqual(FhirSerializer.SerializeBundleEntryToJson(loc),
                            FhirSerializer.SerializeBundleEntryToJson(loc2));

            var loc3 = client.Fetch<Location>(loc.SelfLink);
            Assert.IsNotNull(loc3);
            Assert.AreEqual(FhirSerializer.SerializeBundleEntryToJson(loc),
                            FhirSerializer.SerializeBundleEntryToJson(loc3));

        }


        [TestMethod]
        public void Search()
        {
            FhirClient client = new FhirClient(testEndpoint);
            Bundle result;

            result = client.Search(ResourceType.DiagnosticReport);
            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries.Count > 0);
            Assert.IsTrue(result.Entries[0].Id.ToString().EndsWith("@101"));
            Assert.IsTrue(result.Entries.Count() > 10, "Test should use testdata with more than 10 reports");

            result = client.Search(ResourceType.DiagnosticReport,count:10);
            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries.Count <= 10);
            Assert.IsTrue(result.Entries[0].Id.ToString().EndsWith("@101"));

            //result = client.SearchById<DiagnosticReport>("101", "DiagnosticReport/subject");
            result = client.Search(ResourceType.DiagnosticReport, "_id", "101", new string[] { "DiagnosticReport.subject" } );
            Assert.IsNotNull(result);

            Assert.AreEqual(1,
                    result.Entries.Where(entry => entry.Links.SelfLink.ToString()
                        .Contains("diagnosticreport")).Count());

            Assert.IsTrue(result.Entries.Any(entry =>
                    entry.Links.SelfLink.ToString().Contains("patient/@pat2")));

            result = client.Search(ResourceType.DiagnosticReport, new SearchParam[] 
                {
                    new SearchParam("name", new StringParamValue("Everywoman")),
                    new SearchParam("name", new StringParamValue("Eve")) 
                });

            Assert.IsNotNull(result);
            Assert.IsTrue(result.Entries[0].Links.SelfLink.ToString().Contains("patient/@1"));
        }



        private Uri createdTestOrganization = null;

        [TestMethod]
        public void CreateEditDelete()
        {
            var furore = new Organization
            {
                Name = "Furore",
                Identifier = new List<Identifier> { new Identifier("http://hl7.org/test/1", "3141") },
                Telecom = new List<Contact> { new Contact { System = Contact.ContactSystem.Phone, Value = "+31-20-3467171" } }
            };

            FhirClient client = new FhirClient(testEndpoint);
            var tags = new List<Tag> { new Tag("http://nu.nl/testname", "TestCreateEditDelete") };

            var fe = client.Create(furore,tags);

            Assert.IsNotNull(furore);
            Assert.IsNotNull(fe);
            Assert.IsNotNull(fe.Id);
            Assert.IsNotNull(fe.SelfLink);
            Assert.AreNotEqual(fe.Id,fe.SelfLink);
            Assert.IsNotNull(fe.Tags);
            Assert.AreEqual(1, fe.Tags.Count());
            Assert.AreEqual(fe.Tags.First(), tags[0]);
            createdTestOrganization = fe.Id;

            fe.Content.Identifier.Add(new Identifier("http://hl7.org/test/2", "3141592"));

            var fe2 = client.Update(fe);

            Assert.IsNotNull(fe2);
            Assert.AreEqual(fe.Id, fe2.Id);
            Assert.AreNotEqual(fe.SelfLink, fe2.SelfLink);
            Assert.IsNotNull(fe2.Tags);
            Assert.AreEqual(1, fe2.Tags.Count());
            Assert.AreEqual(fe2.Tags.First(), tags[0]);

            client.Delete(fe2.Id);

            try
            {
                fe = client.Read<Organization>(ResourceLocation.GetIdFromResourceId(fe.Id));
                Assert.Fail();
            }
            catch
            {
                Assert.IsTrue(client.LastResponseDetails.Result == HttpStatusCode.Gone);
            }
            
            Assert.IsNull(fe);
        }


        [TestMethod]
        public void History()
        {
            DateTimeOffset now = DateTimeOffset.Now;

            CreateEditDelete();

            FhirClient client = new FhirClient(testEndpoint);
            Bundle history = client.History(createdTestOrganization);
            Assert.IsNotNull(history);
            Assert.AreEqual(3, history.Entries.Count());
            Assert.AreEqual(2, history.Entries.Where(entry => entry is ResourceEntry).Count());
            Assert.AreEqual(1, history.Entries.Where(entry => entry is DeletedEntry).Count());

            // Now, assume no one is quick enough to insert something between now and the next
            // tests....

            history = client.History<Organization>(now);
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
