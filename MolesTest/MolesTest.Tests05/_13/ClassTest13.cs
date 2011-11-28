using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Microsoft.Moles.Framework;
using MolesTest._13;
using System.IO.Moles;
using System.IO;

namespace MolesTest.Tests._13
{
    /// <summary>
    /// Demonstrate safely mocking/stubbing out class library
    /// </summary>
    [TestClass]
    public class ClassTest13
    {
        [TestMethod]
        [HostType("Moles")]
        public void test1()
        {
            MFile.OpenTextString = _ => new MStreamReader();

            MStreamReader.AllInstances.ReadLine = _ => "123";

            Class13 clazz = new Class13();

            Assert.AreEqual(2 * 123, clazz.generate1());
        }

 
        [TestMethod]
        [HostType("Moles")]
        public void test2()
        {
            MemoryStream memoryStream = new MemoryStream(new byte[] { 0x31, 0x32, 0x33 });   // "123" in ascii/unicode hex

            MFileStream  mFileStream = new MFileStream();

            mFileStream.CanReadGet = () => memoryStream.CanRead;

            mFileStream.ReadByteArrayInt32Int32 = (byte[] array, int offset, int length) => memoryStream.Read(array, offset, length);

            MFile.OpenStringFileMode = (string path, FileMode mode) => mFileStream;

            Class13 clazz = new Class13();

            Assert.AreEqual(2 * 123, clazz.generate2());
        }
    }
}
