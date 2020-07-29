using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using Pacia.Server.Common;
using Nuite.ServerAccess;
using System.IO;
using System.Diagnostics;
using System.Windows.Forms;

using CommonFile;
namespace NuiteThousandsShoes
{
    public class BusiStatistics : BusiKernel
    {
        private List<string> fListValue = new List<string>(); 
        /// <summary>
        /// 统计数据保存(数据库)
        /// </summary>
        /// <param name="shoesbaseseq"></param>
        /// <param name="count"></param>
        /// <param name="times"></param>
        /// <param name="DataTime"></param>
        /// <returns></returns>
        public bool SaveStatisticsResult(TryShoes curSM, ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "SaveStatisticsResult", new object[] { InitConst.DefauleUser, "", curSM.ShoesBaseInfoseq.ToString(), curSM.starttimes, curSM.endtimes });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {

                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return true;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;

                }
            }
            return false;
        }

        /// <summary>
        /// 统计数据保存(本地文件)
        /// </summary>
        /// <param name="curSM"></param>
        /// <param name="ferror"></param>
        /// <returns></returns>
        public bool SaveStatisticsFile(TryShoes curSM, string shopID, ref string ferror)
        {
            bool result = false;
            try
            {
                //string txt = string.Format("鞋子序号：{0},试穿次数：{1}次,试穿时间：{2},开始时间：{3},结束时间：{4}，删除标识：{5},店面序号：{6}",  curSM.ShoesBaseInfoseq, curSM.Count, curSM.times, curSM.starttimes, curSM.endtimes，0,ShopID);
                System.TimeSpan t3 = curSM.endtimes - curSM.starttimes;
                string txt = string.Format("{0},{1},{2},'{3}','{4}',{5},{6}", curSM.ShoesBaseInfoseq, "1", (int)Math.Round(t3.TotalSeconds, MidpointRounding.AwayFromZero), DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"), curSM.starttimes.ToString("yyyy-MM-dd HH:mm:ss"), "0" , shopID);
                fListValue.Add(txt);
                result = true;
            }
            catch (Exception ex)
            {
                ferror = ex.Message;
            }
            return result;
        }

        /// <summary>
        /// 写文件到本地
        /// </summary>
        /// <param name="logString"></param>
        public void WriteToFile(string id)
        {
            string errorStr = "";
            CommonSQLFile csf = new CommonSQLFile();
            string field = "FK_SHOESBASEINFO_SEQ,TRYCOUNT,TRYTIMES,INPUTTIME,DATATIME,DEL,FK_SHOPBASEINFO_SEQ";
            csf.generateFile(id, MotiorCommonLibrary.MotiorCommonLibrary.SystemName.前台试鞋系统.ToString(), "NWPRODUCT_PRODUCTSTATISTICS", field, fListValue, ref errorStr);
            fListValue.Clear();
        }

    }
}
