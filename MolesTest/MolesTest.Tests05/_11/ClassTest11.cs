using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._11;
using MolesTest._11.Moles;

namespace MolesTest.Tests._11
{
    /// <summary>
    /// Demonstrate injecting a dependency into a class without modifying its public API.
    /// </summary>
    [TestClass]
    public class ClassTest11
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency11.AllInstances.generate = _ => 123;

            Class11 clazz = new Class11();

            // No way to mock out the first generate call but not the second, so this will fail: Assert.AreEqual(123 + 999, clazz.generate());

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
