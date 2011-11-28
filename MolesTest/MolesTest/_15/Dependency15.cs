using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace MolesTest._15
{
    public class Dependency15
    {
        public int generate()
        {
            return Thread.GetDomainID() + 999;
        }
    }
}
