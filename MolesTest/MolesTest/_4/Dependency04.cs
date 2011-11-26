using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._4
{
    public class Dependency04
    {
        private int value;

        public Dependency04(int value)
        {
            this.value = value;
        }

        public int generate()
        {
            return value;
        }
    }
}
