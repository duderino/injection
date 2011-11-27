using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._9;
using MolesTest._9.Moles;

namespace MolesTest.Tests._9
{
    /// <summary>
    /// Demonstrate injecting hand-coded mocks or stubs. 
    /// </summary>
    [TestClass]
    public class ClassTest09
    {
        private class MockDependency
        {
            private int value = 123;

            public int generate()
            {
                return value;
            }
        }

        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            // While this works, it doesn't appear to be threadsafe.

            MockDependency mockDependency = new MockDependency();

            MDependency09.AllInstances.generate = _ =>
            {
                return mockDependency.generate();
            };
            
            Class09 clazz = new Class09();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
