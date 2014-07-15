/* 
 * Copyright (c) 2014, Furore (info@furore.com) and contributors
 * See the file CONTRIBUTORS for details.
 * 
 * This file is licensed under the BSD 3-Clause license
 * available at https://raw.githubusercontent.com/ewoutkramer/fhir-net-api/master/LICENSE
 */

using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Hl7.Fhir.Model;
using System.Xml.Linq;
using System.ComponentModel.DataAnnotations;
using Hl7.Fhir.Validation;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class ModelTests
    {
        [TestMethod]
        public void ValidateElementAssertions()
        {
            XElement xr = new XElement("root",
                        new XElement("child", "value"),
                        new XElement("child", "value2"));

            Assert.IsNull(xr.Element("childx"));
            Assert.AreEqual(0, xr.Elements("childx").Count());
            Assert.AreEqual("value", xr.Element("child").Value);
        }


        [TestMethod]
        public void DateTimeHandling()
        {
            FhirDateTime dt = new FhirDateTime("2010-01-01");
            Assert.AreEqual("2010-01-01", dt.Value);

            FhirDateTime dt2 = new FhirDateTime(1972, 11, 30, 15, 10);
            Assert.IsTrue(dt2.Value.StartsWith("1972-11-30T15:10"));

            var stamp = new DateTimeOffset(1972, 11, 30, 15, 10, 0, TimeSpan.Zero);
            dt = new FhirDateTime(stamp);
            Assert.IsTrue(dt.Value.EndsWith("+00:00"));
        }


        [TestMethod]
        public void Uri_Canonical()
        {
            var identifier = new Identifier("http://nhi.health.nz", "123");
            Assert.AreEqual("123", identifier.Value);
            Assert.AreEqual("http://nhi.health.nz", identifier.System);
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
            var u1 = "http://fhir.org/ext/ext-test";
            Assert.IsNull(p.GetExtension("http://fhir.org/ext/ext-test"));

            Extension newEx = p.SetExtension(u1, new FhirBoolean(true));
            Assert.AreSame(newEx, p.GetExtension(u1));

            p.AddExtension("http://fhir.org/ext/ext-test2", new FhirString("Ewout"));
            Assert.AreSame(newEx, p.GetExtension(u1));

            p.RemoveExtension(u1);
            Assert.IsNull(p.GetExtension(u1));

            p.SetExtension("http://fhir.org/ext/ext-test2", new FhirString("Ewout Kramer"));
            var ew = p.GetExtensions("http://fhir.org/ext/ext-test2");
            Assert.AreEqual(1, ew.Count());

            p.AddExtension("http://fhir.org/ext/ext-test2", new FhirString("Wouter Kramer"));

            ew = p.GetExtensions("http://fhir.org/ext/ext-test2");
            Assert.AreEqual(2, ew.Count());
        }


        [TestMethod]
        public void RecognizeContainedReference()
        {
            var rref = new ResourceReference() { Reference = "#patient2223432" };

            Assert.IsTrue(rref.IsContainedReference);

            rref.Reference = "http://somehwere.nl/Patient/1";
            Assert.IsFalse(rref.IsContainedReference);

            rref.Reference = "Patient/1";
            Assert.IsFalse(rref.IsContainedReference);
        }


        [TestMethod]
        public void FindContainedResource()
        {
            var cPat1 = new Patient() { Id = "pat1" };
            var cPat2 = new Patient() { Id = "pat2" };
            var pat = new Patient();

            pat.Contained = new List<Resource> { cPat1, cPat2 };

            var rref = new ResourceReference() { Reference = "#pat2" };

            Assert.IsNotNull(pat.FindContainedResource(rref));
            Assert.IsNotNull(pat.FindContainedResource(rref.Url));
            
            rref.Reference = "#pat3";
            Assert.IsNull(pat.FindContainedResource(rref));
        }

        [TestMethod]
        public void TestDeepCopy()
        {
            var p = new Patient();

            p.Name = new List<HumanName>();
            p.Name.Add(HumanName.ForFamily("Kramer").WithGiven("Ewout"));
            p.Name.Add(HumanName.ForFamily("Kramer").WithGiven("Wouter"));
            p.SetExtension("http://test.nl/test", new FhirString("Hello, world"));
 
            var p2 = (Patient)p.DeepCopy();

            Assert.IsTrue(p2 is Patient);
            Assert.AreNotEqual(p,p2);
            Assert.IsNotNull(p2.Name);
            Assert.AreNotEqual(p.Name, p2.Name);
            Assert.AreEqual(p.Name[0].Family.First(), p2.Name[0].Family.First());
            Assert.AreEqual(p.Name[0].Given.First(), p2.Name[0].Given.First());
            Assert.AreEqual(p.Name[1].Family.First(), p2.Name[1].Family.First());
            Assert.AreEqual(p.Name[1].Given.First(), p2.Name[1].Given.First());

            var ext = p.GetExtension("http://test.nl/test");
            var ext2 = p2.GetExtension("http://test.nl/test");
            Assert.AreNotEqual(ext, ext2);
            Assert.AreNotEqual(ext.Value, ext2.Value);
            Assert.AreEqual(((FhirString)ext.Value).Value, ((FhirString)ext2.Value).Value);
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
    }
}
