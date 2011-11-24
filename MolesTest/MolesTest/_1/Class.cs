using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._1
{
    public class Class
    {
        private Dependency dependency = new Dependency();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
