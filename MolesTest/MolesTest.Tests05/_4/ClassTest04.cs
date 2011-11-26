using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._4;
using MolesTest._4.Moles;

namespace MolesTest.Tests._4
{
    /// <summary>
    /// Demonstrate mocking/stubbing out constructors.
    /// </summary>
    [TestClass]
    public class ClassTest04
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            MDependency04.ConstructorInt32 = (@this, value) => { 
                Assert.AreEqual(999, value);

                var mole = new MDependency04(@this) { generate = () => 123 }; 
            };
            
            Class04 clazz = new Class04();

            Assert.AreEqual(2 * 123, clazz.generate());
        }
    }
}
