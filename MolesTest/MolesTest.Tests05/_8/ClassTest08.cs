using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._8;
using MolesTest._8.Moles;

namespace MolesTest.Tests._8
{
    /// <summary>
    /// Demonstrate injecting a dependency into a class without modifying its public API.
    /// </summary>
    [TestClass]
    public class ClassTest01
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            int count = 0;

            MDependency08.AllInstances.generate = (Dependency08 dependency) =>
            {
                ++count;

                return MolesContext.ExecuteWithoutMoles(() => {
                    return dependency.generate();
                });
            };
            
            Class08 clazz = new Class08();

            for (int i = 0; i < 10; ++i)
            {
                Assert.AreEqual(2 * 999, clazz.generate());
            }

            Assert.AreEqual(10, count);
        }
    }
}
