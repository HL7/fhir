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
    public class TagTests
    {
        [TestMethod]
        public void TagValidation()
        {
            Tag t = new Tag(null, (string)null, null); 
            
            try
            {
                // should throw error
                Validator.ValidateObject(t, new ValidationContext(t), true);
                Assert.Fail();
            }
            catch (ValidationException) { }

            TagList l = new TagList();

            try
            {
                // should throw error               
                l.Category.Add(t);
                Validator.ValidateObject(l, new ValidationContext(l), true);
                Assert.Fail();
            }
            catch (ValidationException) { }

            l.Category.Clear();

            l.Category.Add(new Tag("someterm", Tag.FHIRTAGSCHEME, "hi!"));

            Validator.ValidateObject(l, new ValidationContext(l), true);
        }


        [TestMethod]
        public void TagSearching()
        {
            IList<Tag> tl = new List<Tag>();

            tl.Add(new Tag("http://nu.nl", Tag.FHIRTAGNS, "v1" ) );
            tl.Add(new Tag("http://dan.nl", Tag.FHIRTAGNS, "v2"));
            tl.Add(new Tag("http://dan.nl", "http://crap.com", "v0"));

            Assert.IsFalse(tl.HasTag("http://straks.nl"));
            Assert.IsFalse(tl.HasTag("http://nu.nl", "http://crap.com"));
            Assert.IsTrue(tl.HasTag("http://dan.nl"));
            Assert.IsTrue(tl.HasTag("http://dan.nl", Tag.FHIRTAGNS));

            Assert.AreEqual(2, tl.FilterFhirTags().Count());
        }

        [TestMethod]
        public void TagListMerging()
        {
            IList<Tag> tl = new List<Tag>();
            tl.Add(new Tag("http://nu.nl", Tag.FHIRTAGNS, "v1"));
            tl.Add(new Tag("http://dan.nl", Tag.FHIRTAGNS, "v2"));

            IList<Tag> tl2 = new List<Tag>();
            tl2.Add(new Tag("http://nu.nl", Tag.FHIRTAGNS, "v3"));
            tl2.Add(new Tag("http://nooit.nl", Tag.FHIRTAGNS, "v4"));

            var result = tl.Merge(tl2);

            Assert.AreEqual(3, result.Count());
            Assert.AreEqual(1, result.FilterFhirTags().FindByTerm("http://nu.nl").Count());
            Assert.AreEqual("v3", result.FindByTerm("http://nu.nl", Tag.FHIRTAGNS).First().Label);
            Assert.AreEqual(1, result.FilterFhirTags().FindByTerm("http://nooit.nl").Count());
        }

        [TestMethod]
        public void TagListRemoval()
        {
            IList<Tag> tl = new List<Tag>();
            tl.Add(new Tag("http://nu.nl", Tag.FHIRTAGNS, "v1"));
            tl.Add(new Tag("http://dan.nl", Tag.FHIRTAGNS, "v2"));

            IList<Tag> tl2 = new List<Tag>();
            tl2.Add(new Tag("http://nu.nl", Tag.FHIRTAGNS));
            tl2.Add(new Tag("http://nu.nl",(string)null));
            tl2.Add(new Tag("http://nooit.nl", Tag.FHIRTAGNS));

            var result = tl.Remove(tl2);

            Assert.AreEqual(new Tag("http://dan.nl", Tag.FHIRTAGNS), result.Single());
        }


        [TestMethod]
        public void TagEquality()
        {
            var t1 = new Tag("dog",(string)null);
            var t2 = new Tag("dog", new Uri("http://knmi.nl") );
            var t3 = new Tag("dog", "http://knmi.nl");

            Assert.AreNotEqual(t1, t2);
            Assert.AreNotEqual(t1, t3);
            Assert.AreEqual(t2, t3);            
        }


        private static void verifyTagList(IList<Tag> tl)
        {
            Assert.AreEqual(2, tl.Count);
            Assert.AreEqual("No!", tl[0].Label);
            Assert.AreEqual("http://www.nu.nl/tags", tl[0].Term);
            Assert.AreEqual("Maybe, indeed", tl[1].Label);
            Assert.AreEqual("http://www.furore.com/tags", tl[1].Term);
        }  
    }
}
