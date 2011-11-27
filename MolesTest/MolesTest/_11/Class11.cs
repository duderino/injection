using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._11
{
    public class Class11
    {
        private Dependency11 dependency = new Dependency11();

        public int generate()
        {
            int result = dependency.generate();

            return result + dependency.generate();
        }
    }
}
