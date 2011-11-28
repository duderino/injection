using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._17
{
    public class Class17
    {
        private Dependency17 dependency = new Dependency17();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
