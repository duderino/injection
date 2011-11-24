using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._2;
using MolesTest._2.Moles;

namespace MolesTest.Tests._2
{
    /// <summary>
    /// Demonstrate injecting a dependency into a class without modifying its public API.
    /// </summary>
    [TestClass]
    public class ClassTest
    {
        [TestMethod]
        [HostType("Moles")]
        public void test2()
        {
            MDependency01.AllInstances.generate = _ => 123;
            
            Class clazz = new Class();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
