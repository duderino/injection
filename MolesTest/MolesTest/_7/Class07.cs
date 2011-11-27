using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._7
{
    public class Class07
    {
        private SubDependency07 dependency = new SubDependency07();

        public int generate()
        {
            return 2 * dependency.generate();
        }

        public int superGenerate()
        {
            return 2 * dependency.superGenerate();
        }

        public int subGenerate()
        {
            return 2 * dependency.subGenerate();
        }
    }
}
