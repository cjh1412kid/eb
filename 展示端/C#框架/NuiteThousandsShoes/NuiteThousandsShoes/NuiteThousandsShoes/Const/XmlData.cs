using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NuiteThousandsShoes
{
    public class XmlData
    {
        private string nodeName;

        public string NodeName
        {
            get { return nodeName; }
            set { nodeName = value; }
        }

        private string nodeValue;

        public string NodeValue
        {
            get { return nodeValue; }
            set { nodeValue = value; }
        }

        private bool bEncode;

        public bool BEncode
        {
            get { return bEncode; }
            set { bEncode = value; }
        }

        private string parentNodeName;

        public string ParentNodeName
        {
            get { return parentNodeName; }
            set { parentNodeName = value; }
        }
    }
}
