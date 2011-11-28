using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._15
{
    public class Class15
    {
        private Dependency15 dependency = new Dependency15();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
