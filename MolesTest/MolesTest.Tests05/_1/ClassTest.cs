using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._1;
using MolesTest._1.Moles;

namespace MolesTest.Tests._1
{
    /// <summary>
    /// Demonstrate injecting a dependency into a class without modifying its public API.
    /// </summary>
    [TestClass]
    public class ClassTest
    {
        [TestMethod]
        [HostType("Moles")]
        public void test1()
        {
            MDependency.AllInstances.generate = _ => 123;
            
            Class clazz = new Class();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
