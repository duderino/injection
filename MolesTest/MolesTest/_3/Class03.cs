using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._3
{
    public class Class03
    {
        public int generate()
        {
            return Dependency03.generate() * 2;
        }
    }
}
