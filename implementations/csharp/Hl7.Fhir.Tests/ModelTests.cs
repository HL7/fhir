using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Hl7.Fhir.Model;
using System.Xml.Linq;
using System.ComponentModel.DataAnnotations;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class ModelTests
    {
        [TestMethod]
        public void TestIdValidation()
        {
            Id id = new Id("az23");

            Validator.ValidateObject(id, new ValidationContext(id), true);

            id = new Id("!notgood!");
            validateErrorOrFail(id);

            id = new Id("NotGood");
            validateErrorOrFail(id);

            id = new Id("1234567890123456789012345678901234567");
            validateErrorOrFail(id);
        }


        private void validateErrorOrFail(object instance)
        {
            try
            {
                // should throw error
                Validator.ValidateObject(instance, new ValidationContext(instance), true);
                Assert.Fail();
            }
            catch (ValidationException) { }
        }
    
        [TestMethod]
        public void OIDandUUIDUrls()
        {
            var oidUrl = "urn:oid:1.2.3";
            var illOidUrl = "urn:oid:datmagdusniet";
            var uuidUrl = "urn:uuid:a5afddf4-e880-459b-876e-e4591b0acc11";
            var illUuidUrl = "urn:uuid:ooknietgoed";
            var oidWithZero = "urn:oid:1.2.0.3.4";

            FhirUri uri = new FhirUri(oidUrl);
            Validator.ValidateObject(uri, new ValidationContext(uri), true);

            uri = new FhirUri(illOidUrl);
            validateErrorOrFail(uri);
            
            uri = new FhirUri(uuidUrl);
            Validator.ValidateObject(uri, new ValidationContext(uri), true);

            uri = new FhirUri(illUuidUrl);
            validateErrorOrFail(uri);

            uri = new FhirUri(oidWithZero);
            Validator.ValidateObject(uri, new ValidationContext(uri), true);
         
            Assert.IsTrue(Uri.Equals(new Uri("http://nu.nl"), new Uri("http://nu.nl")));
        }


        [TestMethod]
        public void ValidateResourceWithIncorrectChildElement()
        {
            FhirDateTime dt = new FhirDateTime();
            dt.Value = "Ewout Kramer";

            Observation o = new Observation { Applies = dt };
            DiagnosticReport rep = new DiagnosticReport();
            rep.Contained = new List<Resource> { o };

            validateErrorOrFail(rep);
        }


        [TestMethod]
        public void TestAllowedChoices()
        {
            Patient p = new Patient();

            p.Deceased = new FhirBoolean(true);
            Validator.ValidateObject(p, new ValidationContext(p), true);

            p.Deceased = new FhirUri();
            validateErrorOrFail(p);
        }


        [TestMethod]
        public void TestCardinality()
        {
            OperationOutcome oo = new OperationOutcome();
            validateErrorOrFail(oo);

            oo.Issue = new List<OperationOutcome.OperationOutcomeIssueComponent>();
            validateErrorOrFail(oo);

            var issue = new OperationOutcome.OperationOutcomeIssueComponent();

            oo.Issue.Add(issue);
            validateErrorOrFail(oo);

            issue.Severity = OperationOutcome.IssueSeverity.Information;
            Validator.ValidateObject(issue, new ValidationContext(issue), true);
        }

        [TestMethod]
        public void ValidateElementAssertions()
        {
            XElement xr = new XElement("root",
                        new XElement("child", "value"),
                        new XElement("child", "value2"));

            Assert.IsNull(xr.Element("childx"));
            Assert.AreEqual(0,xr.Elements("childx").Count());
            Assert.AreEqual("value",xr.Element("child").Value);
        }


        [TestMethod]
        public void DateTimeHandling()
        {
            FhirDateTime dt = new FhirDateTime("2010-01-01");
            Assert.AreEqual("2010-01-01", dt.Value);

            FhirDateTime dt2 = new FhirDateTime(1972, 11, 30, 15, 10);
            Assert.IsTrue(dt2.Value.StartsWith("1972-11-30T15:10"));
        }

     
        [TestMethod]
        public void SimpleValueSupport()
        {
            Conformance c = new Conformance();

            Assert.IsNull(c.AcceptUnknown);
            c.AcceptUnknown = true;
            Assert.IsTrue(c.AcceptUnknown.GetValueOrDefault());
            Assert.IsNotNull(c.AcceptUnknownElement);
            Assert.IsTrue(c.AcceptUnknownElement.Value.GetValueOrDefault());

            c.PublisherElement = new FhirString("Furore");
            Assert.AreEqual("Furore", c.Publisher);
            c.Publisher = null;
            Assert.IsNull(c.PublisherElement);
            c.Publisher = "Furore";
            Assert.IsNotNull(c.PublisherElement);

            c.Format = new string[] { "json", "xml" };
            Assert.IsNotNull(c.FormatElement);
            Assert.AreEqual(2, c.FormatElement.Count);
            Assert.AreEqual("json", c.FormatElement.First().Value);

            c.FormatElement = new List<Code>();
            c.FormatElement.Add(new Code("csv"));
            Assert.IsNotNull(c.Format);
            Assert.AreEqual(1, c.Format.Count());
        }


        [TestMethod]
        public void ExtensionManagement()
        {
            Patient p = new Patient();
            Uri u1 = new Uri("http://fhir.org/ext/ext-test");
            Assert.IsNull(p.GetExtension(u1));

            Extension newEx = p.SetExtension(u1, new FhirBoolean(true));
            Assert.AreSame(newEx, p.GetExtension(u1));

            p.AddExtension(new Uri("http://fhir.org/ext/ext-test2"), new FhirString("Ewout"));
            Assert.AreSame(newEx, p.GetExtension(u1));

            p.RemoveExtension(u1);
            Assert.IsNull(p.GetExtension(u1));

            p.SetExtension(new Uri("http://fhir.org/ext/ext-test2"), new FhirString("Ewout Kramer"));
            var ew = p.GetExtensions(new Uri("http://fhir.org/ext/ext-test2"));
            Assert.AreEqual(1, ew.Count());

            p.AddExtension(new Uri("http://fhir.org/ext/ext-test2"), new FhirString("Wouter Kramer"));

            ew = p.GetExtensions(new Uri("http://fhir.org/ext/ext-test2"));
            Assert.AreEqual(2, ew.Count());

        }

        [TestMethod]
        public void TypedResourceEntry()
        {
            var pe = new ResourceEntry<Patient>();

            pe.Resource = new Patient();

            ResourceEntry e = pe;

            Assert.AreEqual(pe.Resource, e.Resource);

            e.Resource = new CarePlan();

            try
            {
                var c = pe.Resource;
                Assert.Fail("Should have bombed");
            }
            catch (InvalidCastException)
            {
                // pass
            }
        }

        [TestMethod]
        public void ValidateBundleEntry()
        {
            var e = new ResourceEntry<Patient>();
            e.Id = new Uri("http://someserver.org/fhir/patient/@1");
            e.Title = "Some title";
            e.LastUpdated = DateTimeOffset.Now;

            // Validates mandatory fields?
            validateErrorOrFail(e);
            e.Resource = new Patient();
            Validator.ValidateObject(e, new ValidationContext(e), true);

            // Checks nested errors on resource content?
            e.Resource = new Patient { Deceased = new FhirUri() };
            validateErrorOrFail(e);

            e.Resource = new Patient();

            var f = new Bundle() { Title = "Some feed title" };
            f.Id = new Uri("http://someserver.org/fhir/feed/@1424234232342");

            // Validates mandatory fields?
            validateErrorOrFail(f);            
            f.LastUpdated = DateTimeOffset.Now;
            Validator.ValidateObject(f, new ValidationContext(f), true);

            // Checks nested errors on nested bundle element?
            f.Entries.Add(e);
            e.Id = null;
            validateErrorOrFail(f);            
        }

        [TestMethod]
        public void ResourceListFiltering()
        {
            var rl = new List<BundleEntry>();

            rl.Add(new ResourceEntry<Patient> { Id = new Uri("http://x.com/@1"), SelfLink = new Uri("http://x.com/@1/history/@1") });
            rl.Add(new ResourceEntry<Patient> { Id = new Uri("http://x.com/@1"), SelfLink = new Uri("http://x.com/@1/history/@2") });
            rl.Add(new ResourceEntry<CarePlan> { Id = new Uri("http://x.com/@2"), SelfLink = new Uri("http://x.com/@2/history/@1") });
            rl.Add(new DeletedEntry() { Id = new Uri("http://x.com/@2"), SelfLink = new Uri("http://x.com/@2/history/@2") });

            var tr = rl.ByResourceType<Patient>();
            Assert.AreEqual(2, tr.Count());
            var tr2 = rl.ByResourceType<CarePlan>();
            Assert.AreEqual(1, tr2.Count());

            var ur = rl.ById(new Uri("http://x.com/@1"));
            Assert.AreEqual(2, ur.Count());
            Assert.AreEqual(2, ur.ByResourceType<Patient>().Count());

            Assert.IsNotNull(ur.BySelfLink(new Uri("http://x.com/@1/history/@1")));
            Assert.IsNotNull(rl.BySelfLink(new Uri("http://x.com/@2/history/@2")));
        }
    }
}
