using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._1
{
    public class Class01
    {
        private Dependency01 dependency = new Dependency01();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
