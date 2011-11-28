using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._12;
using MolesTest._12.Moles;

namespace MolesTest.Tests._12
{
    /// <summary>
    /// Demonstrate mocking/stubbing out any implementation of an interface
    /// </summary>
    [TestClass]
    public class ClassTest12
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            // Does not compile: MDependency12.AllInstances.generate = _ => 123;

            Class12 clazz = new Class12();

            // Does not pass because could not inject stub:  Assert.AreEqual(2 * 123, clazz.generate());

            Assert.AreEqual(2 * 999, clazz.generate());
        }
    }
}
