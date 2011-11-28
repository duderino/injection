using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MolesTest._14
{
    public class Class14
    {
        private Dependency14 dependency = new Dependency14();
        private int result = 0;

        public void callback(int result)
        {
            this.result = result;
        }

        public int generate()
        {
            dependency.generate(this);

            return 2 * result;
        }
    }
}
