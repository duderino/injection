using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._7
{
    public class SuperDependency07
    {
        public virtual int generate()
        {
            return 999;
        }

        public int superGenerate()
        {
            return 999;
        }
    }
}
