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
    /// Demonstrate mocking/stubbing out non-virtual function.
    /// </summary>
    [TestClass]
    public class ClassTest02
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency02.AllInstances.generate = _ => 123;
            
            Class02 clazz = new Class02();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
