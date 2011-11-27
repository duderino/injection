using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._6;
using MolesTest._6.Moles;

namespace MolesTest.Tests._6
{
    /// <summary>
    /// Demonstrate mocking/stubing out inaccessible (e.g., private) inner/nested classes
    /// </summary>
    [TestClass]
    public class ClassTest04
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            // Does not compile: MClass06.MDependency06.AllInstances.generate = _ => 123;

            Class06 clazz = new Class06();

            // Does not work since we can't replace the private nested class: Assert.AreEqual(2 * 123, clazz.generate());

            Assert.AreEqual(2 * 999, clazz.generate());
        }
    }
}
