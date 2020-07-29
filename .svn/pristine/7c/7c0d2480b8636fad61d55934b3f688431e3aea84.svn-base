using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using Pacia.Server.Common;
using Nuite.ServerAccess;

namespace NuiteThousandsShoes
{
    /// <summary>
    /// 前端数据交互类
    /// </summary>
    public class BusDisplayClient : BusiKernel
    {

        /// <summary>
        /// 读取硬件相关配置信息
        /// </summary>
        /// <param name="ferror"></param>
        /// <returns></returns>
        public DataTable GetHardInfoResult(ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "GetHardInfoResult", new object[] { InitConst.DefauleUser, "" });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {
                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return obj.DataTable;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;
                }
            }
            return null;
        }
               

        /// <summary>
        /// 根据标签编号获取鞋子素材
        /// </summary>
        /// <param name="RFIDCode"></param>
        /// <param name="ferror"></param>
        /// <returns>以鞋子序号命名的素材表</returns>
        public DataTable GetPlayResult(string RFIDCode, ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "GetPlayResult", new object[] { InitConst.DefauleUser, "", RFIDCode });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {
                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return obj.DataTable;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;
                }
            }
            return null;
        }


        /// <summary>
        ///根据RFID获取鞋子序号
        /// </summary>
        /// <param name="RFIDCode"></param>
        /// <param name="ferror"></param>
        ///  <param name="RFIDCode">RFIDCode</param>
        /// <returns>鞋子序号</returns>
        public DataTable GetShoeSeqByRFIDCode(string RFIDCode, ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "GetShoeSeqByRFIDCode", new object[] { InitConst.DefauleUser, "", RFIDCode });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {
                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return obj.DataTable;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;
                }
            }
            return null;
        }

        /// <summary>
        /// 根据鞋子序号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeSeq">鞋子序号</param>
        public DataTable GetPlayResultByShoeSeq(string shoeSeq, ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "GetPlayResultByShoeSeq", new object[] { InitConst.DefauleUser, "", shoeSeq });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {
                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return obj.DataTable;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;
                }
            }
            return null;
        }


        /// <summary>
        /// 根据鞋子序号获取相应素材
        /// </summary>
        /// <param name="userName"></param>
        /// <param name="pwd"></param>
        /// <param name="shoeId">鞋子货号</param>
        public DataTable GetPlayResultByShoeId(string shoeId, ref string ferror)
        {
            ServiceResultData obj = WebServiceHelper.InvokeWebService(InitConst.ServiceUrl, "GetPlayResultByShoeID", new object[] { InitConst.DefauleUser, "", shoeId });
            if (obj != null)
            {
                switch (obj.BusinessResult)
                {
                    case 1001: ferror = "验证未通过"; break;
                    case 1004: ferror = "没有查询到数据!"; break;
                    case 0: return obj.DataTable;
                    case 2002: ferror = "调用服务出现异常！"; break;
                    case 2001: ferror = "数据库连接失败！"; break;
                }
            }
            return null;
        }



    }
}
