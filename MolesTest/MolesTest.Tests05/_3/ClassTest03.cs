using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._3;
using MolesTest._3.Moles;

namespace MolesTest.Tests._3
{
    /// <summary>
    /// Demonstrate mocking/stubbing out static functions.
    /// </summary>
    [TestClass]
    public class ClassTest03
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency03.generate = () => 123;
            
            Class03 clazz = new Class03();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
