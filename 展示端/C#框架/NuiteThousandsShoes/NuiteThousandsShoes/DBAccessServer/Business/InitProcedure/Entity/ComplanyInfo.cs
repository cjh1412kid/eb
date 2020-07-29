using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Nuite.DBAccessServer
{
    [Serializable]
    public class ComplanyInfo
    {
        private string seq;

        public string Seq
        {
            get { return seq; }
            set { seq = value; }
        }

        private string name;

        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        private string del;

        public string Del
        {
            get { return del; }
            set { del = value; }
        }

        private string key;

        public string Key
        {
            get { return key; }
            set { key = value; }
        }
    }
}
