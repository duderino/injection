using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._12
{
    public class Class12
    {
        // Make this public so Moles can generate a mole for it
        public class DependencyImpl : Dependency12
        {
            public int generate()
            {
                return 999;
            }
        }

        private Dependency12 dependency = new DependencyImpl();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
