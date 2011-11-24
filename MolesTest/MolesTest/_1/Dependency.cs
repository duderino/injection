using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._1
{
    public class Dependency
    {
        public virtual int generate()
        {
            Random random = new Random();

            return random.Next(0, 1000);
        }
    }
}
