using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._16;
using MolesTest._16.Moles;
using System.Threading;

namespace MolesTest.Tests._16
{
    /// <summary>
    /// Demonstrate associating assertions in hand-coded mocks or stubs with test cases in same thread
    /// </summary>
    [TestClass]
    public class ClassTest16
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency16.AllInstances.generate = _ =>
            {
                // Breaks test case: Assert.AreEqual(1, 2);

                return 123;
            };

            Class16 clazz = new Class16();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
