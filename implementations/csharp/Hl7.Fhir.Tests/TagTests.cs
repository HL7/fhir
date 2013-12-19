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

            l.Category.Add(new Tag("someterm", Tag.FHIRTAGSCHEME_GENERAL, "hi!"));

            Validator.ValidateObject(l, new ValidationContext(l), true);
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
