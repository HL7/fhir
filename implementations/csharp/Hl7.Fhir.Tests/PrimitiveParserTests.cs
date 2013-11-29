using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Hl7.Fhir.Model;
using System.Xml;
using System.Xml.Linq;
using System.IO;
using Newtonsoft.Json;
using Hl7.Fhir.Support;


namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class PrimitiveParserTests
    {
        [TestMethod]
        public void BasicTypePatterns()
        {
            Id result;

            result = Id.Parse("az23");

            try
            {
                // should throw error
                result = Id.Parse("!notgood!");
                Assert.Fail();
            }
            catch { }

            // should throw error
            try
            {
                result = Id.Parse("NotGood");
                Assert.Fail();
            }
            catch {}

            // should throw error
            try
            {
                result = Id.Parse("1234567890123456789012345678901234567");
                Assert.Fail();
            }
            catch {}
        }

    }
}