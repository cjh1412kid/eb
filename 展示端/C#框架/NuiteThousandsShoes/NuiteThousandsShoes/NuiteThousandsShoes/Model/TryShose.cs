using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace NuiteThousandsShoes
{

    /// <summary>
    /// 试鞋类
    /// </summary>
    public class TryShoes
    {
        public string RFID { get; set; }	          //标签
        public int ShoesBaseInfoseq { get; set; }	  //鞋子序号
        public DateTime starttimes { get; set; }	  //开始时间
        public DateTime endtimes { get; set; }		  //结束时间
        public DataTable PlayData { get; set; }       //素材资源
    }

}
