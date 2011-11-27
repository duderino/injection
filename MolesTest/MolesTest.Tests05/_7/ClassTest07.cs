using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._7;
using MolesTest._7.Moles;

namespace MolesTest.Tests._7
{
    /// <summary>
    /// Demonstrate mocking/stubbing out subclass and superclass functions
    /// </summary>
    [TestClass]
    public class ClassTest07
    {
        [TestMethod]
        [HostType("Moles")]
        public void testOverride()
        {
            MSubDependency07.AllInstances.generate = _ => 123;

            Class07 clazz = new Class07();

            Assert.AreEqual(2 * 123, clazz.generate());
            Assert.AreEqual(2 * 333, clazz.subGenerate());
            Assert.AreEqual(2 * 999, clazz.superGenerate());
        }

        [TestMethod]
        [HostType("Moles")]
        public void testOvershadow()
        {
            MSuperDependency07.AllInstances.generate = _ => 123;

            Class07 clazz = new Class07();

            Assert.AreEqual(2 * 333, clazz.generate());
            Assert.AreEqual(2 * 333, clazz.subGenerate());
            Assert.AreEqual(2 * 999, clazz.superGenerate());
        }

        [TestMethod]
        [HostType("Moles")]
        public void testSuper()
        {
            // does not compile: MSubDependency07.AllInstances.superGenerate = _ => 123;

            MSuperDependency07.AllInstances.superGenerate = _ => 123;

            Class07 clazz = new Class07();

            Assert.AreEqual(2 * 333, clazz.generate());
            Assert.AreEqual(2 * 333, clazz.subGenerate());
            Assert.AreEqual(2 * 123, clazz.superGenerate());
        }

        [TestMethod]
        [HostType("Moles")]
        public void testSub()
        {
            MSubDependency07.AllInstances.subGenerate = _ => 123;

            Class07 clazz = new Class07();

            Assert.AreEqual(2 * 333, clazz.generate());
            Assert.AreEqual(2 * 123, clazz.subGenerate());
            Assert.AreEqual(2 * 999, clazz.superGenerate());
        }
    }
}
