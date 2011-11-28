using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._14;
using MolesTest._14.Moles;

namespace MolesTest.Tests._14
{
    /// <summary>
    /// Demonstrate injecting async-friendly mocks/stubs.
    /// </summary>
    [TestClass]
    public class ClassTest14
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency14.AllInstances.generateClass14 = (Dependency14 dependency, Class14 clazz) =>
            {
                clazz.callback(123);
            };
            
            Class14 clazz2 = new Class14();

            Assert.AreEqual(2 * 123, clazz2.generate());
        }
    }
}
