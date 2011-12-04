using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._17;
using MolesTest._17.Moles;
using System.Threading;

namespace MolesTest.Tests._17
{
    /// <summary>
    /// Demonstrate associating assertions in hand-coded mocks or stubs with test cases in different threads
    /// </summary>
    [TestClass]
    public class ClassTest17
    {
        private void run()
        {
            Assert.AreEqual(1, 2);
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

            for (int i = 0; i < threads.Length; ++i)
            {
                threads[i].Start();
            }

            for (int i = 0; i < threads.Length; ++i)
            {
                threads[i].Join();
            }*/
        }
    }
}
