using System;
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
using Hl7.Fhir.Support.Search;

namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class SearchParamTests
    {
        //var p3 = new IntegerParam("age", ComparisonOperator.LTE, 18);
        //Assert.AreEqual("age=%3C%3D18", p3.ToQueryParam());


        [TestMethod]
        public void TestHandleIntegerParam()
        {
            var p1 = new IntegerParamValue(18);
            Assert.AreEqual("18", p1.QueryValue);

            var p2 = new IntegerParamValue(ComparisonOperator.GT, 18);
            Assert.AreEqual(">18", p2.QueryValue);

            var p3 = IntegerParamValue.FromQueryValue("18");
            Assert.AreEqual(18, p3.Value);
            Assert.AreEqual(ComparisonOperator.EQ, p3.Comparison);

            var p4 = IntegerParamValue.FromQueryValue("<=18");
            Assert.AreEqual(18, p4.Value);
            Assert.AreEqual(ComparisonOperator.LTE, p4.Comparison);
        }

        [TestMethod]
        public void TestHandleDateParam()
        {
            var p1 = new DateParamValue(new DateTimeOffset(1972,11,30,15,20,49, TimeSpan.Zero));
            Assert.AreEqual("1972-11-30T15:20:49+00:00", p1.QueryValue);

            var p2 = new DateParamValue(ComparisonOperator.GT, "1972-11-30");
            Assert.AreEqual(">1972-11-30", p2.QueryValue);

            var p3 = DateParamValue.FromQueryValue("1972-11-30T15:00:04+02:00");
            Assert.AreEqual("1972-11-30T15:00:04+02:00", p3.Value);
            Assert.AreEqual(ComparisonOperator.EQ, p3.Comparison);

            var p4 = DateParamValue.FromQueryValue("<1972-11-30T15:00:04+02:00");
            Assert.AreEqual("1972-11-30T15:00:04+02:00", p4.Value);
            Assert.AreEqual(ComparisonOperator.LT, p4.Comparison);
        }

        [TestMethod]
        public void TestHandleReferenceParam()
        {
            var p1 = new ReferenceParamValue("patient", "2");
            Assert.AreEqual("patient/2", p1.QueryValue);

            var p2 = ReferenceParamValue.FromQueryValue("organization/34");
            Assert.AreEqual("34", p2.Id);
            Assert.AreEqual("organization", p2.ResourceType);
        }


        [TestMethod]
        public void TestHandleStringParam()
        {
            var p1 = new StringParamValue("patient");
            Assert.AreEqual("\"patient\"", p1.QueryValue);

            var p2 = StringParamValue.FromQueryValue("\"organization\"");
            Assert.AreEqual("organization", p2.Value);
        }


        [TestMethod]
        public void TestHandleTokenParam()
        {
            var p1 = new TokenParamValue("NOK", "http://somewhere.nl/codes");
            Assert.AreEqual("http://somewhere.nl/codes!NOK", p1.QueryValue);

            var p2 = new TokenParamValue("NOK", namespaceSensitive:false);
            Assert.AreEqual("NOK", p2.QueryValue);

            var p3 = new TokenParamValue("NOK", namespaceSensitive:true);
            Assert.AreEqual("!NOK", p3.QueryValue);

            var p4 = TokenParamValue.FromQueryValue("http://somewhere.nl/codes!NOK");
            Assert.AreEqual("http://somewhere.nl/codes", p4.Namespace);
            Assert.AreEqual("NOK", p4.Value);
            Assert.IsTrue(p4.NamespaceSensitive);

            var p5 = TokenParamValue.FromQueryValue("!NOK");
            Assert.AreEqual(null, p5.Namespace);
            Assert.AreEqual("NOK", p5.Value);
            Assert.IsTrue(p5.NamespaceSensitive);

            var p6 = TokenParamValue.FromQueryValue("NOK");
            Assert.AreEqual(null, p6.Namespace);
            Assert.AreEqual("NOK", p6.Value);
            Assert.IsFalse(p6.NamespaceSensitive);
        }


        [TestMethod]
        public void TestHandleCombinedParam()
        {
        }

        [TestMethod]
        public void ParseIntegerParam()
        {
            //var p1 = IntegerParam.FromQueryParam("age=18");
            //Assert.AreEqual("age", p1.Name);
            //Assert.AreEqual(18, p1.Value);
            //Assert.AreEqual(ComparisonOperator.EQ,p1.Comparison);

            //var p2 = IntegerParam.FromQueryParam("age=%3E18");
            //Assert.AreEqual(ComparisonOperator.GT, p2.Comparison);
            //Assert.AreEqual(18, p2.Value);

            //var p3 = IntegerParam.FromQueryParam("age=%3C%3D18");
            //Assert.AreEqual(ComparisonOperator.LTE, p3.Comparison);
            //Assert.AreEqual(18, p3.Value);

            //var p4 = IntegerParam.FromQueryParam("age:missing=true");
            //Assert.AreEqual(MissingOperator.HasNoValue, p4.Missing);
            //Assert.AreEqual("age", p4.Name);

            //var p5 = IntegerParam.FromQueryParam("age:missing=false");
            //Assert.AreEqual(MissingOperator.HasAnyValue, p5.Missing);
            //Assert.AreEqual("age", p5.Name);
        }
    }
}