using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest
{
    public static class Y2KChecker
    {
        public static void Check()
        {
            if (DateTime.Now == new DateTime(2000, 1, 1))
            {
                throw new ApplicationException("y2k bug!");
            }
        }

        public static void Main()
        {
            Check();
        }
    }
}
