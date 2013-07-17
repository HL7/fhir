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


namespace Hl7.Fhir.Tests
{
    [TestClass]
    public class SearchParamTests
    {
        [TestMethod]
        public void TestCreateIntegerParam()
        {
            //var p1 = new IntegerParam("age", 18);
            //Assert.AreEqual("age=18", p1.ToQueryParam());

            //var p2 = new IntegerParam("age", ComparisonOperator.GT, 18);
            //Assert.AreEqual("age=%3E18", p2.ToQueryParam());

            //var p3 = new IntegerParam("age", ComparisonOperator.LTE, 18);
            //Assert.AreEqual("age=%3C%3D18", p3.ToQueryParam());

            //var p4 = new IntegerParam("age", MissingOperator.HasNoValue);
            //Assert.AreEqual("age:missing=true", p4.ToQueryParam());

            //var p5 = new IntegerParam("age", MissingOperator.HasAnyValue);
            //Assert.AreEqual("age:missing=false", p5.ToQueryParam());
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