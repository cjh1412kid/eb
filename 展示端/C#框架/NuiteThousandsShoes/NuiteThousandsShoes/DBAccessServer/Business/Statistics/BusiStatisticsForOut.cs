using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Pacia.Server.Common;

namespace Nuite.DBAccessServer
{

    public partial class DBAccessServerRemoting : MarshalByRefObject
    {
        #region 获取统计信息表
        /// <summary>
        /// 统计信息表查询
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="time">时间</param>
        /// <param name="flag">0当前时间，-1上一个月，1下一个月</param>
        /// <returns></returns>
        public string GetStatisticsResult(string userName, string pwd, string time, int flag)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiStatistics busi = new BusiStatistics(ref serviceResultData);
                busi.GetStatisticsResult(userName, pwd, time, flag);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }
        #endregion


        #region 保存统计信息
        /// <summary>
        /// 保存统计信息
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoesbaseseq"></param>
        /// <param name="count"></param>
        /// <param name="times"></param>
        /// <returns></returns>
        public string SaveStatisticsResult(string userName, string pwd, string shoesbaseseq, int count, double times)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiStatistics busi = new BusiStatistics(ref serviceResultData);
                busi.SaveStatisticsResult(userName, pwd, shoesbaseseq, count, times);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }
        #endregion
    }
}
