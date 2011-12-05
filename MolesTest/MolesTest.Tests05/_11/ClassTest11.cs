using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._11;
using MolesTest._11.Moles;

namespace MolesTest.Tests._11
{
    /// <summary>
    /// Demonstrate injecting a dependency into a class without modifying its public API.
    /// </summary>
    [TestClass]
    public class ClassTest11
    {
        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            int calls = 0;

            MDependency11.AllInstances.generate = (Dependency11 dependency) =>
            {
                if (++calls == 2) {
                    return 123;
                }

                return MolesContext.ExecuteWithoutMoles(() =>
                {
                    return dependency.generate();
                });
            };

            Class11 clazz = new Class11();

            Assert.AreEqual(999 + (2 * 123), clazz.generate());
        }
    }
}
