using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Nuite.DBAccessServer
{
    [Serializable]
    public class BrandShopRelation
    {
        private string seq;

        public string Seq
        {
            get { return seq; }
            set { seq = value; }
        }
        private string brandSeq;

        public string BrandSeq
        {
            get { return brandSeq; }
            set { brandSeq = value; }
        }
        private string shopBaseInfoSeq;

        public string ShopBaseInfoSeq
        {
            get { return shopBaseInfoSeq; }
            set { shopBaseInfoSeq = value; }
        }
    }
}
