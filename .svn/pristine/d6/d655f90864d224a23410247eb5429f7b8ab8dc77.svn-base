using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Pacia.Server.Common;

namespace Nuite.DBAccessServer
{
    public partial class DBAccessServerRemoting : MarshalByRefObject
    {
        #region 硬件配置信息
        /// <summary>
        /// 获取硬件配置信息
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <returns></returns>
        public string GetHardInfoResult(string userName, string pwd)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiDisplayClient busi = new BusiDisplayClient(ref serviceResultData);
                busi.GetHardInfoResult(userName, pwd);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }

        #endregion


        #region 根据RFID获取鞋子序号
        /// <summary>
        /// 根据RFID获取鞋子序号
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="RFIDCode">RFIDCode</param>
        /// <returns></returns>
        public string GetShoeSeqByRFIDCode(string userName, string pwd, string RFIDCode)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiDisplayClient busi = new BusiDisplayClient(ref serviceResultData);
                busi.GetShoeSeqByRFIDCode(userName, pwd, RFIDCode);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }

        #endregion



        #region 获取素材相关
        /// <summary>
        /// 根据标签号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="RFIDCode">-1时表示获取默认宣传素材</param>
        public string GetPlayResult(string userName, string pwd, string RFIDCode)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiDisplayClient busi = new BusiDisplayClient(ref serviceResultData);
                busi.GetPlayResult(userName, pwd, RFIDCode);               
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }

        /// <summary>
        /// 根据鞋子序号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeSeq">鞋子序号</param>
        public string GetPlayResultByShoeSeq(string userName, string pwd, string shoeSeq)
        {

            try
            {
                ServiceResultData serviceResultData = null;
                BusiDisplayClient busi = new BusiDisplayClient(ref serviceResultData);
                busi.GetPlayResultByShoeSeq(userName, pwd, shoeSeq);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }


        /// <summary>
        /// 根据鞋子货号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeId">鞋子货号</param>
        public string GetPlayResultByShoeID(string userName, string pwd, string shoeId)
        {
            try
            {
                ServiceResultData serviceResultData = null;
                BusiDisplayClient busi = new BusiDisplayClient(ref serviceResultData);
                busi.GetPlayResultByShoeID(userName, pwd, shoeId);
                return GetReturnStringAndFree(serviceResultData);
            }
            catch
            { }
            return "";
        }

            #endregion
        }
}
