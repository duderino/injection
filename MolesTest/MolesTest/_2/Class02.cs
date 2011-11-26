using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._2
{
    public class Class02
    {
        private Dependency02 dependency = new Dependency02();

        public int generate()
        {
            return dependency.generate() * 2;
        }
    }
}
