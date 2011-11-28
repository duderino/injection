using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._15;
using MolesTest._15.Moles;
using System.Threading;

namespace MolesTest.Tests._15
{
    /// <summary>
    /// Demonstrate injecting async-friendly mocks/stubs.
    /// </summary>
    [TestClass]
    public class ClassTest15
    {
        public void run()
        {
            int magicNumber = Thread.GetDomainID() + 123;

            MDependency15.AllInstances.generate = _ => magicNumber;

            Class15 clazz = new Class15();

            Assert.AreEqual(2 * magicNumber, clazz.generate());
        }

        [TestMethod]
        [HostType("Moles")]
        public void test()
        {
            /* Crashes Visual Studio 2010 Test Case
            Thread[] threads = new Thread[10];

            for (int i = 0; i < threads.Length; ++i)
            {
                threads[i] = new Thread(this.run);
            }

            for (int i = 0; i < 10; ++i)
            {
                threads[i].Start();
            }

            for (int i = 0; i < 10; ++i)
            {
                threads[i].Join();
            }
             */
        }
    }
}
