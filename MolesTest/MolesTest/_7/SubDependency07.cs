using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._7
{
    public class SubDependency07 : SuperDependency07
    {
        public override int generate()
        {
            return 333;
        }

        public int subGenerate()
        {
            return 333;
        }
    }
}
