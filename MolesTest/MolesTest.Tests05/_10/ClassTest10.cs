using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._10;
using MolesTest._10.Moles;

namespace MolesTest.Tests._10
{
    /// <summary>
    /// Demonstrate toggling of mocks/stub injection.
    /// </summary>
    [TestClass]
    public class ClassTest01
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency10.AllInstances.generate = _ => 123;
            
            Class10 clazz = new Class10();

            Assert.AreEqual(2 * 123, clazz.generate());

            MDependency10.AllInstances.generate = null;

            Assert.AreEqual(2 * 999, clazz.generate());

            MDependency10.AllInstances.generate = _ => 123;

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
