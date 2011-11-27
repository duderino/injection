using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._6
{
    public class Class06
    {
        private class Dependency06
        {
            public int generate()
            {
                return 999;
            }
        }

        private Dependency06 dependency = new Dependency06();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
