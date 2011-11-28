using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.IO;

namespace MolesTest._13
{
    public class Class13
    {
        public int generate1() 
        {
            StreamReader reader = File.OpenText("foo.txt");

            string line = reader.ReadLine();

            return 2 * int.Parse(line);
        }

        public int generate2()
        {
            FileStream stream = File.Open("foo", FileMode.Open);

            StreamReader reader = new StreamReader(stream);

            string line = reader.ReadLine();

            return 2 * int.Parse(line);
        }
    }
}
