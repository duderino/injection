using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._4
{
    public class Class04
    {
        private Dependency04 dependency = new Dependency04(999);

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
