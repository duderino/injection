using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._16
{
    public class Class16
    {
        private Dependency16 dependency = new Dependency16();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
