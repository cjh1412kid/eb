using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Nuite.DBAccessServer
{
    [Serializable]
    public class ShopInfo
    {
        private string seq;

        public string Seq
        {
            get { return seq; }
            set { seq = value; }
        }
        private string areaBaseSeq;

        public string AreaBaseSeq
        {
            get { return areaBaseSeq; }
            set { areaBaseSeq = value; }
        }
        private string shopID;

        public string ShopID
        {
            get { return shopID; }
            set { shopID = value; }
        }
        private string shopName;

        public string ShopName
        {
            get { return shopName; }
            set { shopName = value; }
        }
        private string shopAddress;

        public string ShopAddress
        {
            get { return shopAddress; }
            set { shopAddress = value; }
        }
        private string shopTypeFlag;

        public string ShopTypeFlag
        {
            get { return shopTypeFlag; }
            set { shopTypeFlag = value; }
        }
        private string inputDate;

        public string InputDate
        {
            get { return inputDate; }
            set { inputDate = value; }
        }
    }
}
