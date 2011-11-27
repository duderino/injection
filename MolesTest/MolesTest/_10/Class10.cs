using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._10
{
    public class Class10
    {
        private Dependency10 dependency = new Dependency10();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
