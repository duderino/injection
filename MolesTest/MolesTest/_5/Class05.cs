using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._5
{
    public class Class05
    {
        public int generate()
        {
            var dependency = new { generate = 999 };

            return dependency.generate * 2;
        }
    }
}
