using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._9
{
    public class Class09
    {
        private Dependency09 dependency = new Dependency09();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
