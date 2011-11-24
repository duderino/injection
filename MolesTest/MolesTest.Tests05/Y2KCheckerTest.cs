// <copyright file="Y2KCheckerTest.cs">Copyright ©  2011</copyright>
using System;
using Microsoft.Pex.Framework;
using Microsoft.Pex.Framework.Validation;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using MolesTest;
using Microsoft.Moles.Framework;
using System.Moles;

namespace MolesTest
{
    /// <summary>This class contains parameterized unit tests for Y2KChecker</summary>
    [PexClass(typeof(Y2KChecker))]
    [PexAllowedExceptionFromTypeUnderTest(typeof(InvalidOperationException))]
    [PexAllowedExceptionFromTypeUnderTest(typeof(ArgumentException), AcceptExceptionSubtypes = true)]
    [TestClass]
    public partial class Y2KCheckerTest
    {
        /// <summary>Test stub for Check()</summary>
        [PexMethod]
        [TestMethod]
        [HostType("Moles"), PexAllowedException(typeof(ApplicationException))]
        public void Check(DateTime dateTime)
        {
            MDateTime.NowGet = () => dateTime;

            Y2KChecker.Check();
            // TODO: add assertions to method Y2KCheckerTest.Check()
        }

        /// <summary>Test stub for Main()</summary>
        [PexMethod]
        public void Main()
        {
            Y2KChecker.Main();
            // TODO: add assertions to method Y2KCheckerTest.Main()
        }
    }
}
