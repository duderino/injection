using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._8
{
    public class Class08
    {
        private Dependency08 dependency = new Dependency08();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
